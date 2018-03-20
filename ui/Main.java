package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.Normalizer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainWindow.fxml"));

        Image Icon = new Image(getClass().getResourceAsStream("/Images/Veolia.png"));

        if (Icon != null) {
            primaryStage.getIcons().add(Icon);
        }

        primaryStage.setTitle("Générateur de fichier pivot");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
