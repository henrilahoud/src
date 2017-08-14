package parser;

import javafx.stage.*;
import model.*;
import handler.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static parser.util.HeaderUtils.*;
import static parser.util.StringUtils.*;
import static ui.UiUtils.*;
import static handler.exceptionWrapper.*;

public class CsvWriter {
    private DataWrapper wrapper;
    private Set<String> newTable;

    public CsvWriter(DataWrapper wrapper) throws NullValueRunTimeException {
        this.wrapper = wrapper;
    }

    public void write() {
        try {
            newTable = new HashSet<>();

            for (Emplacement e : wrapper.getEmplacements()) {
                prepareRows(e);
            }
            generateCsv();
        }
        catch (IOException e) {
            exceptions.add(e);
        }
    }

    private void prepareRows(Emplacement e) {
        if (!(e.getConteneurs() == null || (e.getConteneurs().isEmpty()))) {

            // For each conteneur, create a row filling the main information of e, and the information of the conteneur, and add it to newTable
            for (Conteneur c : e.getConteneurs()) {
                String row[] = new String[PIVOTCOLUMNS_NB];

                fillEmplInformation(e, row);
                fillContInformation(c, row);

                newTable.add(joinRow(row));
            }
        }
    }

    private void fillEmplInformation(Emplacement e, String r[]) {
        // TODO First test with partial information
        try {
            r[USAGER_CODEUSAGER] = reformat(e.getUsager().getCodeUsager());
            r[ENQUETE_PAYSHORSFRANCEFACTURATION] = reformat(e.getUsager().getAdresseFacturation().getPays());
        }
        catch (Exception ex) {
            System.out.println("fillEmplInformation");
        }
    }

    private void fillContInformation(Conteneur c, String r[]) {
        // TODO First test with partial information
        try {
            r[ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC] = reformat(c.getDateDistribution());
        }
        catch (Exception ex) {
            System.out.println("fillContInformation");
        }
    }

    public void generateCsv() throws IOException {
        adviseUser(INFORMATIONTITLE, INFORMATIONHEADER, INFORMATIONCONTENT);

        FileChooser saveAs = new FileChooser();
        // TODO Description + rewrite in UiUtils
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TEXT","*.csv");
        saveAs.setSelectedExtensionFilter(filter);
        saveAs.setTitle("Emplacement du fichier généré");

        File NewCsv = saveAs.showSaveDialog(null);

        if (NewCsv != null){
            FileWriter Writer = new FileWriter(new File(NewCsv.toString() + ".csv"));

            // Append titles
            Writer.append(MAINTITLES);
            Writer.append(SUBTITLES);

            for (String row : newTable){
                Writer.append(row);
            }
            Writer.flush();
            Writer.close();
        }
    }
}
