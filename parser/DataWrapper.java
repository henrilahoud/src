package parser;

import handler.ErrorHandler;
import handler.PivotError;
import model.Emplacement;

import java.util.List;

public class DataWrapper {
    private final List<Emplacement> emplacements;
    private final int nbEmplacements;
    private final int nbConteneurs;
    private final ErrorHandler errorHandler;


    public DataWrapper(List<Emplacement> emplacements, int nbEmplacements, int nbConteneurs, ErrorHandler errorHandler) {
        this.emplacements = emplacements;
        this.nbEmplacements = nbEmplacements;
        this.nbConteneurs = nbConteneurs;
        this.errorHandler = errorHandler;
    }

    public int getNbConteneurs() {
        return nbConteneurs;
    }

    public int getNbEmplacements() {
        return nbEmplacements;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public List<Emplacement> getEmplacements() {
        return emplacements;
    }
}
