/*
package parser;

import handler.ErrorHandler;
import handler.NoDataParsedException;
import model.Emplacement;
import parser.util.HeaderUtils;
import parser.util.Statistics;
import parser.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;

import static parser.util.HeaderUtils.resetCodeUsager;
import static parser.util.Statistics.resetStats;

public class CsvParser implements GenericParser<DataWrapper,File> {

    private BufferedReader csvReader;
    private ArrayList<String[]> csvTable;
    private int totalNbOfLines;

    public int getTotalNbOfLines() {
        return totalNbOfLines;
    }

    public void setTotalNbOfLines(int totalNbOfLines) {
        this.totalNbOfLines = totalNbOfLines;
    }

    private Consumer<Integer> onProgressUpdateListener;
    
    public CsvParser(Consumer<Integer> onProgressUpdateListener) {
        this.onProgressUpdateListener = onProgressUpdateListener;
    }
    
    private void updateProgress(Integer progress) {
        if (onProgressUpdateListener != null) {
            onProgressUpdateListener.accept(progress);
        }
    }
    
    @Override
    public DataWrapper parse(File f) throws Exception {
        Map<String, Integer> headers = openCsvFile(f);

        resetStats();
        resetCodeUsager();

        ErrorHandler errorHandler = new ErrorHandler();

        int progress = 0;


        if ((csvTable.isEmpty())) {
            throw new NoDataParsedException();
            // Error while openingFile or no entries. Return null
        }
        updateProgress(progress);
        // For each row, determine whether it is a user row or a container row.
        Set<Emplacement> emplacements = new HashSet<>();
        EmplacementParser emplacementParser = new EmplacementParser(headers);
        
        // First fill Usagers to be able to search them and link them to their Conteneur
        for (String[] r : csvTable) {
            if (emplacementParser.supports(r)) {
                Emplacement e = emplacementParser.parse(r);
                emplacements.add(e);

                updateProgress(++progress * 100 / totalNbOfLines);
            }
        }

        //Reset Progress to take the error handler into account
        int nbEmp = progress;
        progress = 0;

        //Then fill conteneurs
        ConteneurParser conteneurParser = new ConteneurParser(headers, emplacements);
        for (String[] r : csvTable) {
            if (conteneurParser.supports(r)) {
                conteneurParser.parse(r);
                updateProgress((nbEmp + ++progress) * 100 / (totalNbOfLines));
            }
        }

        for (Emplacement e : emplacements) {
            errorHandler.process(e);
            updateProgress((nbEmp + ++progress) * 100 / (totalNbOfLines));
        }

        return new DataWrapper(new ArrayList<>(emplacements), Statistics.nbEmplacements, Statistics.nbConteneurs, errorHandler);
    }

    private Map<String, Integer> openCsvFile(File f) throws IOException {
        Map<String, Integer> headers = null;

        try {
            csvReader = Files.newBufferedReader(f.toPath(), StandardCharsets.ISO_8859_1);
            csvTable = new ArrayList<>();

            // Parse header
            String header = csvReader.readLine();
            headers = HeaderUtils.mapHeaders(header);

            // Parse rows
            if (headers == null) {
            }
                String row = csvReader.readLine();
                while (row != null) {
                    csvTable.add(StringUtils.splitRow(row));
                    row = csvReader.readLine();
                }


            this.totalNbOfLines = csvTable.size();
            return headers;
        } finally {
            csvReader.close();
        }
    }
}
*/
