package parser;

import javafx.stage.*;
import model.*;
import handler.*;
import parser.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static parser.util.HeaderUtils.*;
import static parser.util.StringUtils.*;
import static ui.UiUtils.*;
import static handler.exceptionWrapper.*;

public class CsvWriter {
    private DataWrapper wrapper;
    private List<String> newTable;

    public CsvWriter(DataWrapper wrapper) throws NullValueRunTimeException {
        this.wrapper = wrapper;
    }

    public void write() {
        try {
            newTable = new ArrayList<>();

            Collections.sort(wrapper.getEmplacements(), (Emplacement e1, Emplacement e2) -> e1.getUsager().getCodeUsager() - e2.getUsager().getCodeUsager());

            for (Emplacement e : wrapper.getEmplacements()) {
                prepareRows(e);
            }
            generateCsv();
        }
        catch (IOException e) {
            exceptions.add(e);
        }
    }

    private void prepareRows(Emplacement e) {
        String row[] = new String[PIVOTCOLUMNS_NB];
        fillEmplInformation(e, row);
        fillContInformation(new Conteneur(), row);

        if (!(e.getConteneurs() == null || (e.getConteneurs().isEmpty()))) {

            // For each conteneur, create a row filling the main information of e, and the information of the conteneur, and add it to newTable
            for (Conteneur c : e.getConteneurs()) {
                fillContInformation(c, row);
                newTable.add(joinRow(row));
            }
        } else {
            newTable.add(joinRow(row));
        }
    }

    private void fillEmplInformation(Emplacement e, String r[]) {
        try {
            //Informations Usagers
            r[USAGER_CODEUSAGER] = reformat(e.getUsager().getCodeUsager());
            r[USAGER_CODEREPRISE] = StringUtils.DEFAULTVALUE;
            r[ENQUETE_ACTIVITEPROFESSIONNELLE] = reformat(e.getActivitePro());
            r[ENQUETE_CIVILITECONTACT1] = reformat(e.getUsager().getContact1().getCivilite());
            r[ENQUETE_NOMCONTACT1] = reformat(e.getUsager().getContact1().getNomOuRaisonSocialeFormatted());
            r[ENQUETE_PRENOMCONTACT1] = reformat(e.getUsager().getContact1().getPrenomFormatted());
            r[ENQUETE_CIVILITECONTACT2] = reformat(e.getUsager().getContact2().getCivilite());
            r[ENQUETE_NOMCONTACT2] = reformat(e.getUsager().getContact2().getNomOuRaisonSocialeFormatted());
            r[ENQUETE_PRENOMCONTACT2] = reformat(e.getUsager().getContact2().getPrenomFormatted());
            r[ENQUETE_RAISONSOCIALE] = reformat(e.getUsager().getRaisonSociale());
            r[ENQUETE_TELEPHONE1] = reformat(e.getUsager().getContact1().getTelephone1());
            r[ENQUETE_TELEPHONE2] = reformat(e.getUsager().getContact1().getTelephone2());
            r[ENQUETE_ADRESSEEMAIL] = reformat(e.getUsager().getContact1().getEmail());
            r[ENQUETE_NOMBREHABITANTS] = reformat(e.getUsager().getNombreHabitants());
            r[ENQUETE_PROPRIETAIRELOCATAIRE] = reformat(e.getUsager().getStatutHabitat());
            r[ENQUETE_COMMENTAIRES] = reformat(e.getUsager().getCommentaires());
            r[ENQUETE_SIRET] = reformat(e.getUsager().getSiret());

            //Informations Emplacement
            r[ENQUETE_DATEEMMENAGEMENT] = reformat(e.getDateEmmenagement());
            r[ENQUETE_NUMVOIEEXTENSION] = reformat(e.getAdresseEmplacement().getNumero());
            r[ENQUETE_ADRESSEEMPLACEMENTTYPE] = reformat(e.getAdresseEmplacement().getType());
            r[ENQUETE_ADRESSEEMPLACEMENTARTICLE] = reformat(e.getAdresseEmplacement().getArticle());
            r[ENQUETE_ADRESSEEMPLACEMENTNOM] = reformat(e.getAdresseEmplacement().getNom());
            r[ENQUETE_ADRESSEEMPLACEMENTCP] = reformat(e.getAdresseEmplacement().getCp());
            r[ENQUETE_ADRESSEEMPLACEMENTVILLE] = reformat(e.getAdresseEmplacement().getVille());
            r[ENQUETE_RESIDENCEPRINCIPALESECONDAIRE] = reformat(e.getAdresseEmplacement().getResidencePrincipaleSecondaire());

            //Informations Cartes
            r[ENQUETE_TYPECARTE] = reformat(e.getUsager().getCarte().getType());
            r[ENQUETE_NUMCARTE] = reformat(e.getUsager().getCarte().getNumero());
            r[ENQUETE_DATEATTRIBUTIONCARTE] = reformat(e.getUsager().getCarte().getDateAttribution());
            r[ENQUETE_MOTIFDISTRIBUTIONCARTE] = reformat(e.getUsager().getCarte().getMotifDistribution());

            //Informations Facturation
            r[ENQUETE_FACTURATIONADRESSE] = reformat(e.getAdresseEmplacement().getFacturation());
            r[ENQUETE_NOMCOMPLETRAISONSOCIALE] = reformat(e.getUsager().getNomOuRaisonSocialeFacturation());
            r[ENQUETE_NUMAPPETAGE] = reformat(e.getUsager().getAdresseFacturation().getNumAptOuEtage());
            r[ENQUETE_ENTREEBATIMENTIMMEUBLE] = reformat(e.getUsager().getAdresseFacturation().getEntreeBatImmeuble());
            r[ENQUETE_NUMEROLIBELLEVOIE] = reformat(e.getUsager().getAdresseFacturation().getNumEtLabelVoie());
            r[ENQUETE_COMPLEMENTADRESSE] = reformat(e.getUsager().getAdresseFacturation().getComplementAdresse());
            r[ENQUETE_CPVILLEFRANCEFACTURATION1] = reformat(e.getUsager().getAdresseFacturation().getCp());
            r[ENQUETE_CPVILLEFRANCEFACTURATION2] = reformat(e.getUsager().getAdresseFacturation().getVille());
            r[ENQUETE_PAYSHORSFRANCEFACTURATION] = reformat(e.getUsager().getAdresseFacturation().getPays());
            r[ENQUETE_CATEGORIETIERS] = reformat(e.getUsager().getReglement().getCategorieTiers());
            r[ENQUETE_NATUREJURIDIQUETIERS] = reformat(e.getUsager().getReglement().getNatureJuridique());

            //Informations Proprietaire ou Bailleur
            r[USAGER_CODEPROPRIETAIRE] = reformat(e.getProprietaire().getCodeProprietaire());
            r[ENQUETE_STATUTPOUB] = reformat(e.getProprietaire().getStatut());
            r[ENQUETE_CIVILITEPOUB] = reformat(e.getProprietaire().getContact().getCivilite());
            r[ENQUETE_NOMRAISONSOCIALEPOUB] = reformat(e.getProprietaire().getContact().getNomOuRaisonSociale());
            r[ENQUETE_PRENOMPOUB] = reformat(e.getProprietaire().getContact().getPrenom());
            r[ENQUETE_NUMAPPETAGEPOUB] = reformat(e.getProprietaire().getAdresse().getNumAptOuEtage());
            r[ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB] = reformat(e.getProprietaire().getAdresse().getEntreeBatImmeuble());
            r[ENQUETE_NUMEROLIBELLEVOIEPOUB] = reformat(e.getProprietaire().getAdresse().getNumEtLabelVoie());
            r[ENQUETE_COMPLEMENTADRESSEPOUB] = reformat(e.getProprietaire().getAdresse().getComplementAdresse());
            r[ENQUETE_CPVILLEFRANCEPOUB1] = reformat(e.getProprietaire().getAdresse().getCp());
            r[ENQUETE_CPVILLEFRANCEPOUB2] = reformat(e.getProprietaire().getAdresse().getVille());
            r[ENQUETE_PAYSHORSFRANCEPOUB] = reformat(e.getProprietaire().getAdresse().getPays());
            r[ENQUETE_TELEPHONE1POUB] = reformat(e.getProprietaire().getContact().getTelephone1());
            r[ENQUETE_TELEPHONE2POUB] = reformat(e.getProprietaire().getContact().getTelephone2());
            r[ENQUETE_ADRESSEEMAILPOUB] = reformat(e.getProprietaire().getContact().getEmail());

            //Informations TEOMI
            r[ENQUETE_NUMINVARIANTFISCAL] = reformat(e.getTeomi().getNumInvariant());
            r[ENQUETE_ANNEECONSTRUCTION] = reformat(e.getTeomi().getAnneeConstruction());
            r[ENQUETE_CODERIVOLI] = reformat(e.getTeomi().getCodeRivoli());
            r[ENQUETE_NUMEROPARCELLE] = reformat(e.getTeomi().getNumParcelle());

            //Informations SEPA
            r[USAGER_MODEREGLEMENT] = StringUtils.DEFAULTVALUE;
            r[USAGER_DATECREATION] = StringUtils.DEFAULTVALUE;
            r[USAGER_RUM] = StringUtils.DEFAULTVALUE;
            r[USAGER_DATEMANDAT] = StringUtils.DEFAULTVALUE;
            r[USAGER_IBAN] = StringUtils.DEFAULTVALUE;
            r[USAGER_TITULAIRE] = StringUtils.DEFAULTVALUE;

        }
        catch (Exception ex) {
            System.out.println("fillEmplInformation");
        }
    }

    private void fillContInformation(Conteneur c, String r[]) {
        try {
            r[ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC] = reformat(c.getDateDistribution());
            r[ENQUETE_CONTENEUR_FLUXMATIERE] = reformat(c.getFluxOuMatiere());
            r[ENQUETE_CONTENEUR_VOLUMEBAC] = reformat(c.getVolumeBac());
            r[ENQUETE_CONTENEUR_SERRURE] = reformat(c.getSerrure());
            r[ENQUETE_CONTENEUR_FOURNISSEUR] = reformat(c.getFournisseur());
            r[ENQUETE_CONTENEUR_NUMPUCE] = reformat(c.getNumeroPuce());
            r[ENQUETE_CONTENEUR_NUMCUVE] = reformat(c.getNumeroCuve());
            r[ENQUETE_CONTENEUR_NUMCAB] = reformat(c.getNumeroCab());
        }
        catch (Exception ex) {
            System.out.println("fillContInformation");
        }
    }

    public void generateCsv() throws IOException {
        adviseUser(SAVEFILETITLE, SAVEFILEHEADER, SAVEFILECONTENT);

        FileChooser saveAs = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Fichiers CSV","*.csv");
        saveAs.getExtensionFilters().add(filter);
        saveAs.setInitialDirectory(savePath);
        saveAs.setTitle("Emplacement du fichier généré");

        File NewCsv = saveAs.showSaveDialog(null);

        if (NewCsv != null){
            FileWriter Writer = new FileWriter(new File(NewCsv.toString()));

            // Append titles
            Writer.append(MAINTITLES);
            Writer.append(SUBTITLES);

            for (String row : newTable){
                Writer.append(row);
            }
            Writer.flush();
            Writer.close();

            adviseUser(JOBDONETITLE, JOBDONEHEADER, JOBDONECONTENT);
        }
    }
}
