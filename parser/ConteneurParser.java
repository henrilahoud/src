package parser;

import handler.NullValueRunTimeException;
import model.Conteneur;
import model.Emplacement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static parser.util.HeaderUtils.*;

public class ConteneurParser implements GenericParser<Conteneur,ArrayList<String>> {


    private final Map<String, Integer> headers;


    /*public ConteneurParser(Map<String, Integer> columns, Set<Emplacement> parentEmplacements) {
        this.columns = columns;
    }*/

    public ConteneurParser(Map<String, Integer> conteneurHeaders) {
        this.headers = conteneurHeaders;
    }

    //TODO should disappear
    /*public boolean supports(String.get() row) {
        return !row.get(columns.get(COLUMN_PARENTID)).trim().isEmpty();
    }*/

    @Override
    public Conteneur parse(ArrayList<String> row) throws NullValueRunTimeException {
        Conteneur c = new Conteneur();
        c.setIdentifier(row.get(headers.get(COLUMN_IDENTIFIER)));
        //TODO c.setDateDistribution(new DateParser().parse(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC))));
        c.setFluxOuMatiere(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_FLUXMATIERE)));
        c.setVolumeBac(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_VOLUMEBAC)));
        c.setSerrure(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_SERRURE)));
        c.setFournisseur(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_FOURNISSEUR)));
        c.setNumeroPuce(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_NUMPUCE)));
        c.setNumeroCuve(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_NUMCUVE)));
        c.setNumeroCab(row.get(headers.get(COLUMN_ENQUETE_CONTENEUR_NUMCAB)));

        return c;
    }

//TODO delete
    /*private Emplacement findEmplacement(String id) {
        for (Emplacement e : parentEmplacements) {
            if (id.equals(e.getIdentifier())) {
                return e;
            }
        }
        return null;
    }*/
}
