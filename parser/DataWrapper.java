package parser;

import model.Emplacement;
import model.Usager;

import java.util.List;

public class DataWrapper {
    private final List<Emplacement> emplacements;


    public DataWrapper(List<Emplacement> emplacements) {
        this.emplacements = emplacements;
    }

    public List<Emplacement> getEmplacements() {
        return emplacements;
    }
}
