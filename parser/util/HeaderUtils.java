package parser.util;

import java.util.HashMap;
import java.util.Map;

public abstract class HeaderUtils {
    public static int CODEUSAGER = 1;
    public static final String DEFAULTDATE = "";
    public static final String DEFAULTVALUE = "";
    public static final String INPUTDATEPATTERN = "dd.MM.yyyy";
    public static final String OUTPUTDATEPATTERN = "dd/MM/yyyy";
    public static final int PIVOTCOLUMNS_NB = 73;

    // First 2 rows of output
    public static final String MAINTITLES = "Renseignements de l'usager occupant;;;;;;;;;;;;;;;;;Renseignements de l'emplacement;;;;;;;;Conteneur;;;;;;;;Cartes;;;;Adresse de facturation;;;;;;;;;;;Renseignements du Propriétaire ou Bailleur;;;;;;;;;;;;;;;Informations TEOMi;;;Informations SEPA;;;;;\n";
    public static final String SUBTITLES = "Code usager;Code reprise;Activité professionnelle;Civilité Contact 1;Nom Contact 1;Prénom Contact 1;Civilité Contact 2;Nom Contact 2;Prénom Contact 2;Raison sociale;Téléphone 1;Téléphone 2;Adresse email;Nombre Habitants;Propriétaire / Locataire;Commentaires;N° SIRET;Date d'emménagement;N° de voie + Extension;Type de Voie;Article de la voie;Nom de la voie;CP Ville;Ville;Résidence Principale ou Secondaire;Date de distribution du bac;Flux / Matière;Volume bac (L);Serrure;Fournisseur;N° de puce;N° de cuve;N° de CAB;Type;N° carte;Date d'attribution de la carte;Motif de distribution;Facturer à l'adresse de l'emplacement;Nom complet / Raison sociale;N° App / Etage;Entrée / Bâtiment / Immeuble;Numéro + Libellé de la voie;Complément adresse;CP Ville;Ville;Pays;Catégorie du tiers;Nature juridique du tiers;Code propriétaire;Statut propriétaire ou bailleur ?;Civilité;Nom ou Raison sociale;Prénom;N° App / Etage;Entrée / Bâtiment / Immeuble;Numéro + Libellé de la voie;Complément adresse;CP Ville;Ville;Pays;Téléphone;Téléphone 2;Adresse email;Numéro invariant fiscal;Année de construction du logement;Code Rivoli;Numéro de parcelle;Mode de règlement;Date de création;RUM;Date de mandat;IBAN;Titulaire du compte\n";


    // Colonne generee
    public static final String COLUMN_USAGER_CODEUSAGER = "Code Usager";
    // Ces colonnes sont dans le tableau d'entree
    public static final String COLUMN_PARENTID = "ParentId";
    public static final String COLUMN_STRUCTURENAME = "StructureName";
    public static final String COLUMN_RELATIONNAME = "RelationName";
    public static final String COLUMN_IDENTIFIER = "Identifier";
    public static final String COLUMN_USERNAME = "UserName";
    public static final String COLUMN_USERFIRSTNAME = "UserFirstName";
    public static final String COLUMN_USERLASTNAME = "UserLastName";
    public static final String COLUMN_SUB_MOB_CREATEDAT = "Sub_Mob_CreatedAt";
    public static final String COLUMN_SUB_MOB_UPDATEDAT = "Sub_Mob_UpdatedAt";
    public static final String COLUMN_SUB_WEB_CREATEDAT = "Sub_Web_CreatedAt";
    public static final String COLUMN_SUB_WEB_UPDATEDAT = "Sub_Web_UpdatedAt";
    public static final String COLUMN_TASKUSER = "TaskUser";
    public static final String COLUMN_TASKCLIENT = "TaskClient";
    public static final String COLUMN_TASKSITE = "TaskSite";
    public static final String COLUMN_TASKADDRESS = "TaskAddress";
    public static final String COLUMN_TASKSTATUS = "TaskStatus";
    public static final String COLUMN_TASKSTARTDATE = "TaskStartDate";
    public static final String COLUMN_TASKENDDATE = "TaskEndDate";
    public static final String COLUMN_TASKDESCRIPTION = "TaskDescription";
    public static final String COLUMN_ENQUETE_ACTIVITEPROFESSIONNELLE = "Enquête de Dotation:ActiviteProfessionnelle, Activité professionnelle";
    public static final String COLUMN_ENQUETE_RAISONSOCIALE = "Enquête de Dotation:RaisonSociale";
    public static final String COLUMN_ENQUETE_SIRET = "Enquête de Dotation:SIRET";
    public static final String COLUMN_ENQUETE_CIVILITECONTACT1 = "Enquête de Dotation:CiviliteContact1, Civilité";
    public static final String COLUMN_ENQUETE_NOMCONTACT1 = "Enquête de Dotation:NomContact1";
    public static final String COLUMN_ENQUETE_PRENOMCONTACT1 = "Enquête de Dotation:PrenomContact1";
    public static final String COLUMN_ENQUETE_TELEPHONE1 = "Enquête de Dotation:Telephone1";
    public static final String COLUMN_ENQUETE_TELEPHONE2 = "Enquête de Dotation:Telephone2";
    public static final String COLUMN_ENQUETE_ADRESSEEMAIL = "Enquête de Dotation:AdresseEmail";
    //public static final String COLUMN_ENQUETE_DEUXIEMECONTACT = "Enquête de Dotation:DeuxiemeContact";
    public static final String COLUMN_ENQUETE_CIVILITECONTACT2 = "Enquête de Dotation:CiviliteContact2, Civilité";
    public static final String COLUMN_ENQUETE_NOMCONTACT2 = "Enquête de Dotation:NomContact2";
    public static final String COLUMN_ENQUETE_PRENOMCONTACT2 = "Enquête de Dotation:PrenomContact2";
    public static final String COLUMN_ENQUETE_PROPRIETAIRELOCATAIRE = "Enquête de Dotation:ProprietaireLocataire, Propriétaire_Locataire";
    public static final String COLUMN_ENQUETE_NOMBREHABITANTS = "Enquête de Dotation:NombreHabitants";
    public static final String COLUMN_ENQUETE_NATUREJURIDIQUETIERS = "Enquête de Dotation:NatureJuridiqueCategorieTiers, Nature juridique du tiers";
    public static final String COLUMN_ENQUETE_CATEGORIETIERS = "Enquête de Dotation:NatureJuridiqueCategorieTiers, Catégorie du tiers";
    public static final String COLUMN_ENQUETE_COMMENTAIRES = "Enquête de Dotation:Commentaires";
    public static final String COLUMN_ENQUETE_STATUTPOUB = "Enquête de Dotation:StatutPouB, Statut propriétaire ou bailleur";
    public static final String COLUMN_ENQUETE_CIVILITEPOUB = "Enquête de Dotation:CivilitePouB, Civilité";
    public static final String COLUMN_ENQUETE_NOMRAISONSOCIALEPOUB = "Enquête de Dotation:NomRaisonSocialePouB";
    public static final String COLUMN_ENQUETE_PRENOMPOUB = "Enquête de Dotation:PrenomPouB";
    public static final String COLUMN_ENQUETE_NUMAPPETAGEPOUB = "Enquête de Dotation:NumAppEtagePouB";
    public static final String COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB = "Enquête de Dotation:EntreeBatimentImmeublePouB";
    public static final String COLUMN_ENQUETE_NUMEROLIBELLEVOIEPOUB = "Enquête de Dotation:NumeroLibelleVoiePouB";
    public static final String COLUMN_ENQUETE_COMPLEMENTADRESSEPOUB = "Enquête de Dotation:ComplementAdressePouB";
    //public static final String COLUMN_ENQUETE_FRANCEPOUB = "Enquête de Dotation:FrancePouB";
    public static final String COLUMN_ENQUETE_CPVILLEFRANCEPOUB1 = "Enquête de Dotation:CPVilleFrancePouB, Lvl 1";
    public static final String COLUMN_ENQUETE_CPVILLEFRANCEPOUB2 = "Enquête de Dotation:CPVilleFrancePouB, Lvl 2";
    public static final String COLUMN_ENQUETE_CPHORSFRANCEPOUB = "Enquête de Dotation:CPHorsFrancePouB";
    public static final String COLUMN_ENQUETE_VILLEHORSFRANCEPOUB = "Enquête de Dotation:VilleHorsFrancePouB";
    public static final String COLUMN_ENQUETE_PAYSHORSFRANCEPOUB = "Enquête de Dotation:PaysHorsFrancePouB, Pays";
    public static final String COLUMN_ENQUETE_TELEPHONE1POUB = "Enquête de Dotation:Telephone1PouB";
    public static final String COLUMN_ENQUETE_TELEPHONE2POUB = "Enquête de Dotation:Telephone2PouB";
    public static final String COLUMN_ENQUETE_ADRESSEEMAILPOUB = "Enquête de Dotation:AdresseEmailPouB";
    public static final String COLUMN_ENQUETE_DATEEMMENAGEMENT = "Enquête de Dotation:DateEmmenagement";
    public static final String COLUMN_ENQUETE_NUMVOIEEXTENSION = "Enquête de Dotation:NumVoieExtension";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENT1 = "Enquête de Dotation:AdresseEmplacement, Lvl 1";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENT2 = "Enquête de Dotation:AdresseEmplacement, Lvl 2";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENT3 = "Enquête de Dotation:AdresseEmplacement, Lvl 3";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENT4 = "Enquête de Dotation:AdresseEmplacement, Lvl 4";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENT5 = "Enquête de Dotation:AdresseEmplacement, Lvl 5";
    public static final String COLUMN_ENQUETE_RESIDENCEPRINCIPALESECONDAIRE = "Enquête de Dotation:ResidencePrincipaleSecondaire, Résidence principale ou secondaire";
    public static final String COLUMN_ENQUETE_FACTURATIONADRESSE = "Enquête de Dotation:FacturationAdresse";
    public static final String COLUMN_ENQUETE_NOMCOMPLETRAISONSOCIALE = "Enquête de Dotation:NomCompletRaisonSociale";
    public static final String COLUMN_ENQUETE_NUMAPPETAGE = "Enquête de Dotation:NumAppEtage";
    public static final String COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLE = "Enquête de Dotation:EntreeBatimentImmeuble";
    public static final String COLUMN_ENQUETE_NUMEROLIBELLEVOIE = "Enquête de Dotation:NumeroLibelleVoie";
    public static final String COLUMN_ENQUETE_COMPLEMENTADRESSE = "Enquête de Dotation:ComplementAdresse";
    //public static final String COLUMN_ENQUETE_FRANCEFACTURATION = "Enquête de Dotation:FranceFacturation";
    public static final String COLUMN_ENQUETE_CPVILLEFRANCEFACTURATION1 = "Enquête de Dotation:CPVilleFranceFacturation, Lvl 1";
    public static final String COLUMN_ENQUETE_CPVILLEFRANCEFACTURATION2 = "Enquête de Dotation:CPVilleFranceFacturation, Lvl 2";
    public static final String COLUMN_ENQUETE_CPHORSFRANCEFACTURATION = "Enquête de Dotation:CPHorsFranceFacturation";
    public static final String COLUMN_ENQUETE_VILLEHORSFRANCEFACTURATION = "Enquête de Dotation:VilleHorsFranceFacturation";
    public static final String COLUMN_ENQUETE_PAYSHORSFRANCEFACTURATION = "Enquête de Dotation:PaysHorsFranceFacturation, Pays";
    //public static final String COLUMN_ENQUETE_ATTRIBUTIONCARTE = "Enquête de Dotation:AttributionCarte";
    public static final String COLUMN_ENQUETE_TYPECARTE = "Enquête de Dotation:Type, Type de carte";
    public static final String COLUMN_ENQUETE_NUMCARTE = "Enquête de Dotation:NumCarte";
    public static final String COLUMN_ENQUETE_DATEATTRIBUTIONCARTE = "Enquête de Dotation:DateAttributionCarte";
    //public static final String COLUMN_ENQUETE_TEOMI = "Enquête de Dotation:TEOMI";
    public static final String COLUMN_ENQUETE_MOTIFDISTRIBUTIONCARTE = "Enquête de Dotation:MotifDistribution, Motif de distribution";
    public static final String COLUMN_ENQUETE_NUMINVARIANTFISCAL = "Enquête de Dotation:NumInvariantFiscal";
    public static final String COLUMN_ENQUETE_ANNEECONSTRUCTION = "Enquête de Dotation:AnneeConstruction";
    public static final String COLUMN_ENQUETE_CODERIVOLI = "Enquête de Dotation:CodeRivoli";
    public static final String COLUMN_ENQUETE_NUMEROPARCELLE = "Enquête de Dotation:NumeroParcelle";
    public static final String COLUMN_ENQUETE_CIVILITENONPARTICULIER = "Enquête de Dotation:CiviliteNonParticulier, Civilité";
    public static final String COLUMN_ENQUETE_NOMNONPARTICULIER = "Enquête de Dotation:NomNonParticulier";
    public static final String COLUMN_ENQUETE_PRENOMNONPARTICULIER = "Enquête de Dotation:PrenomNonParticulier";
    public static final String COLUMN_ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC = "Enquête de Dotation_CONTENEURISATION:DateDistributionBac";
    public static final String COLUMN_ENQUETE_CONTENEUR_FLUXMATIERE = "Enquête de Dotation_CONTENEURISATION:FluxMatiere, Flux_Matière";
    public static final String COLUMN_ENQUETE_CONTENEUR_VOLUMEBAC = "Enquête de Dotation_CONTENEURISATION:VolumeBac, ENQUETE - Volume bac (L)";
    public static final String COLUMN_ENQUETE_CONTENEUR_SERRURE = "Enquête de Dotation_CONTENEURISATION:Serrure";
    public static final String COLUMN_ENQUETE_CONTENEUR_FOURNISSEUR = "Enquête de Dotation_CONTENEURISATION:Fournisseur, Fournisseur";
    public static final String COLUMN_ENQUETE_CONTENEUR_NUMPUCE = "Enquête de Dotation_CONTENEURISATION:NumPuce";
    public static final String COLUMN_ENQUETE_CONTENEUR_NUMCUVE = "Enquête de Dotation_CONTENEURISATION:NumCuve";
    public static final String COLUMN_ENQUETE_CONTENEUR_NUMCAB = "Enquête de Dotation_CONTENEURISATION:NumCAB";


    //public static final Map<String, Integer> DEST_HEADERS = new HashMap<>();
    //{
        public static final int USAGER_CODEUSAGER = 0;
        /*
        public static final int ENQUETE_ACTIVITEPROFESSIONNELLE = 2;
        public static final int ENQUETE_RAISONSOCIALE = 9;
        public static final int ENQUETE_SIRET, 16);
        public static final int ENQUETE_CIVILITENONPARTICULIER,3);
        public static final int ENQUETE_NOMNONPARTICULIER,4);
        public static final int ENQUETE_PRENOMNONPARTICULIER,5);
        public static final int ENQUETE_CIVILITECONTACT1,3);
        public static final int ENQUETE_NOMCONTACT1,4);
        public static final int ENQUETE_PRENOMCONTACT1,5);
        public static final int ENQUETE_TELEPHONE1,10);
        public static final int ENQUETE_TELEPHONE2,11);
        public static final int ENQUETE_CIVILITECONTACT2,6);
        public static final int ENQUETE_NOMCONTACT2,7);
        public static final int ENQUETE_PRENOMCONTACT2,8);
        public static final int ENQUETE_PROPRIETAIRELOCATAIRE,14);
        public static final int ENQUETE_NOMBREHABITANTS,13);
        public static final int ENQUETE_NATUREJURIDIQUETIERS,47); //inversés liste Christophe
        public static final int ENQUETE_CATEGORIETIERS,46); //Pareil
        public static final int ENQUETE_COMMENTAIRES,15);
        public static final int ENQUETE_STATUTPOUB,49);
        public static final int ENQUETE_CIVILITEPOUB,50);
        public static final int ENQUETE_NOMRAISONSOCIALEPOUB,51);
        public static final int ENQUETE_PRENOMPOUB,52);
        public static final int ENQUETE_NUMAPPETAGEPOUB,53);
        public static final int ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB,54);
        public static final int ENQUETE_NUMEROLIBELLEVOIEPOUB,55);
        public static final int ENQUETE_COMPLEMENTADRESSEPOUB,56);
        public static final int ENQUETE_CPVILLEFRANCEPOUB1,57);
        public static final int ENQUETE_CPVILLEFRANCEPOUB2,58);
        public static final int ENQUETE_CPHORSFRANCEPOUB,57);
        public static final int ENQUETE_VILLEHORSFRANCEPOUB,58);
        public static final int ENQUETE_PAYSHORSFRANCEPOUB,59);
        public static final int ENQUETE_TELEPHONE1POUB,60);
        public static final int ENQUETE_TELEPHONE2POUB,61);
        public static final int ENQUETE_ADRESSEEMAILPOUB,62);
        public static final int ENQUETE_DATEEMMENAGEMENT,17);
        public static final int ENQUETE_ADRESSEEMPLACEMENT1,23);
        public static final int ENQUETE_ADRESSEEMPLACEMENT2,22);
        public static final int ENQUETE_ADRESSEEMPLACEMENT3,19);
        public static final int ENQUETE_ADRESSEEMPLACEMENT4,20);
        public static final int ENQUETE_ADRESSEEMPLACEMENT5,21);
        public static final int ENQUETE_NUMVOIEEXTENSION,18);
        public static final int ENQUETE_RESIDENCEPRINCIPALESECONDAIRE,24);
        public static final int ENQUETE_FACTURATIONADRESSE,37);
        public static final int ENQUETE_NOMCOMPLETRAISONSOCIALE,38);
        public static final int ENQUETE_NUMAPPETAGE,39);
        public static final int ENQUETE_ENTREEBATIMENTIMMEUBLE,40);
        public static final int ENQUETE_NUMEROLIBELLEVOIE,41);
        public static final int ENQUETE_COMPLEMENTADRESSE,42);
        public static final int ENQUETE_CPVILLEFRANCEFACTURATION1,43);
        public static final int ENQUETE_CPVILLEFRANCEFACTURATION2,44);
        public static final int ENQUETE_CPHORSFRANCEFACTURATION,43);
        public static final int ENQUETE_VILLEHORSFRANCEFACTURATION,44);
        */
        public static final int ENQUETE_PAYSHORSFRANCEFACTURATION = 45;

        /*
        public static final int ENQUETE_TYPECARTE,33);
        public static final int ENQUETE_NUMCARTE,34);
        public static final int ENQUETE_DATEATTRIBUTIONCARTE,35);
        public static final int ENQUETE_MOTIFDISTRIBUTIONCARTE,36);
        public static final int ENQUETE_NUMINVARIANTFISCAL,63);
        public static final int ENQUETE_ANNEECONSTRUCTION,64);
        public static final int ENQUETE_ADRESSEEMAIL,12);
        public static final int ENQUETE_CODERIVOLI,65);
        public static final int ENQUETE_NUMEROPARCELLE,66);
        */
        public static final int ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC = 25;

        /*
        public static final int ENQUETE_CONTENEUR_FLUXMATIERE,26);
        public static final int ENQUETE_CONTENEUR_VOLUMEBAC,27);
        public static final int ENQUETE_CONTENEUR_SERRURE,28);
        public static final int ENQUETE_CONTENEUR_FOURNISSEUR,29);
        public static final int ENQUETE_CONTENEUR_NUMPUCE,30);
        public static final int ENQUETE_CONTENEUR_NUMCUVE,31);
        public static final int ENQUETE_CONTENEUR_NUMCAB,32);
    //}

    /*
     * Map Headers with their column name and index as value
     * @param row the input row
     * @return
     */
    public static Map<String, Integer> mapHeaders(String row) {
        // Split row
        Map<String, Integer> headers = new HashMap<>();

        String[] headerStrs = StringUtils.splitRow(row);

        for (int i = 0; i < headerStrs.length; i++) {
            headers.put(headerStrs[i], i);
        }

        if (headers.keySet().contains(COLUMN_PARENTID)) {
            return headers;
        }
        // TODO throw
        return null;
    }
}
