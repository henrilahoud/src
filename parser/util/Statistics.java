package parser.util;

public abstract class Statistics {
    public static int nbEmplacements;
    public static int nbEmplacementsNotAdded;
    public static int nbConteneurs;
    public static int nbUsagers;

    public static void resetStats() {
        nbEmplacements = 0;
        nbEmplacementsNotAdded = 0;
        nbConteneurs = 0;
        nbUsagers = 0;
    }
}
