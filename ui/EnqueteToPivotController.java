package ui;

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
import static ui.UiUtils.ERRORTITLE;
import static ui.UiUtils.NODATACONTENT;
import static ui.UiUtils.NODATAHEADER;
import static ui.UiUtils.WRONGTYPECONTENT;
import static ui.UiUtils.WRONGTYPEHEADER;
import static ui.UiUtils.warnUser;

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
                    warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
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
        if (progressPercent <= 10) {
          text = "Loading file";
        } else if (progressPercent < 20) {
          text = "Sorting user and container rows";
        } else if (progressPercent < 60) {
          text = "Parsing parent rows";
        } else if (progressPercent < 100) {
          text = "Parsing child rows";
        } else {
          text = "Loading";
        }
        progressMessage.setText(text);
      });
      loadTask.setOnSucceeded(e -> {
        // THIS CODE WILL BE EXECUTED ON MAIN THREAD
        // CSV was loaded successfully. Get result and save
        DataWrapper wrapper = loadTask.getValue();
        updateProgress(1.0);
        progressMessage.setText("File successfully loaded and parsed");
        saveCsvFile(wrapper);
      });
      loadTask.setOnFailed(e -> {
        // THIS CODE WILL BE EXECUTED ON MAIN THREAD
        // TODO henri regarde comment on gère l'UI récupérée à partir d'une exception ici
        // Your background thread code raised an exception.
        // Too bad. Now handle it here!
        if (loadTask.getException() instanceof FileNotFoundException) {
          // TODO henri Mon thread a lancé une exception FileNotFound, donc je vais notifier ici car je suis dans le main thread.
          // J'ai donc séparé ma logique business de ma présentation
          warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
        } else {
          // TODO implement other errors
          warnUser(ERRORTITLE, "TODO", "TODO");
        }
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
      // Show save as dialog
      FileChooser saveAs = new FileChooser();
      FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Fichiers CSV","*.csv");
      saveAs.getExtensionFilters().add(filter);
      saveAs.setInitialDirectory(savePath);
      saveAs.setTitle("Emplacement du fichier généré");
      File output = saveAs.showSaveDialog(progressBar.getScene().getWindow());
      progressMessage.setText("Saving to file");
      WriteTask writeTask = WriteTask.create(output, data);
      writeTask.progressProperty().addListener((observable, oldValue, newValue) -> {
        updateProgress(newValue);
      });
      writeTask.setOnSucceeded(e -> {
        updateProgress(1);
        progressMessage.setText("File saved successfully");
        // THIS CODE IS CALLED FROM MAIN THREAD
        // EVERYTHING WENT WELL
        // TODO henri ici mets que tout s'est bien déroulé et cache ta progress bar
      });
      writeTask.setOnFailed(e -> {
        Throwable exception = writeTask.getException();
        throw new IllegalStateException(exception);
        // TODO henri ici regarde ce qu'il peut se passer de mal et affiche une erreur en fonction. et cache ta progress bar
      });
      new Thread(writeTask).start();
    }
    
}