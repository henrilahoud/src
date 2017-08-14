package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import parser.CsvLoader;

public class EnqueteToPivotController {

    @FXML
    protected void handleLoadButtonAction(ActionEvent event) {
        CsvLoader loader = new CsvLoader();
        loader.load();
    }

    /*
    @Override
    public void initialize(URL Path, ResourceBundle Resources){
        File ImageFile = new File("/Users/Henri/IdeaProjects/parser/src/BandeauRouge.png");
        Image MyImage = new Image(ImageFile.toURI().toString());
        MyImageView = new ImageView(MyImage);
    }
    */
}
