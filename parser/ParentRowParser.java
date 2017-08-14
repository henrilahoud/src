package parser;

import handler.NullValueRunTimeException;
import model.*;

import java.util.Map;

import static handler.exceptionWrapper.errors;
import static parser.util.HeaderUtils.*;

public class ParentRowParser implements GenericParser<Emplacement,String[]> {

    private final Map<String, Integer> columns;

    public ParentRowParser(Map<String, Integer> columns) {
        this.columns = columns;
    }

    public boolean supports(String[] row) {
        return row[columns.get(COLUMN_PARENTID)].trim().isEmpty();
    }

    @Override
    public Emplacement parse(String[] row) throws NullValueRunTimeException {
        try {
            // First fill Usager then fill emplacement
            Emplacement emp = new Emplacement();
            Usager u = new Usager();

            emp.setIdentifier(row[columns.get(COLUMN_IDENTIFIER)]);

            u.setCodeUsager(Integer.toString(CODEUSAGER));
            CODEUSAGER ++;

            Contact c1 = new Contact();
            c1.setCivilite(row[columns.get(COLUMN_ENQUETE_CIVILITECONTACT1)]);
            c1.setNomOuRaisonSociale(row[columns.get(COLUMN_ENQUETE_NOMCONTACT1)]);
            c1.setPrenom(row[columns.get(COLUMN_ENQUETE_PRENOMCONTACT1)]);
            c1.setTelephone1(row[columns.get(COLUMN_ENQUETE_TELEPHONE1)]);
            c1.setTelephone2(row[columns.get(COLUMN_ENQUETE_TELEPHONE2)]);
            c1.setEmail(row[columns.get(COLUMN_ENQUETE_ADRESSEEMAIL)]);
            u.setContact1(c1);

            Contact c2 = new Contact();
            c2.setCivilite(row[columns.get(COLUMN_ENQUETE_CIVILITECONTACT2)]);
            c2.setNomOuRaisonSociale(row[columns.get(COLUMN_ENQUETE_NOMCONTACT2)]);
            c2.setPrenom(row[columns.get(COLUMN_ENQUETE_PRENOMCONTACT2)]);
            u.setContact2(c2);

            u.setNombreHabitants(row[columns.get(COLUMN_ENQUETE_NOMBREHABITANTS)]);
            u.setStatutHabitat(row[columns.get(COLUMN_ENQUETE_PROPRIETAIRELOCATAIRE)]);
            u.setCommentaires(row[columns.get(COLUMN_ENQUETE_COMMENTAIRES)]);
            u.setRaisonSociale(row[columns.get(COLUMN_ENQUETE_RAISONSOCIALE)]);
            u.setSiret(row[columns.get(COLUMN_ENQUETE_SIRET)]);

            Carte carte = new Carte();
            carte.setType(row[columns.get(COLUMN_ENQUETE_TYPECARTE)]);
            carte.setNumero(row[columns.get(COLUMN_ENQUETE_NUMCARTE)]);

            carte.setDateAttribution(new DateParser().parse(row[columns.get(COLUMN_ENQUETE_DATEATTRIBUTIONCARTE)]));
            carte.setMotifDistribution(row[columns.get(COLUMN_ENQUETE_MOTIFDISTRIBUTIONCARTE)]);
            u.setCarte(carte);

            u.setNomOuRaisonSocialeFacturation(row[columns.get(COLUMN_ENQUETE_NOMCOMPLETRAISONSOCIALE)]) ;
            AdresseGenerique f = new AdresseGenerique();
            f.setNumAptOuEtage(row[columns.get(COLUMN_ENQUETE_NUMAPPETAGE)]);
            f.setEntreeBatImmeuble(row[columns.get(COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLE)]);
            f.setNumEtLabelVoie(row[columns.get(COLUMN_ENQUETE_NUMEROLIBELLEVOIE)]);
            f.setComplementAdresse(row[columns.get(COLUMN_ENQUETE_COMPLEMENTADRESSE)]);
            f.setCp(row[columns.get(COLUMN_ENQUETE_CPVILLEFRANCEFACTURATION1)] + row[columns.get(COLUMN_ENQUETE_CPHORSFRANCEFACTURATION)]); // only one of the 2 will be filled
            f.setVille(row[columns.get(COLUMN_ENQUETE_CPVILLEFRANCEFACTURATION2)] + row[columns.get(COLUMN_ENQUETE_VILLEHORSFRANCEFACTURATION)]); // only one of the 2 will be filled
            f.setPays(row[columns.get(COLUMN_ENQUETE_PAYSHORSFRANCEFACTURATION)]);
            u.setAdresseFacturation(f);

            Reglement r = new Reglement();
            r.setCategorieTiers(row[columns.get(COLUMN_ENQUETE_CATEGORIETIERS)]);
            r.setNatureJuridique(row[columns.get(COLUMN_ENQUETE_NATUREJURIDIQUETIERS)]);

            Teomi t = new Teomi();
            t.setNumInvariant(row[columns.get(COLUMN_ENQUETE_NUMINVARIANTFISCAL)]);
            t.setAnneeConstruction(row[columns.get(COLUMN_ENQUETE_ANNEECONSTRUCTION)]);
            t.setCodeRivoli(row[columns.get(COLUMN_ENQUETE_CODERIVOLI)]);
            t.setNumParcelle(row[columns.get(COLUMN_ENQUETE_NUMEROPARCELLE)]);
            u.setTeomi(t);

            // Now fill Emplacement
            emp.setActivitePro(row[columns.get(COLUMN_ENQUETE_ACTIVITEPROFESSIONNELLE)]);
            emp.setDateEmmenagement(new DateParser().parse(row[columns.get(COLUMN_ENQUETE_DATEEMMENAGEMENT)]));

            AdresseEmplacement adresse = new AdresseEmplacement();
            adresse.setNumero(row[columns.get(COLUMN_ENQUETE_NUMVOIEEXTENSION)]);
            adresse.setType(row[columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENT3)]);
            adresse.setArticle(row[columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENT4)]);
            adresse.setNom(row[columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENT5)]);
            adresse.setCp(row[columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENT2)]);
            adresse.setVille(row[columns.get(COLUMN_ENQUETE_ADRESSEEMPLACEMENT1)]);
            adresse.setFacturation(row[columns.get(COLUMN_ENQUETE_FACTURATIONADRESSE)]); // Facturer a l'adresse de l'emplacement ?
            emp.setAdresseEmplacement(adresse);

            emp.setUsager(u);

            Proprietaire p = new Proprietaire();
            p.setCodeProprietaire(""); // No column ?
            p.setStatut(row[columns.get(COLUMN_ENQUETE_STATUTPOUB)]);

            Contact cp = new Contact();
            cp.setCivilite(row[columns.get(COLUMN_ENQUETE_CIVILITEPOUB)]);
            cp.setNomOuRaisonSociale(row[columns.get(COLUMN_ENQUETE_NOMRAISONSOCIALEPOUB)]);
            cp.setPrenom(row[columns.get(COLUMN_ENQUETE_PRENOMPOUB)]);
            cp.setTelephone1(row[columns.get(COLUMN_ENQUETE_TELEPHONE1POUB)]);
            cp.setTelephone2(row[columns.get(COLUMN_ENQUETE_TELEPHONE2POUB)]);
            cp.setEmail(row[columns.get(COLUMN_ENQUETE_ADRESSEEMAILPOUB)]);
            p.setContact(cp);

            AdresseGenerique ap = new AdresseGenerique();
            ap.setNumAptOuEtage(row[columns.get(COLUMN_ENQUETE_NUMAPPETAGEPOUB)]);
            ap.setEntreeBatImmeuble(row[columns.get(COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB)]);
            ap.setNumEtLabelVoie(row[columns.get(COLUMN_ENQUETE_NUMEROLIBELLEVOIEPOUB)]);
            ap.setComplementAdresse(row[columns.get(COLUMN_ENQUETE_COMPLEMENTADRESSEPOUB)]);
            ap.setCp(row[columns.get(COLUMN_ENQUETE_CPVILLEFRANCEPOUB1)] + row[columns.get(COLUMN_ENQUETE_CPHORSFRANCEPOUB)]); // only one filled
            ap.setVille(row[columns.get(COLUMN_ENQUETE_CPVILLEFRANCEPOUB2)] + row[columns.get(COLUMN_ENQUETE_VILLEHORSFRANCEPOUB)]); // only one filled
            ap.setPays(row[columns.get(COLUMN_ENQUETE_PAYSHORSFRANCEPOUB)]);
            p.setAdresse(ap);

            emp.setProprietaire(p);

            return emp;
        }
        catch (Exception e) {
            errors.add(e);
            throw new NullValueRunTimeException(e);
        }
    }
}
