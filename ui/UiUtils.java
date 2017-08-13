package ui;

import javafx.scene.control.Alert;

public abstract class UiUtils {
    public static final String ERRORTITLE = "Erreur";
    public static final String INFORMATIONTITLE = "Attention";
    public static final String WRONGTYPEHEADER = "Le fichier choisi n'est pas valable";
    public static final String WRONGTYPECONTENT = "Seuls les fichiers CSV sont acceptés par cette application.\nVeuillez choisir un fichier CSV.";
    public static final String NODATAHEADER = "Le fichier choisi ne semble pas correspondre";
    public static final String NODATACONTENT = "Les intitulés de colonnes ne sont pas identifiés.\nVeuillez vous assurer du bon format du fichier choisi.";
    public static final String INFORMATIONHEADER = "Veuillez sauvegarder le fichier généré";
    public static final String INFORMATIONCONTENT = "";


    public static void adviseUser(String title, String header, String content) {
        Alert Msg = new Alert(Alert.AlertType.INFORMATION);
        Msg.setTitle(title);
        Msg.setHeaderText(header);
        Msg.setContentText(content);
        Msg.show();
    }

    public static void warnUser(String title, String header, String content) {
        Alert Msg = new Alert(Alert.AlertType.ERROR);
        Msg.setTitle(title);
        Msg.setHeaderText(header);
        Msg.setContentText(content);
        Msg.show();
    }
}
