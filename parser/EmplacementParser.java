package parser;

import handler.NullValueRunTimeException;
import model.*;

import java.util.ArrayList;
import java.util.Map;

import static handler.ExceptionWrapper.*;
import static parser.util.HeaderUtils.*;
import static parser.util.DataChecker.*;
import static parser.util.StringUtils.reformatTel;

public class EmplacementParser implements GenericParser<Emplacement,ArrayList<String>> {

    private final Map<String, Integer> columns;

    public EmplacementParser(Map<String, Integer> columns) {
        this.columns = columns;
    }

    //TODO This method should disapear since a whole sheet is dedicated to Emplacements only
    /*public boolean supports(ArrayList<String> row) {
        return row[columns.get(COLUMN_PARENTID)].trim().isEmpty();
    }*/

    @Override
    public Emplacement parse(ArrayList<String> row) throws NullValueRunTimeException {
        try {
            // First fill Usager then fill emplacement
            Emplacement emp = new Emplacement();
            Usager u = new Usager();

            emp.setIdentifier(row.get(columns.get(COLUMN_IDENTIFIER)));

            u.setCodeUsager(++CODEUSAGER);

            Contact c1 = new Contact();
            c1.setCivilite(row.get(columns.get(COLUMN_ENQUETE_CIVILITECONTACT1)));
            c1.setNomOuRaisonSociale(row.get(columns.get(COLUMN_ENQUETE_NOMCONTACT1)));
            c1.setPrenom(row.get(columns.get(COLUMN_ENQUETE_PRENOMCONTACT1)));
            c1.setTelephone1(reformatTel(row.get(columns.get(COLUMN_ENQUETE_TELEPHONE1))));
            c1.setTelephone2(reformatTel(row.get(columns.get(COLUMN_ENQUETE_TELEPHONE2))));
            c1.setEmail(row.get(columns.get(COLUMN_ENQUETE_ADRESSEEMAIL)));
            u.setContact1(c1);

            Contact c2 = new Contact();
            c2.setCivilite(row.get(columns.get(COLUMN_ENQUETE_CIVILITECONTACT2)));
            c2.setNomOuRaisonSociale(row.get(columns.get(COLUMN_ENQUETE_NOMCONTACT2)));
            c2.setPrenom(row.get(columns.get(COLUMN_ENQUETE_PRENOMCONTACT2)));
            u.setContact2(c2);

            u.setNombreHabitants(row.get(columns.get(COLUMN_ENQUETE_NOMBREHABITANTS)));
            u.setStatutHabitat(row.get(columns.get(COLUMN_ENQUETE_PROPRIETAIRELOCATAIRE)));
            u.setCommentaires(row.get(columns.get(COLUMN_ENQUETE_COMMENTAIRES)));
            u.setRaisonSociale(row.get(columns.get(COLUMN_ENQUETE_RAISONSOCIALE)));
            u.setSiret(row.get(columns.get(COLUMN_ENQUETE_SIRET)));

            Carte carte = new Carte();
            carte.setType(row.get(columns.get(COLUMN_ENQUETE_TYPECARTE)));
            carte.setNumero(row.get(columns.get(COLUMN_ENQUETE_NUMCARTE)));

            carte.setDateAttribution(new DateParser().parse(row.get(columns.get(COLUMN_ENQUETE_DATEATTRIBUTIONCARTE))));
            carte.setMotifDistribution(row.get(columns.get(COLUMN_ENQUETE_MOTIFDISTRIBUTIONCARTE)));
            u.setCarte(carte);

            u.setNomOuRaisonSocialeFacturation(row.get(columns.get(COLUMN_ENQUETE_NOMCOMPLETRAISONSOCIALEFACTURATION))) ;
            AdresseGenerique f = new AdresseGenerique();
            f.setNumAptOuEtage(row.get(columns.get(COLUMN_ENQUETE_NUMAPPETAGEFACTURATION)));
            f.setEntreeBatImmeuble(row.get(columns.get(COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLEFACTURATION)));
            f.setNumEtLabelVoie(row.get(columns.get(COLUMN_ENQUETE_NUMEROLIBELLEVOIFACTURATION)));
            f.setComplementAdresse(row.get(columns.get(COLUMN_ENQUETE_COMPLEMENTADRESSEFACTURATION)));
            f.setCp(row.get(columns.get(COLUMN_ENQUETE_CPFRANCEFACTURATION)) + row.get(columns.get(COLUMN_ENQUETE_CPHORSFRANCEFACTURATION))); // only one of the 2 will be filled
            f.setVille(row.get(columns.get(COLUMN_ENQUETE_VILLEFRANCEFACTURATION)) + row.get(columns.get(COLUMN_ENQUETE_VILLEHORSFRANCEFACTURATION))); // only one of the 2 will be filled
            f.setPays(row.get(columns.get(COLUMN_ENQUETE_PAYSHORSFRANCEFACTURATION)));
            u.setAdresseFacturation(f);

            Reglement r = new Reglement();
            r.setCategorieTiers(row.get(columns.get(COLUMN_ENQUETE_CATEGORIETIERS)));
            r.setNatureJuridique(row.get(columns.get(COLUMN_ENQUETE_NATUREJURIDIQUETIERS)));
            u.setReglement(r);

            Teomi t = new Teomi();
            t.setNumInvariant(row.get(columns.get(COLUMN_ENQUETE_NUMINVARIANTFISCAL)));
            t.setAnneeConstruction(row.get(columns.get(COLUMN_ENQUETE_ANNEECONSTRUCTION)));
            t.setCodeRivoli(row.get(columns.get(COLUMN_ENQUETE_CODERIVOLI)));
            t.setNumParcelle(row.get(columns.get(COLUMN_ENQUETE_NUMEROPARCELLE)));
            emp.setTeomi(t);

            // Now fill Emplacement
            emp.setActivitePro(row.get(columns.get(COLUMN_ENQUETE_ACTIVITEPROFESSIONNELLE)));
            emp.setDateEmmenagement(new DateParser().parse(row.get(columns.get(COLUMN_ENQUETE_DATEEMMENAGEMENT))));

            AdresseEmplacement adresse = new AdresseEmplacement();
            adresse.setNumero(row.get(columns.get(COLUMN_ENQUETE_NUMVOIE))+ " " + row.get(columns.get(COLUMN_ENQUETE_EXTENSION)));
            adresse.setType(row.get(columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENTTYPE)));
            adresse.setArticle(row.get(columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENTARTICLE)));
            adresse.setNom(row.get(columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENTNOM)));
            adresse.setCp(row.get(columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENTCP)));
            adresse.setVille(row.get(columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENTVILLE)));
            adresse.setResidencePrincipaleSecondaire(row.get(columns.get(COLUMN_ENQUETE_RESIDENCEPRINCIPALESECONDAIRE)));
            adresse.setFacturation(row.get(columns.get(COLUMN_ENQUETE_FACTURATIONAUTREADRESSE))); //TODO inverser fonctionnement // Facturer a l'adresse de l'emplacement ?
            adresse.setNumAppEtage(row.get(columns.get(COLUMN_ENQUETE_NUMAPPETAGEEMPLACEMENT)));
            adresse.setEntreeBatImmeuble(row.get(columns.get(COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLEEMPLACEMENT))) ;
            emp.setAdresseEmplacement(adresse);

            emp.setUsager(u);

            Proprietaire p = new Proprietaire();
            p.setCodeProprietaire(""); // No column ?
            p.setStatut(row.get(columns.get(COLUMN_ENQUETE_STATUTPOUB)));

            Contact cp = new Contact();
            cp.setCivilite(row.get(columns.get(COLUMN_ENQUETE_CIVILITEPOUB)));
            cp.setNomOuRaisonSociale(row.get(columns.get(COLUMN_ENQUETE_NOMRAISONSOCIALEPOUB)));
            cp.setPrenom(row.get(columns.get(COLUMN_ENQUETE_PRENOMPOUB)));
            cp.setTelephone1(reformatTel(row.get(columns.get(COLUMN_ENQUETE_TELEPHONE1POUB))));
            cp.setTelephone2(reformatTel(row.get(columns.get(COLUMN_ENQUETE_TELEPHONE2POUB))));
            cp.setEmail(row.get(columns.get(COLUMN_ENQUETE_ADRESSEEMAILPOUB)));
            p.setContact(cp);

            AdresseGenerique ap = new AdresseGenerique();
            ap.setNumAptOuEtage(row.get(columns.get(COLUMN_ENQUETE_NUMAPPETAGEPOUB)));
            ap.setEntreeBatImmeuble(row.get(columns.get(COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB)));
            ap.setNumEtLabelVoie(row.get(columns.get(COLUMN_ENQUETE_NUMEROLIBELLEVOIEPOUB)));
            ap.setComplementAdresse(row.get(columns.get(COLUMN_ENQUETE_COMPLEMENTADRESSEPOUB)));
            ap.setCp(row.get(columns.get(COLUMN_ENQUETE_CPFRANCEPOUB)) + row.get(columns.get(COLUMN_ENQUETE_CPHORSFRANCEPOUB))); // only one filled
            ap.setVille(row.get(columns.get(COLUMN_ENQUETE_VILLEFRANCEPOUB)) + row.get(columns.get(COLUMN_ENQUETE_VILLEHORSFRANCEPOUB))); // only one filled
            ap.setPays(row.get(columns.get(COLUMN_ENQUETE_PAYSHORSFRANCEPOUB)));
            p.setAdresse(ap);

            emp.setProprietaire(p);

            return emp;
        }
        catch (Exception e) {
            // TODO delete
            // exceptions.add(e);
            throw new NullValueRunTimeException();
        }
    }

    private void checkForErrors(Emplacement e) {

    }
}
