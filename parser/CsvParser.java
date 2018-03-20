package parser;

import handler.NullValueRunTimeException;
import model.Emplacement;
import parser.util.HeaderUtils;
import parser.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static handler.exceptionWrapper.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static parser.util.HeaderUtils.resetCodeUsager;

public class CsvParser implements GenericParser<DataWrapper,File> {

    private BufferedReader csvReader;
    private ArrayList<String[]> csvTable;
    public static Integer counter = 1;

    @Override
    public DataWrapper parse(File f) throws NullValueRunTimeException {
        try {
            Map<String, Integer> headers = openFile(f);

            //TODO exceptions has to be emptied at each new file
            if ((csvTable.isEmpty())) {
                // Error while openingFile or no entries. Return null
                return null;
            }

            // For each row, determine whether it is a user row or a container row.
            Set<Emplacement> emplacements = new HashSet<>();
            ParentRowParser parentRowParser = new ParentRowParser(headers);


            // First fill Usagers to be able to search them and link them to their Conteneur
            resetCodeUsager();
            for (String[] r : csvTable) {
                if (parentRowParser.supports(r)) {
                    emplacements.add(parentRowParser.parse(r));
                }
            }

            ChildRowParser childRowParser = new ChildRowParser(headers, emplacements);
            for (String[] r : csvTable) {
                if (childRowParser.supports(r)) {
                    childRowParser.parse(r);
                }
                counter ++;
            }
            return new DataWrapper(new ArrayList<>(emplacements), new ArrayList<>(exceptions));
        } catch (Exception e) {
            System.out.printf(counter.toString());
            exceptions.add(e);
            throw new NullValueRunTimeException(e);
        }
    }

    private Map<String, Integer> openFile(File f) throws IOException {
        Map<String, Integer> headers = null;

        try {
            csvReader = Files.newBufferedReader(f.toPath(), StandardCharsets.ISO_8859_1);
            csvTable = new ArrayList<>();

            // Parse header
            String header = csvReader.readLine();
            headers = HeaderUtils.mapHeaders(header);

            // Parse rows
            if (headers != null) {
                String row = csvReader.readLine();
                while (row != null) {
                    csvTable.add(StringUtils.splitRow(row));
                    row = csvReader.readLine();
                }
            }
        }
        catch (IOException e) {
            exceptions.add(e);
            return null;
        }
        finally {
            csvReader.close();
            return headers;
        }
    }
}
