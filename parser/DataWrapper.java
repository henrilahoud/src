package parser;

import model.Emplacement;

import java.util.List;

public class DataWrapper {
    private final List<Emplacement> emplacements;
    private final int nbEmplacements;
    private final int nbConteneurs;
    // TODO delete
    // private final List<Exception> exceptionsSet;


    public DataWrapper(List<Emplacement> emplacements, int nbEmplacements, int nbConteneurs) {
        this.emplacements = emplacements;
        this.nbEmplacements = nbEmplacements;
        this.nbConteneurs = nbConteneurs;
        // TODO delete the exceptionsSet
        // this.exceptionsSet = exceptionsSet;
    }

    public int getNbConteneurs() {
        return nbConteneurs;
    }

    public int getNbEmplacements() {
        return nbEmplacements;
    }

    // TODO delete
    // public List<Exception> getExceptionsSet() {
    //    return exceptionsSet;
    //}

    public List<Emplacement> getEmplacements() {
        return emplacements;
    }
}
