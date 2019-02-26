package parser;

import handler.ErrorHandler;
import handler.PivotError;
import model.Conteneur;
import model.Emplacement;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import parser.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static handler.ExceptionWrapper.exceptions;
import static handler.PivotError.NBOFERRORTYPES;
import static parser.util.HeaderUtils.*;
import static parser.util.StringUtils.joinRow;
import static parser.util.StringUtils.reformat;
import static parser.util.StringUtils.reformatLastColumn;

public class CsvWriter {
    private DataWrapper wrapper;
    private List<Emplacement> emplacements;
    private List<String> newTable;
    private Consumer<Integer> onProgressUpdateListener;
    private File output;
    private XSSFWorkbook workBook;
    private XSSFSheet excelSheet;
    private int rowIndexXls = 2;


    public CsvWriter(File output, DataWrapper wrapper, Consumer<Integer> onProgressUpdateListener) {
        this.wrapper = wrapper;
        this.emplacements = this.wrapper.getEmplacements();
        this.onProgressUpdateListener = onProgressUpdateListener;
        this.output = output;
        this.workBook = new XSSFWorkbook();
        this.excelSheet = workBook.createSheet();
    }
    
    private void updateProgress(Integer progress) {
        if (onProgressUpdateListener != null) {
            onProgressUpdateListener.accept(progress);
        }
    }

    public void write() {
        try {
            System.out.println("Entering CsvWriter write method");

            newTable = new ArrayList<>();
            updateProgress(0);
            Collections.sort(emplacements, Comparator.comparingInt(e2 -> e2.getUsager().getCodeUsager()));
            updateProgress(20);
            for (Emplacement e : emplacements) {
                prepareRows(e);
            }
            updateProgress(60);
            generateCsv();
        }
        catch (IOException e) {
            exceptions.add(e);
        }
    }

    private void prepareRows(Emplacement e) {
        System.out.println("Entering CsvWriter prepareRows method");

        String row[] = new String[PIVOTCOLUMNS_NB + 1 + NBOFERRORTYPES]; // +1 is only for Pays de Valois, NBOFERRORTYPES is to write errors at end of file
        fillEmplInformation(e, row);
        fillContInformation(new Conteneur(), row, true); //Fill a first empty row in case Emplacement has no Conteneur

        if (!(e.getConteneurs() == null || (e.getConteneurs().isEmpty()))) {

            // For each conteneur, create a row filling the main information of e, and the information of the conteneur, and add it to newTable
            for (Conteneur c : e.getConteneurs()) {
                fillContInformation(c, row, e.isValid()); //isValid was created for Pays de Valois

                //Add columns at the end for errors, errors of Emplacement must appear on each Conteneur row
                fillErrorInformation(wrapper, e, row);

                // TODO addRowToXls(row);
                newTable.add(joinRow(row));
            }
        } else {
            // TODO addRowToXls(row);
            newTable.add(joinRow(row));
        }
    }

    private void fillErrorInformation(DataWrapper w, Emplacement e, String r[]){
        System.out.println("Entering CsvWriter fillErrorInformation method");

        ErrorHandler handler = w.getErrorHandler();
        int index = PIVOTCOLUMNS_NB + 1;

        //Get rid of "null" values in the output
        for (int i = 0; i < NBOFERRORTYPES; i++) {
            r[index + i] = "";
        }

        for (PivotError error : handler.getPivotErrors().get(e.getUsager().getCodeUsager())) {
            int errorIndex = index + error.getType().ordinal();
            r[errorIndex] = reformat(error.getContent(), false);
        }
    }

    private void fillEmplInformation(Emplacement e, String r[]) {
        try {
            System.out.println("Entering CsvWriter fillEmplInformation method");

            //Informations Usagers
            r[USAGER_CODEUSAGER] = reformat(e.getUsager().getCodeUsager());
            r[USAGER_CODEREPRISE] = StringUtils.DEFAULTVALUE;
            r[ENQUETE_ACTIVITEPROFESSIONNELLE] = reformat(e.getActivitePro(), false);
            r[ENQUETE_CIVILITECONTACT1] = reformat(e.getUsager().getContact1().getCivilite(), false);
            r[ENQUETE_NOMCONTACT1] = reformat(e.getUsager().getContact1().getNomOuRaisonSociale(), true);
            r[ENQUETE_PRENOMCONTACT1] = reformat(e.getUsager().getContact1().getPrenom(), true);
            r[ENQUETE_CIVILITECONTACT2] = reformat(e.getUsager().getContact2().getCivilite(), false);
            r[ENQUETE_NOMCONTACT2] = reformat(e.getUsager().getContact2().getNomOuRaisonSociale(), true);
            r[ENQUETE_PRENOMCONTACT2] = reformat(e.getUsager().getContact2().getPrenom(), true);
            r[ENQUETE_RAISONSOCIALE] = reformat(e.getUsager().getRaisonSociale(), true);
            r[ENQUETE_TELEPHONE1] = reformat(e.getUsager().getContact1().getTelephone1(), false);
            r[ENQUETE_TELEPHONE2] = reformat(e.getUsager().getContact1().getTelephone2(), false);
            r[ENQUETE_ADRESSEEMAIL] = reformat(e.getUsager().getContact1().getEmail(), false);
            r[ENQUETE_NOMBREHABITANTS] = reformat(e.getUsager().getNombreHabitants(), false);
            r[ENQUETE_PROPRIETAIRELOCATAIRE] = reformat(e.getUsager().getStatutHabitat(), false);
            r[ENQUETE_COMMENTAIRES] = reformat(e.getUsager().getCommentaires(), false);
            r[ENQUETE_SIRET] = reformat(e.getUsager().getSiret(), false);

            //Informations Emplacement
            r[ENQUETE_DATEEMMENAGEMENT] = reformat(e.getDateEmmenagement());
            r[ENQUETE_NUMVOIEEXTENSION] = reformat(e.getAdresseEmplacement().getNumero(), false);
            r[ENQUETE_ADRESSEEMPLACEMENTTYPE] = reformat(e.getAdresseEmplacement().getType(), false);
            r[ENQUETE_ADRESSEEMPLACEMENTARTICLE] = reformat(e.getAdresseEmplacement().getArticle(), false);
            r[ENQUETE_ADRESSEEMPLACEMENTNOM] = reformat(e.getAdresseEmplacement().getNom(), true);
            r[ENQUETE_ADRESSEEMPLACEMENTCP] = reformat(e.getAdresseEmplacement().getCp(), false);
            r[ENQUETE_ADRESSEEMPLACEMENTVILLE] = reformat(e.getAdresseEmplacement().getVille(), true);
            r[ENQUETE_RESIDENCEPRINCIPALESECONDAIRE] = reformat(e.getAdresseEmplacement().getResidencePrincipaleSecondaire(), false);

            //Informations Cartes
            r[ENQUETE_TYPECARTE] = reformat(e.getUsager().getCarte().getType(), false);
            r[ENQUETE_NUMCARTE] = reformat(e.getUsager().getCarte().getNumero(), false);
            r[ENQUETE_DATEATTRIBUTIONCARTE] = reformat(e.getUsager().getCarte().getDateAttribution());
            r[ENQUETE_MOTIFDISTRIBUTIONCARTE] = reformat(e.getUsager().getCarte().getMotifDistribution(), false);

            //Informations Facturation
            r[ENQUETE_FACTURATIONADRESSE] = reformat(e.getAdresseEmplacement().getFacturation(), false);
            r[ENQUETE_NOMCOMPLETRAISONSOCIALE] = reformat(e.getUsager().getNomOuRaisonSocialeFacturation(), true);
            r[ENQUETE_NUMAPPETAGEEMPLACEMENT] = reformat(e.getUsager().getAdresseFacturation().getNumAptOuEtage(), true);
            r[ENQUETE_ENTREEBATIMENTIMMEUBLE] = reformat(e.getUsager().getAdresseFacturation().getEntreeBatImmeuble(), true);
            r[ENQUETE_NUMEROLIBELLEVOIE] = reformat(e.getUsager().getAdresseFacturation().getNumEtLabelVoie(), true);
            r[ENQUETE_COMPLEMENTADRESSE] = reformat(e.getUsager().getAdresseFacturation().getComplementAdresse(), true);
            r[ENQUETE_CPVILLEFRANCEFACTURATION1] = reformat(e.getUsager().getAdresseFacturation().getCp(), true);
            r[ENQUETE_CPVILLEFRANCEFACTURATION2] = reformat(e.getUsager().getAdresseFacturation().getVille(), true);
            r[ENQUETE_PAYSHORSFRANCEFACTURATION] = reformat(e.getUsager().getAdresseFacturation().getPays(), true);
            r[ENQUETE_CATEGORIETIERS] = reformat(e.getUsager().getReglement().getCategorieTiers(), false);
            r[ENQUETE_NATUREJURIDIQUETIERS] = reformat(e.getUsager().getReglement().getNatureJuridique(), false);

            //Informations Proprietaire ou Bailleur
            r[USAGER_CODEPROPRIETAIRE] = reformat(e.getProprietaire().getCodeProprietaire(), false);
            r[ENQUETE_STATUTPOUB] = reformat(e.getProprietaire().getStatut(), false);
            r[ENQUETE_CIVILITEPOUB] = reformat(e.getProprietaire().getContact().getCivilite(), false);
            r[ENQUETE_NOMRAISONSOCIALEPOUB] = reformat(e.getProprietaire().getContact().getNomOuRaisonSociale(), true);
            r[ENQUETE_PRENOMPOUB] = reformat(e.getProprietaire().getContact().getPrenom(), true);
            r[ENQUETE_NUMAPPETAGEPOUB] = reformat(e.getProprietaire().getAdresse().getNumAptOuEtage(), true);
            r[ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB] = reformat(e.getProprietaire().getAdresse().getEntreeBatImmeuble(), true);
            r[ENQUETE_NUMEROLIBELLEVOIEPOUB] = reformat(e.getProprietaire().getAdresse().getNumEtLabelVoie(), true);
            r[ENQUETE_COMPLEMENTADRESSEPOUB] = reformat(e.getProprietaire().getAdresse().getComplementAdresse(), true);
            r[ENQUETE_CPVILLEFRANCEPOUB1] = reformat(e.getProprietaire().getAdresse().getCp(), true);
            r[ENQUETE_CPVILLEFRANCEPOUB2] = reformat(e.getProprietaire().getAdresse().getVille(), true);
            r[ENQUETE_PAYSHORSFRANCEPOUB] = reformat(e.getProprietaire().getAdresse().getPays(), true);
            r[ENQUETE_TELEPHONE1POUB] = reformat(e.getProprietaire().getContact().getTelephone1(), false);
            r[ENQUETE_TELEPHONE2POUB] = reformat(e.getProprietaire().getContact().getTelephone2(), false);
            r[ENQUETE_ADRESSEEMAILPOUB] = reformat(e.getProprietaire().getContact().getEmail(), false);

            //Informations TEOMI
            r[ENQUETE_NUMINVARIANTFISCAL] = reformat(e.getTeomi().getNumInvariant(), true);
            r[ENQUETE_ANNEECONSTRUCTION] = reformat(e.getTeomi().getAnneeConstruction(), false);
            r[ENQUETE_CODERIVOLI] = reformat(e.getTeomi().getCodeRivoli(), true);
            r[ENQUETE_NUMEROPARCELLE] = reformat(e.getTeomi().getNumParcelle(), true);

            //Informations SEPA
            r[USAGER_MODEREGLEMENT] = StringUtils.DEFAULTVALUE;
            r[USAGER_DATECREATION] = StringUtils.DEFAULTVALUE;
            r[USAGER_RUM] = StringUtils.DEFAULTVALUE;
            r[USAGER_DATEMANDAT] = StringUtils.DEFAULTVALUE;
            r[USAGER_IBAN] = StringUtils.DEFAULTVALUE;
            r[USAGER_TITULAIRE] = StringUtils.DEFAULTVALUE;

            //TODO This is not needed, it is just a concat of some values to control more easily
            r[PIVOTCOLUMNS_NB]= reformat(e.getAdresseEmplacement().getNumero() + e.getAdresseEmplacement().getNumAppEtage() + e.getAdresseEmplacement().getEntreeBatImmeuble(), false);
        }
        catch (Exception ex) {
            System.out.println("fillEmplInformation");
        }
    }

    private void fillContInformation(Conteneur c, String r[], boolean isValid) {
        try {
            System.out.println("Entering CsvWriter fillContInformation method\n" + c.toString());

            //TODO r[ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC] = reformat(c.getDateDistribution());
            r[ENQUETE_CONTENEUR_FLUXMATIERE] = reformat(c.getFluxOuMatiere(), false);
            r[ENQUETE_CONTENEUR_VOLUMEBAC] = reformat(c.getVolumeBac(), false);
            r[ENQUETE_CONTENEUR_SERRURE] = reformat(c.getSerrure(), false);
            r[ENQUETE_CONTENEUR_FOURNISSEUR] = reformat(c.getFournisseur(), false);
            r[ENQUETE_CONTENEUR_NUMPUCE] = reformat(c.getNumeroPuce(), false);
            r[ENQUETE_CONTENEUR_NUMCUVE] = reformat(c.getNumeroCuve(), false);
            r[ENQUETE_CONTENEUR_NUMCAB] = reformat(c.getNumeroCab(), false);

            //TODO This was for pays de valois, can disapear
            /*if (!isValid) {
                r[PIVOTCOLUMNS_NB]= reformatLastColumn(c.isFilled());
            }*/
        }
        catch (Exception ex) {
            System.out.println("fillContInformation");
        }
    }

    public void generateCsv() throws IOException {
        System.out.println("Entering CsvWriter generateCsv method");

        if (output == null) {
            throw new IllegalStateException();
        }
        FileWriter writer = new FileWriter(output);

        // Append titles
        writer.append(MAINTITLES);
        writer.append(SUBTITLES);
        updateProgress(80);
        for (String row : newTable) {
            writer.append(row);
        }
        writer.flush();
        writer.close();

        updateProgress(100);
    }


    /*public void addRowToXls(String row[]) {
        XSSFRow excelRow = excelSheet.createRow(rowIndexXls++);
        for (int i = 0; i < row.length; i++) {
            XSSFCell c = excelRow.createCell(i);
            c.setCellValue(row[i]);
        }
    }*/
}
