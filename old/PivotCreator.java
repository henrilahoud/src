package old;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;

public class PivotCreator {
    static final int PIVOT_COLUMNS = 73;
    private ArrayList<String> pivot;
    private ArrayList<String> headers;
    private HashMap<String,Integer> mapping;
    private HashMap<String,Integer> columns;

    public PivotCreator(OldCsvParser Csv) throws IOException {
        mapping = new HashMap<String, Integer>();

        mapping.put("Enquête de Dotation:Code usager",0);
        mapping.put("Enquête de Dotation:ActiviteProfessionnelle, Activité professionnelle",2);
        mapping.put("Enquête de Dotation:RaisonSociale",9);
        mapping.put("Enquête de Dotation:SIRET",16);
        mapping.put("Enquête de Dotation:CiviliteNonParticulier, Civilité",3);
        mapping.put("Enquête de Dotation:NomNonParticulier",4);
        mapping.put("Enquête de Dotation:PrenomNonParticulier",5);
        mapping.put("Enquête de Dotation:CiviliteContact1, Civilité",3);
        mapping.put("Enquête de Dotation:NomContact1",4);
        mapping.put("Enquête de Dotation:PrenomContact1",5);
        mapping.put("Enquête de Dotation:Telephone1",10);
        mapping.put("Enquête de Dotation:Telephone2",11);
        mapping.put("Enquête de Dotation:CiviliteContact2, Civilité",6);
        mapping.put("Enquête de Dotation:NomContact2",7);
        mapping.put("Enquête de Dotation:PrenomContact2",8);
        mapping.put("Enquête de Dotation:ProprietaireLocataire, Propriétaire_Locataire",14);
        mapping.put("Enquête de Dotation:NombreHabitants",13);
        mapping.put("Enquête de Dotation:NatureJuridiqueCategorieTiers, Nature juridique du tiers",47); //inversés liste Christophe
        mapping.put("Enquête de Dotation:NatureJuridiqueCategorieTiers, Catégorie du tiers",46); //Pareil
        mapping.put("Enquête de Dotation:Commentaires",15);
        mapping.put("Enquête de Dotation:StatutPouB, Statut propriétaire ou bailleur",49);
        mapping.put("Enquête de Dotation:CivilitePouB, Civilité",50);
        mapping.put("Enquête de Dotation:NomRaisonSocialePouB",51);
        mapping.put("Enquête de Dotation:PrenomPouB",52);
        mapping.put("Enquête de Dotation:NumAppEtagePouB",53);
        mapping.put("Enquête de Dotation:EntreeBatimentImmeublePouB",54);
        mapping.put("Enquête de Dotation:NumeroLibelleVoiePouB",55);
        mapping.put("Enquête de Dotation:ComplementAdressePouB",56);
        mapping.put("Enquête de Dotation:CPVilleFrancePouB, Lvl 1",57);
        mapping.put("Enquête de Dotation:CPVilleFrancePouB, Lvl 2",58);
        mapping.put("Enquête de Dotation:CPHorsFrancePouB",57);
        mapping.put("Enquête de Dotation:VilleHorsFrancePouB",58);
        mapping.put("Enquête de Dotation:PaysHorsFrancePouB, Pays",59);
        mapping.put("Enquête de Dotation:Telephone1PouB",60);
        mapping.put("Enquête de Dotation:Telephone2PouB",61);
        mapping.put("Enquête de Dotation:AdresseEmailPouB",62);
        mapping.put("Enquête de Dotation:DateEmmenagement",17);
        mapping.put("Enquête de Dotation:AdresseEmplacement, Lvl 1",23);
        mapping.put("Enquête de Dotation:AdresseEmplacement, Lvl 2",22);
        mapping.put("Enquête de Dotation:AdresseEmplacement, Lvl 3",19);
        mapping.put("Enquête de Dotation:AdresseEmplacement, Lvl 4",20);
        mapping.put("Enquête de Dotation:AdresseEmplacement, Lvl 5",21);
        mapping.put("Enquête de Dotation:NumVoieExtension",18);
        mapping.put("Enquête de Dotation:ResidencePrincipaleSecondaire, Résidence principale ou secondaire",24);
        mapping.put("Enquête de Dotation:FacturationAdresse",37);
        mapping.put("Enquête de Dotation:NomCompletRaisonSociale",38);
        mapping.put("Enquête de Dotation:NumAppEtage",39);
        mapping.put("Enquête de Dotation:EntreeBatimentImmeuble",40);
        mapping.put("Enquête de Dotation:NumeroLibelleVoie",41);
        mapping.put("Enquête de Dotation:ComplementAdresse",42);
        mapping.put("Enquête de Dotation:CPVilleFranceFacturation, Lvl 1",43);
        mapping.put("Enquête de Dotation:CPVilleFranceFacturation, Lvl 2",44);
        mapping.put("Enquête de Dotation:CPHorsFranceFacturation",43);
        mapping.put("Enquête de Dotation:VilleHorsFranceFacturation",43);
        mapping.put("Enquête de Dotation:PaysHorsFranceFacturation, Pays",45);
        mapping.put("Enquête de Dotation:Type, Type de carte",33);
        mapping.put("Enquête de Dotation:NumCarte",34);
        mapping.put("Enquête de Dotation:DateAttributionCarte",35);
        mapping.put("Enquête de Dotation:MotifDistribution, Motif de distribution",36);
        mapping.put("Enquête de Dotation:NumInvariantFiscal",63);
        mapping.put("Enquête de Dotation:AnneeConstruction",64);
        mapping.put("Enquête de Dotation:AdresseEmail",12);
        mapping.put("Enquête de Dotation:CodeRivoli",65);
        mapping.put("Enquête de Dotation:NumeroParcelle",66);
        mapping.put("Enquête de Dotation_CONTENEURISATION:DateDistributionBac",25);
        mapping.put("Enquête de Dotation_CONTENEURISATION:FluxMatiere, Flux_Matière",26);
        mapping.put("Enquête de Dotation_CONTENEURISATION:VolumeBac, ENQUETE - Volume bac (L)",27);
        mapping.put("Enquête de Dotation_CONTENEURISATION:Serrure",28);
        mapping.put("Enquête de Dotation_CONTENEURISATION:Fournisseur, Fournisseur",29);
        mapping.put("Enquête de Dotation_CONTENEURISATION:NumPuce",30);
        mapping.put("Enquête de Dotation_CONTENEURISATION:NumCuve",31);
        mapping.put("Enquête de Dotation_CONTENEURISATION:NumCAB",32);

        columns = new HashMap<>(Csv.getColumns());
        preparePivot(Csv);
    }

    private void preparePivot(OldCsvParser Csv){
        headers = new ArrayList<>();
        pivot = new ArrayList<>();


        for (int i=0 ; i<Csv.getPivotRows().size() ; i++){
            pivot.add(prepareRow(Csv.getCsvTable().get(Csv.getPivotRows().get(i)))); //PrepareRow de la ligne de CsvTable qui a l'indice i dans PivotRows
        }

    }

    private String prepareRow(String Row[]){
        StringJoiner Joiner = new StringJoiner(";","","\n");
        String PivotRow[]= new String[PIVOT_COLUMNS];
        Arrays.fill(PivotRow, "");

        for (HashMap.Entry<String,Integer> PivotColumn : mapping.entrySet()){
            String ColumnName = PivotColumn.getKey();
            int CsvIndex = columns.get(ColumnName);
            PivotRow[PivotColumn.getValue()] = Row[CsvIndex];
        }

        for (int i=0; i<PIVOT_COLUMNS; i++){
                Joiner.add(PivotRow[i]);
        }

        return Joiner.toString();
    }


}
