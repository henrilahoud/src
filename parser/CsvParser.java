package parser;

import handler.NoDataParsedException;
import model.Emplacement;
import parser.util.HeaderUtils;
import parser.util.Statistics;
import parser.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import static handler.ExceptionWrapper.exceptions;
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
        Map<String, Integer> headers = openFile(f);

        resetStats();
        resetCodeUsager();

        int progress = 0;


        if ((csvTable.isEmpty())) {
            throw new NoDataParsedException();
            // Error while openingFile or no entries. Return null
        }
        updateProgress(progress);
        // For each row, determine whether it is a user row or a container row.
        Set<Emplacement> emplacements = new HashSet<>();
        ParentRowParser parentRowParser = new ParentRowParser(headers);
        
        // First fill Usagers to be able to search them and link them to their Conteneur
        for (String[] r : csvTable) {
            if (parentRowParser.supports(r)) {
                if (!(emplacements.add(parentRowParser.parse(r)))) {
                }
                updateProgress(++progress * 100 / totalNbOfLines);
            }
        }

        //Then fill conteneurs
        ChildRowParser childRowParser = new ChildRowParser(headers, emplacements);
        for (String[] r : csvTable) {
            if (childRowParser.supports(r)) {
                childRowParser.parse(r);
                updateProgress(++progress * 100 / totalNbOfLines);
            }
        }
        return new DataWrapper(new ArrayList<>(emplacements), new ArrayList<>(exceptions));
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
