package parser;

import handler.ErrorHandler;
import model.Conteneur;
import model.Emplacement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import parser.GenericParser;
import parser.util.HeaderUtils;
import parser.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;

public class XlsxParser implements GenericParser <DataWrapper,File> {
    private XSSFWorkbook xlsx;

    private Consumer<Integer> onProgressUpdateListener;

    public XlsxParser(Consumer<Integer> onProgressUpdateListener) {
        this.onProgressUpdateListener = onProgressUpdateListener;
    }

    private void updateProgress(Integer progress) {
        if (onProgressUpdateListener != null) {
            onProgressUpdateListener.accept(progress);
        }
    }

    @Override
    public DataWrapper parse (File f) throws Exception {
        Map<String, Emplacement> emplacements = new HashMap<>(); // Each Emplacement parsed will be added to this Map, having for key the ID given by Daxium
        Map<String, Conteneur> conteneurs = new HashMap<>(); // Same

        // Will process every emplacement and run check ups on the data to see if correctly entered
        ErrorHandler error = new ErrorHandler();

        try {
            // Finds the workbook instance for XLSX file and fetch all the headers
            xlsx = new XSSFWorkbook(f);

            if (xlsx.getNumberOfSheets() != 4) {
                throw new IllegalStateException("Le fichier Excel n'est pas au bon format");
            }

            Map<String, Integer> emplacementHeaders;
            Map<String, Integer> distribHeaders;
            Map<String, Integer> inventaireHeaders;
            Map<String, String> lookup;

            XSSFSheet emplacementsSheet = xlsx.getSheetAt(0);
            XSSFSheet distribSheet = xlsx.getSheetAt(1);
            XSSFSheet inventaireSheet = xlsx.getSheetAt(2);
            XSSFSheet lookUpSheet = xlsx.getSheetAt(3);

            int totalNbOfRows = emplacementsSheet.getPhysicalNumberOfRows() + distribSheet.getPhysicalNumberOfRows() + inventaireSheet.getPhysicalNumberOfRows() + lookUpSheet.getPhysicalNumberOfRows();
            int progressCounter = 0;

            int nbEmplacements = 0;
            int nbConteneurs = 0;

            // First, map headers, they will be injected into the Parsers to bind the Data with the correct attributes of Emplacement and Conteneur
            emplacementHeaders = fetchHeaders(xlsx, 0);
            distribHeaders = fetchHeaders(xlsx, 1);
            inventaireHeaders = fetchHeaders(xlsx, 2);
            lookup = fetchLookup(xlsx);


            /* Now, fetch the Emplacement and the Conteneur :
               Model : <ID, Emplacement>
                       <ID, Conteneur>
                       <ID, ID> to bind them
            */
            for (int ri = 1; ri < emplacementsSheet.getPhysicalNumberOfRows(); ri++) { //Starts at 1 to avoid taking the headers into account
                XSSFRow row = emplacementsSheet.getRow(ri);
                ArrayList<String> rowArray = new ArrayList<>();

                for (int i = 0; i < emplacementHeaders.size(); i++) {
                    if (row.getCell(i,RETURN_NULL_AND_BLANK) == null) {
                        rowArray.add("");
                    } else {
                        rowArray.add(StringUtils.XlsxStringValue(row.getCell(i,RETURN_NULL_AND_BLANK)));
                    }
                }

                EmplacementParser ep = new EmplacementParser(emplacementHeaders);
                Emplacement e = ep.parse(rowArray);

                emplacements.put(e.getIdentifier(), e);
                updateProgress(++progressCounter * 100 / totalNbOfRows);
                nbEmplacements++;
            }

            // Emplacements have been made, now fetch all the Conteneurs (those distributed, second sheet)
            for (int ri = 1; ri < distribSheet.getPhysicalNumberOfRows(); ri++) { //Starts at 1 to avoid taking the headers into account
                Row row = distribSheet.getRow(ri);
                ArrayList<String> rowArray = new ArrayList<>();

                for (int i = 0; i < distribHeaders.size(); i++) {
                    if (row.getCell(i,RETURN_NULL_AND_BLANK) == null) {
                        rowArray.add("");
                    } else {
                        rowArray.add(StringUtils.XlsxStringValue(row.getCell(i,RETURN_NULL_AND_BLANK)));
                    }
                }

                ConteneurParser cp = new ConteneurParser(distribHeaders);
                Conteneur c = cp.parse(rowArray);

                conteneurs.put(c.getIdentifier(), c);
                updateProgress(++progressCounter * 100 / totalNbOfRows);
                nbConteneurs++;
            }

            // Emplacements have been made, now fetch all the Conteneurs (those in the inventaire AND containing Puce numbers, third sheet)
            for (int ri = 1; ri < inventaireSheet.getPhysicalNumberOfRows(); ri++) { //Starts at 1 to avoid taking the headers into account
                Row row = inventaireSheet.getRow(ri);
                ArrayList<String> rowArray = new ArrayList<>();

                for (int i = 0; i < inventaireHeaders.size(); i++) {
                    if (row.getCell(i,RETURN_NULL_AND_BLANK) == null) {
                        rowArray.add("");
                    } else {
                        rowArray.add(StringUtils.XlsxStringValue(row.getCell(i,RETURN_NULL_AND_BLANK)));
                    }
                }

                ConteneurParser cp = new ConteneurParser(inventaireHeaders);
                Conteneur c = cp.parse(rowArray);

                if (!(c.getNumeroCuve().isEmpty() && c.getNumeroPuce().isEmpty() && c.getNumeroCab().isEmpty())) {
                    conteneurs.put(c.getIdentifier(), c);
                    nbConteneurs++;
                }
                updateProgress(++progressCounter * 100 / totalNbOfRows);
            }

            // Emplacements and Conteneurs have been extracted, now bind them
            for (String key : lookup.keySet()) {
                try {
                    if (conteneurs.containsKey(key)) {
                        emplacements.get(lookup.get(key)).getConteneurs().add(conteneurs.get(key)); // Fetching the emplacement with ID = value associated to "key" and adding conteneur with ID "key" to it
                        error.process(emplacements.get(lookup.get(key)));
                        updateProgress(++progressCounter * 100 / totalNbOfRows);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
        }

        return new DataWrapper(new ArrayList<>(emplacements.values()), emplacements.size(), conteneurs.size(), error);
    }

    private Map<String, Integer> fetchHeaders(XSSFWorkbook xlsx, int sheetPosition) {
        Map<String, Integer> headers = new HashMap<>();

        if (xlsx.getNumberOfSheets() <= sheetPosition) {
            throw new IllegalStateException("L'indice de l'onglet recherché est hors périmètre");
        }

        // Return sheet from the XLSX workbook
        XSSFSheet mySheet = xlsx.getSheetAt(sheetPosition);

        // Get 1st row and iterate through the titles to map Title with Column number
        Row firstRow = mySheet.getRow(0);
        Iterator<Cell> ci = firstRow.cellIterator();

        while (ci.hasNext()) {
            Cell c = ci.next();
            headers.put(StringUtils.XlsxStringValue(c),Integer.valueOf(c.getColumnIndex()) );
        }

        return headers;
    }

    private Map<String, String> fetchLookup(XSSFWorkbook xlsx) {
        Map<String, String> lookup = new HashMap<>();

        if (xlsx.getNumberOfSheets() < 4) { // The lookup table is located on the 4th sheet
            throw new IllegalStateException("L'indice de l'onglet recherché est hors périmètre");
        }

        // Return sheet from the XLSX workbook
        XSSFSheet mySheet = xlsx.getSheetAt(3); // The lookup table is located on the 4th sheet

        Iterator<Row> i = mySheet.rowIterator();

        // Go through each row and select 1st and 3rd cells
        // Key must be 3rd Cell to ensure unicity
        while (i.hasNext()) {
            Row r = i.next();

            lookup.put(StringUtils.XlsxStringValue(r.getCell(2)),StringUtils.XlsxStringValue(r.getCell(0)));
        }

        return lookup;
    }
}
