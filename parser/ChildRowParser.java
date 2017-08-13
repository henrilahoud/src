package parser;

import exceptionHandler.NullValueRunTimeException;
import model.Conteneur;
import model.Emplacement;
import model.Usager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static parser.util.HeaderUtils.*;

public class ChildRowParser implements GenericParser<Emplacement,String[]> {


    private final Map<String, Integer> columns;
    private final Set<Emplacement> parentEmplacements;

    public ChildRowParser(Map<String, Integer> columns, Set<Emplacement> parentEmplacements) {
        this.columns = columns;
        this.parentEmplacements = parentEmplacements;
    }

    public boolean supports(String[] row) {
        return !row[columns.get(COLUMN_PARENTID)].trim().isEmpty();
    }

    @Override
    public Emplacement parse(String[] row) throws NullValueRunTimeException {
        // Retrouver une ligne parente a partir de son parentId, et mapper le conteneur a l'emplacement.
        String parentId = row[columns.get(COLUMN_PARENTID)];
        Emplacement e = findEmplacement(parentId);

        Conteneur c = new Conteneur();
        c.setDateDistribution(new DateParser().parse(row[columns.get(COLUMN_ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC)]));
        c.setFluxOuMatiere(row[columns.get(COLUMN_ENQUETE_CONTENEUR_FLUXMATIERE)]);
        c.setVolumeBac(row[columns.get(COLUMN_ENQUETE_CONTENEUR_VOLUMEBAC)]);
        c.setSerrure(row[columns.get(COLUMN_ENQUETE_CONTENEUR_SERRURE)]);
        c.setFournisseur(row[columns.get(COLUMN_ENQUETE_CONTENEUR_FOURNISSEUR)]);
        c.setNumeroPuce(row[columns.get(COLUMN_ENQUETE_CONTENEUR_NUMPUCE)]);
        c.setNumeroCuve(row[columns.get(COLUMN_ENQUETE_CONTENEUR_NUMCUVE)]);
        c.setNumeroCab(row[columns.get(COLUMN_ENQUETE_CONTENEUR_NUMCAB)]);
        e.getConteneurs().add(c);

        return e;
    }


    private Emplacement findEmplacement(String id) {
        for (Emplacement e : parentEmplacements) {
            if (id.equals(e.getIdentifier())) {
                return e;
            }
        }
        return null;
    }
}
