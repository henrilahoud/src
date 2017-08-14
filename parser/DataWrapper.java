package parser;

import model.Emplacement;

import java.util.List;

public class DataWrapper {
    private final List<Emplacement> emplacements;
    private final List<Exception> exceptionsSet;


    public DataWrapper(List<Emplacement> emplacements, List<Exception> exceptionsSet) {
        this.emplacements = emplacements;
        this.exceptionsSet = exceptionsSet;
    }

    public List<Exception> getExceptionsSet() {
        return exceptionsSet;
    }

    public List<Emplacement> getEmplacements() {
        return emplacements;
    }
}
