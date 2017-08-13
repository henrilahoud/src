package old;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.lang.String;

public class OldCsvParser {
    static final int FIRST_ROW = 1;

    private HashMap<String, Integer> Columns; //NomColonne, Ligne
    private HashMap<String, Integer> ContainerRows; //ID, Ligne
    private HashMap<String, Integer> ParentRows; //ID, Ligne
    private ArrayList<String> RowsToDelete; //ID
    private BufferedReader CsvReader;
    private ArrayList<String[]> CsvTable;
    private ArrayList<Integer> PivotRows;
    private boolean AllLoaded;

    public OldCsvParser(File Csv) throws IOException {
        AllLoaded = false;
        CsvReader = Files.newBufferedReader(Csv.toPath(), java.nio.charset.StandardCharsets.ISO_8859_1);
        CsvTable = new ArrayList<>();

        String Row = CsvReader.readLine();
        while (Row != null) {
            CsvTable.add(Row.replace("\"", "").concat(";Enquête de Dotation:Code usager").split(";"));
            Row = CsvReader.readLine();
        }
        CsvReader.close();

        if (CsvTable.size() != 0) {
            fillColumnsTitles();
        } else {
            Alert Msg = new Alert(Alert.AlertType.ERROR);
            Msg.setTitle("Erreur");
            Msg.setHeaderText("Le fichier choisi a l'air vide");
            Msg.setContentText("Veuillez vérifier l'intégrité du fichier importé.");
            Msg.show();
        }

        if (identifyRows()) {
            fillInformationInRows();
            selectRowsToCopy();
            AllLoaded = true;
        }
    }

    public boolean isAllLoaded() {
        return AllLoaded;
    }

    public ArrayList<String[]> getCsvTable() {
        return CsvTable;
    }

    public ArrayList<Integer> getPivotRows() {
        return PivotRows;
    }

    public HashMap<String, Integer> getColumns() {
        return Columns;
    }

    private void fillColumnsTitles() {
        String[] FirstRow = CsvTable.get(0);
        Columns = new HashMap<String, Integer>();

        for (int i = 0; i < FirstRow.length; i++) {
            Columns.put(FirstRow[i], i);
        }
    }

    /*
        On identifie chaque ligne (Usager ou conteneur)
        Si Conteneur, on enregistre ID et numéro de ligne, on enregistre IDParent dans la table des "à supprimer"
        Si Usager (Parent), on enregistre ID et numéro de ligne, on enregistre ID et Code Usager
     */
    private boolean identifyRows() {
        try {
            int IdentifierIndex = Columns.get("Identifier");
            int ParentIdIndex = Columns.get("ParentId");
            int UserCodeIndex = Columns.get("Enquête de Dotation:Code usager");
            int UserCode = 1;

            ContainerRows = new HashMap<String, Integer>();
            ParentRows = new HashMap<String, Integer>();
            RowsToDelete = new ArrayList<String>();

            for (int i = FIRST_ROW; i < CsvTable.size(); i++) {

                if (CsvTable.get(i)[ParentIdIndex].length() > 0) { //Si le champ ParentId est rempli
                    ContainerRows.put(CsvTable.get(i)[IdentifierIndex], i);
                    RowsToDelete.add(CsvTable.get(i)[ParentIdIndex]);
                } else if (CsvTable.get(i)[ParentIdIndex].length() == 0) { //Si le champ ParentId est vide
                    CsvTable.get(i)[UserCodeIndex] = Integer.toString(UserCode);
                    ParentRows.put(CsvTable.get(i)[IdentifierIndex], i);
                    UserCode++;
                } else { //là on a un problème
                    System.out.println("Ligne " + i + " n'est ni conteneur ni usager");
                }
            }
            return true;
        } catch (Exception e) {
            Alert Msg = new Alert(Alert.AlertType.ERROR);
            Msg.setTitle("Erreur");
            Msg.setHeaderText("Le fichier choisi ne semble pas correspondre");
            Msg.setContentText("Les intitulés de colonnes ne sont pas identifiés.\nVeuillez vous assurer du bon format du fichier choisi.");
            Msg.show();
            return false;
        }
    }

    private void fillInformationInRows() {
        for (HashMap.Entry<String, Integer> Container : ContainerRows.entrySet()) {
            int ContainerRow = Container.getValue();
            String ParentId = CsvTable.get(ContainerRow)[Columns.get("ParentId")];
            int ParentRowIndex = ParentRows.get(ParentId).intValue();

            copyInformations(ParentRowIndex, Container.getValue());
        }
    }

    private void copyInformations(int Parent, int Child) {
        for (HashMap.Entry<String, Integer> Column : Columns.entrySet()) {
            if ((Column.getKey().matches("^Enquête de Dotation:.+")) && !(Column.getKey().matches(".+CONTENEURISATION.+"))) {
                CsvTable.get(Child)[Column.getValue()] = CsvTable.get(Parent)[Column.getValue()];
            }
        }
    }

    private void selectRowsToCopy() {
        PivotRows = new ArrayList<Integer>();

        for (HashMap.Entry<String, Integer> Container : ContainerRows.entrySet()) {
            PivotRows.add(Container.getValue());
        }

        for (HashMap.Entry<String, Integer> Parent : ParentRows.entrySet()) {
            if (!RowsToDelete.contains(Parent.getKey())) {
                PivotRows.add(Parent.getValue());
            }
        }
    }
}
