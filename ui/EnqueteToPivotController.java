package ui;

import handler.NoDataParsedException;
import handler.NullValueRunTimeException;
import handler.UnsupportedFileException;
import handler.WrongFileTypeException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import parser.DataWrapper;

import java.io.File;
import java.io.FileNotFoundException;

import static parser.util.StringUtils.CSVREGEX;
import static parser.util.StringUtils.savePath;
import static ui.UiUtils.*;

public class EnqueteToPivotController {
    @FXML
    private VBox progressBarVBox;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Text progressValue;
    @FXML
    private Text progressMessage;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button loadBtn;
    
    @FXML
    public void initialize() {
        loadBtn.setOnAction(event -> {
            try {
                // Show file chooser (blocking)
                FileChooser openFileWindow = new FileChooser();
                // Wait for file
                File csvFile = openFileWindow.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
                if (csvFile == null) {
                    //TODO not sure if user has to be notified here
                    //warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
                    return; // Need to exit here
                } else if (!(csvFile.getPath().toLowerCase().matches(CSVREGEX))) {
                    warnUser(ERRORTITLE, WRONGTYPEHEADER, WRONGTYPECONTENT);
                    return; // Need to exit here
                }
                
                // If file verification successful, begin process by showing progressbar
                showProgressBar();
                
                // Then begin loading process
                loadAndParseCsvFile(csvFile);

            } catch (Exception e) {
                warnUser("Erreur fatale", "Veuillez transmettre cette erreur à l'équipe RIVP :", e.getMessage());
            }

        });
    }
    
    private void showProgressBar() {
      progressBarVBox.setVisible(true);
    }
    
    private void hideProgressBar() {
      progressBarVBox.setVisible(false);
    }
    
    
    private void updateProgress(Number n) {
      progressBar.setProgress(n.doubleValue());
      int progressPercent = (int) (Math.floor(n.doubleValue() * 100));
      progressValue.setText(String.valueOf(progressPercent) + " %");
    }
    
    private void loadAndParseCsvFile(File csvFile) {
      // THIS CODE IS CALLED FROM MAIN THREAD
      
      // 1: Create Load CSV Task
      LoadTask loadTask = LoadTask.create(csvFile);
      
      loadTask.progressProperty().addListener((observable, oldValue, newValue) -> {
        updateProgress(newValue);
        String text;
        int progressPercent = (int) (Math.floor((double) newValue * 100));
        if (progressPercent == 0) {
            text = "Chargement...";
        } else if (progressPercent < 20) {
            text = "Ça va aller vite !";
        } else if (progressPercent < 40) {
            text = "Bon d'accord je suis optimiste";
        } else if (progressPercent < 60) {
            text = "Je fais ce que je peux...";
        } else if (progressPercent < 80) {
            text = "Je fatigue un peu là...";
        } else if (progressPercent < 100) {
            text = "Allez, dernière ligne droite";
        } else {
            text = "Chargement...";
        }
        progressMessage.setText(text);
      });
      loadTask.setOnSucceeded(e -> {
        // THIS CODE WILL BE EXECUTED ON MAIN THREAD
        // CSV was loaded successfully. Get result and save
        DataWrapper wrapper = loadTask.getValue();
        updateProgress(1.0);
        progressMessage.setText("Document correctement chargé et parsé");
        saveCsvFile(wrapper);
      });
      loadTask.setOnFailed(e -> {
        // THIS CODE WILL BE EXECUTED ON MAIN THREAD
        // Your background thread code raised an exception.
        // Too bad. Now handle it here!
        if (loadTask.getException() instanceof FileNotFoundException) {
            warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
        } else if (loadTask.getException() instanceof WrongFileTypeException) {
            warnUser(ERRORTITLE, WRONGTYPEHEADER, WRONGTYPECONTENT);
        } else if (loadTask.getException() instanceof NoDataParsedException) {
            warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
        } else if (loadTask.getException() instanceof UnsupportedFileException) {
            warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
        } else if (loadTask.getException() instanceof NullValueRunTimeException) {
            warnUser(ERRORTITLE, "Veuillez transmettre cette erreur à l'équipe RIVP :", loadTask.getException().getMessage());
        }
        else {
            warnUser(ERRORTITLE, "TODO", "TODO");
        }

        // TODO implement other errors
        // Error has happened, so we hide progress bar
        hideProgressBar();
      });
      
      // HERE we run the other thread which will call the loadTask code and handle all success / callback events on calling thread
      new Thread(loadTask).start();
    }
    
    private void saveCsvFile(DataWrapper data) {
        // THIS CODE IS CALLED FROM MAIN THREAD
        if (data.getEmplacements() == null) {
        warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
        return;
        }
        //Advise User that he has to save the created doc
        adviseUser(SAVEFILETITLE, SAVEFILEHEADER, SAVEFILECONTENT);

        // Show save as dialog
        FileChooser saveAs = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Fichiers CSV","*.csv");
        saveAs.getExtensionFilters().add(filter);
        saveAs.setInitialDirectory(savePath);
        saveAs.setTitle("Emplacement du fichier généré");
        File output = saveAs.showSaveDialog(progressBar.getScene().getWindow());

        progressMessage.setText("Sauvegarde du fichier pivot");

        WriteTask writeTask = WriteTask.create(output, data);
        writeTask.progressProperty().addListener((observable, oldValue, newValue) -> {
            updateProgress(newValue);
        });

        writeTask.setOnSucceeded(e -> {
            updateProgress(1);
            progressMessage.setText("File saved successfully");

            // THIS CODE IS CALLED FROM MAIN THREAD
            // EVERYTHING WENT WELL
            adviseUserJobDone(data.getNbEmplacements(), data.getNbConteneurs());
            hideProgressBar();
      });
      writeTask.setOnFailed(e -> {
        if (writeTask.getException() instanceof IllegalStateException) {
            adviseUser(SAVEFILETITLE, SAVEFILEHEADER, SAVEFILECONTENT);
        }

        hideProgressBar();
      });
      new Thread(writeTask).start();
    }
    
}