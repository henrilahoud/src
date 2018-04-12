package ui;

import handler.NullValueRunTimeException;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Emplacement;
import parser.*;
import parser.util.HeaderUtils;
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

//import static handler.exceptionWrapper.exceptions;
import static parser.util.HeaderUtils.resetCodeUsager;
import static parser.util.StringUtils.CSVREGEX;
import static parser.util.StringUtils.savePath;
import static ui.UiUtils.*;
import static ui.UiUtils.WRONGTYPECONTENT;
import static ui.UiUtils.warnUser;

public class EnqueteToPivotController {
    @FXML
    private VBox progressBarVBox;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private GridPane gridPane;

    private BufferedReader csvReader;
    private ArrayList<String[]> csvTable;
    private int totalNbOfLines;

    @FXML
    protected void handleLoadButtonAction(ActionEvent event) throws Exception {
        //CsvLoader loader = new CsvLoader();
        try {
            /*
            Stage fileChooserStage = new Stage();


            fileChooserStage.show();

            FileChooser openFileWindow = new FileChooser();
            File csvFile = openFileWindow.showOpenDialog(fileChooserStage);

            if (csvFile == null) {
                warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);

            } else if (!(csvFile.getPath().toLowerCase().matches(CSVREGEX))) {
                warnUser(ERRORTITLE, WRONGTYPEHEADER, WRONGTYPECONTENT);
            }
            */


            //If file verification successful, begin process

            progressBarVBox.setVisible(true);

            //CsvParser parser = new CsvParser();
            //DataWrapper wrapper = parser.parse(csvFile);

            ArrayList<Emplacement> emplacements = parse(/*csvFile*/);

            progressBarVBox.setVisible(false);

            if (emplacements != null) {
                CsvWriter writer = new CsvWriter(emplacements);
                writer.write();
            } else {
                warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
            }

        } catch (Exception e) {
            //exceptions.add(e);
            warnUser("Erreur fatale", "Veuillez transmettre cette erreur à l'équipe RIVP :", e.getMessage());
        }

        //progressBarVBox.setVisible(true);


    }

    /*
    private void load(File csvFile) throws Exception {
        try {
            if (csvFile != null) {
                if (csvFile.getPath().toLowerCase().matches(CSVREGEX)) {

                    //If file verification successful, begin process
                    progressBarVBox.setVisible(true);

                    //CsvParser parser = new CsvParser();
                    //DataWrapper wrapper = parser.parse(csvFile);

                    ArrayList<Emplacement> emplacements = parse(csvFile);

                    progressBarVBox.setVisible(false);

                    if (emplacements != null) {
                        CsvWriter writer = new CsvWriter(emplacements);
                        writer.write();
                    } else {
                        warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
                    }
                } else {
                    warnUser(ERRORTITLE, WRONGTYPEHEADER, WRONGTYPECONTENT);
                }
            }
        } catch (Exception e) {
            //exceptions.add(e);
            warnUser("Erreur fatale", "Veuillez transmettre cette erreur à l'équipe RIVP :", e.getMessage());
        }
    }
    */


    private ArrayList<Emplacement> parse(/*File f*/) throws Exception {
        try {
            Map<String, Integer> headers = openFile(/*f*/);

            //TODO exceptions has to be emptied at each new file
            if ((csvTable.isEmpty())) {
                // Error while openingFile or no entries. Return null
                return null;
            }

            // For each row, determine whether it is a user row or a container row.
            Set<Emplacement> emplacements = new HashSet<>();
            ParentRowParser parentRowParser = new ParentRowParser(headers);
            int progress = 0;
                // First fill Usagers to be able to search them and link them to their Conteneur
                resetCodeUsager();
                for (String[] r : csvTable) {
                    if (parentRowParser.supports(r)) {
                        emplacements.add(parentRowParser.parse(r));
                        progressBar.setProgress(progress++ / totalNbOfLines);
                    }
                }

                ChildRowParser childRowParser = new ChildRowParser(headers, emplacements);
                for (String[] r : csvTable) {
                    if (childRowParser.supports(r)) {
                        childRowParser.parse(r);
                        progressBar.setProgress(progress++ / totalNbOfLines);
                    }
                }

                return new ArrayList<>(emplacements);
            }

            catch (Exception e) {
            //System.out.printf(counter.toString());TODO Exception message
            //exceptions.add(e);
            throw new Exception(e);
        }
    }

    /*
    private ArrayList<Emplacement> parse(File f) throws Exception {
        try {
            Map<String, Integer> headers = openFile(f);

            Task<ArrayList<Emplacement>> startTreatment = new Task<ArrayList<Emplacement>>() {
                @Override
                protected ArrayList<Emplacement> call() throws Exception {

                    //TODO exceptions has to be emptied at each new file
                    if ((csvTable.isEmpty())) {
                        // Error while openingFile or no entries. Return null
                        return null;
                    }

                    // For each row, determine whether it is a user row or a container row.
                    Set<Emplacement> emplacements = new HashSet<>();
                    ParentRowParser parentRowParser = new ParentRowParser(headers);
                    int progress = 0;

                    // First fill Usagers to be able to search them and link them to their Conteneur
                    resetCodeUsager();
                    for (String[] r : csvTable) {
                        if (parentRowParser.supports(r)) {
                            emplacements.add(parentRowParser.parse(r));
                            progressBar.setProgress(progress++ / totalNbOfLines);
                        }
                    }

                    ChildRowParser childRowParser = new ChildRowParser(headers, emplacements);
                    for (String[] r : csvTable) {
                        if (childRowParser.supports(r)) {
                            childRowParser.parse(r);
                            progressBar.setProgress(progress++ / totalNbOfLines);
                        }
                    }

                    return new ArrayList<>(emplacements);
                }

                @Override
                protected void succeeded() {
                    System.out.println("Job Done");
                }
            };

            startTreatment.setOnSucceeded(e-> startTreatment.getValue());

            Thread initiate = new Thread(startTreatment);
            //TODO thread n'est pas attendu

            initiate.start();

            return null;

        } catch (Exception e) {
            //System.out.printf(counter.toString());TODO Exception message
            //exceptions.add(e);
            throw new NullValueRunTimeException(e);
        }
    }
    */

    private Map<String, Integer> openFile(/*File f*/) throws Exception {
        Map<String, Integer> headers = null;

        try {
            csvReader = Files.newBufferedReader(new File("/Users/Henri/Desktop/2018_04_04_10_33_16_1.csv").toPath(), StandardCharsets.ISO_8859_1);
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

            this.totalNbOfLines = csvTable.size();
        }
        catch (Exception e) {
            //exceptions.add(e);
            return null;
        }
        finally {
            csvReader.close();
            return headers;
        }
    }
}