package ui;

import javafx.scene.control.Alert;
import parser.util.Statistics;

public abstract class UiUtils {
    public static final String ERRORTITLE = "Erreur";
    public static final String SAVEFILETITLE = "Sauvegarde du fichier généré";
    public static final String JOBDONETITLE = "Sauvegarde réussie";
    public static final String WRONGTYPEHEADER = "Le fichier choisi n'est pas valable";
    public static final String WRONGTYPECONTENT = "Types de fichiers autorisés :\n- Pour Daxium : fichier .xlsx\n- Pour Fantoir : fichier .txt\n- Pour BAN : fichier .csv\n\nMerci de sélectionner le bon type de fichier.";
    public static final String NODATAHEADER = "Le fichier choisi ne semble pas correspondre";
    public static final String NODATACONTENT = "Les intitulés de colonnes ne sont pas (tous) identifiés.\nVeuillez vous assurer du bon format du fichier choisi.";
    public static final String SAVEFILEHEADER = "Veuillez choisir l'emplacement du fichier généré";
    public static final String SAVEFILECONTENT = "";
    public static final String JOBDONEHEADER = "Le fichier a été sauvegardé avec succès";
    // TODO delete
    // public static String JOBDONECONTENT = "Nombre d'Emplacements : " + Integer.toString(Statistics.nbEmplacements) + "\nNombre de Conteneurs : " + Integer.toString(Statistics.nbConteneurs);


    public static void adviseUser(String title, String header, String content) {
        Alert Msg = new Alert(Alert.AlertType.INFORMATION);
        Msg.setTitle(title);
        Msg.setHeaderText(header);
        Msg.setContentText(content);
        Msg.showAndWait();
    }

    public static void warnUser(String title, String header, String content) {
        Alert Msg = new Alert(Alert.AlertType.ERROR);
        Msg.setTitle(title);
        Msg.setHeaderText(header);
        Msg.setContentText(content);
        Msg.showAndWait();
    }

    public static void adviseUserJobDone(int nbEmplacements, int nbConteneurs) {
        adviseUser(JOBDONETITLE, JOBDONEHEADER, "Nombre d'Emplacements : " + Integer.toString(nbEmplacements) + "\nNombre de Conteneurs : " + Integer.toString(nbConteneurs));
    }
}
