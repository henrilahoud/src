package parser;

import handler.NullValueRunTimeException;
import model.Emplacement;
import parser.util.HeaderUtils;
import parser.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static handler.exceptionWrapper.*;
import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class CsvParser implements GenericParser<DataWrapper,File> {

    private BufferedReader csvReader;
    private ArrayList<String[]> csvTable;

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
            }
            return new DataWrapper(new ArrayList<>(emplacements), new ArrayList<>(exceptions));
        } catch (Exception e) {
            exceptions.add(e);
            throw new NullValueRunTimeException(e);
        }
    }

    private Map<String, Integer> openFile(File f) {
        try {
            csvReader = Files.newBufferedReader(f.toPath(), ISO_8859_1);
            csvTable = new ArrayList<>();

            // Parse header
            String header = csvReader.readLine();
            Map<String, Integer> headers = HeaderUtils.mapHeaders(header);

            // Parse rows
            if (headers != null) {
                String row = csvReader.readLine();
                while (row != null) {
                    csvTable.add(StringUtils.splitRow(row));
                    row = csvReader.readLine();
                }
            }

            csvReader.close();
            return headers;
        }
        catch (IOException e) {
            exceptions.add(e);
            return null;
        }
    }
}
