package parser.util;

import handler.UnsupportedFileException;

import java.util.HashMap;
import java.util.Map;

public abstract class HeaderUtils {
    public static int CODEUSAGER = 0;
    public static final String DEFAULTDATE = "";
    public static final String DEFAULTVALUE = "";
    public static final String INPUTDATEPATTERN = "dd/MM/yyyy";
    public static final String OUTPUTDATEPATTERN = "dd/MM/yyyy";
    public static final int PIVOTCOLUMNS_NB = 73;

    // First 2 rows of output
    public static final String MAINTITLES = "Renseignements de l'usager occupant;;;;;;;;;;;;;;;;;Renseignements de l'emplacement;;;;;;;;Conteneur;;;;;;;;Cartes;;;;Adresse de facturation;;;;;;;;;;;Renseignements du Propriétaire ou Bailleur;;;;;;;;;;;;;;;Informations TEOMi;;;;Informations SEPA;;;;;;Erreurs;;;;;;\n";
    public static final String SUBTITLES = "Code usager;Code reprise;Activité professionnelle;Civilité Contact 1;Nom Contact 1;Prénom Contact 1;Civilité Contact 2;Nom Contact 2;Prénom Contact 2;Raison sociale;Téléphone 1;Téléphone 2;Adresse email;Nombre Habitants;Propriétaire / Locataire;Commentaires;N° SIRET;Date d'emménagement;N° de voie + Extension;Type de Voie;Article de la voie;Nom de la voie;CP Ville;Ville;Résidence Principale ou Secondaire;Date de distribution du bac;Flux / Matière;Volume bac (L);Serrure;Fournisseur;N° de puce;N° de cuve;N° de CAB;Type;N° carte;Date d'attribution de la carte;Motif de distribution;Facturer à l'adresse de l'emplacement;Nom complet / Raison sociale;N° App / Etage;Entrée / Bâtiment / Immeuble;Numéro + Libellé de la voie;Complément adresse;CP Ville;Ville;Pays;Catégorie du tiers;Nature juridique du tiers;Code propriétaire;Statut propriétaire ou bailleur ?;Civilité;Nom ou Raison sociale;Prénom;N° App / Etage;Entrée / Bâtiment / Immeuble;Numéro + Libellé de la voie;Complément adresse;CP Ville;Ville;Pays;Téléphone;Téléphone 2;Adresse email;Numéro invariant fiscal;Année de construction du logement;Code Rivoli;Numéro de parcelle;Mode de règlement;Date de création;RUM;Date de mandat;IBAN;Titulaire du compte;Pays de Vallois : Double saisie;Information obligatoire manquante (orange);Information obligatoire manquante (vert);Format numéro de téléphone;Format email;Format SIRET;Format code postal;Nombre d'habitants élevé\n";


    // Colonne generee
//    public static final String COLUMN_USAGER_CODEUSAGER = "Code Usager";
    // Ces colonnes sont dans le tableau d'entree
//    public static final String COLUMN_PARENTID = "ParentId";
//    public static final String COLUMN_STRUCTURENAME = "StructureName";
//    public static final String COLUMN_RELATIONNAME = "RelationName";
    public static final String COLUMN_IDENTIFIER = "submissionNumber";
//    public static final String COLUMN_USERNAME = "UserName";
//    public static final String COLUMN_USERFIRSTNAME = "UserFirstName";
//    public static final String COLUMN_USERLASTNAME = "UserLastName";
//    public static final String COLUMN_SUB_MOB_CREATEDAT = "Sub_Mob_CreatedAt";
//    public static final String COLUMN_SUB_MOB_UPDATEDAT = "Sub_Mob_UpdatedAt";
//    public static final String COLUMN_SUB_WEB_CREATEDAT = "Sub_Web_CreatedAt";
//    public static final String COLUMN_SUB_WEB_UPDATEDAT = "Sub_Web_UpdatedAt";
//    public static final String COLUMN_TASKUSER = "TaskUser";
//    public static final String COLUMN_TASKCLIENT = "TaskClient";
//    public static final String COLUMN_TASKSITE = "TaskSite";
//    public static final String COLUMN_TASKADDRESS = "TaskAddress";
//    public static final String COLUMN_TASKSTATUS = "TaskStatus";
//    public static final String COLUMN_TASKSTARTDATE = "TaskStartDate";
//    public static final String COLUMN_TASKENDDATE = "TaskEndDate";
//    public static final String COLUMN_TASKDESCRIPTION = "TaskDescription";
    public static final String COLUMN_ENQUETE_ACTIVITEPROFESSIONNELLE = "ActiviteProfessionnelle";
    public static final String COLUMN_ENQUETE_RAISONSOCIALE = "RaisonSociale";
    public static final String COLUMN_ENQUETE_SIRET = "SIRET";
    public static final String COLUMN_ENQUETE_CIVILITECONTACT1 = "CiviliteContact1";
    public static final String COLUMN_ENQUETE_NOMCONTACT1 = "NomContact1";
    public static final String COLUMN_ENQUETE_PRENOMCONTACT1 = "PrenomContact1";
    public static final String COLUMN_ENQUETE_TELEPHONE1 = "Telephone1";
    public static final String COLUMN_ENQUETE_TELEPHONE2 = "Telephone2";
    public static final String COLUMN_ENQUETE_ADRESSEEMAIL = "AdresseEmail";
    //public static final String COLUMN_ENQUETE_DEUXIEMECONTACT = "Enquête de Dotation:DeuxiemeContact";
    public static final String COLUMN_ENQUETE_CIVILITECONTACT2 = "CiviliteContact2";
    public static final String COLUMN_ENQUETE_NOMCONTACT2 = "NomContact2";
    public static final String COLUMN_ENQUETE_PRENOMCONTACT2 = "PrenomContact2";
    public static final String COLUMN_ENQUETE_PROPRIETAIRELOCATAIRE = "ProprietaireLocataire";
    public static final String COLUMN_ENQUETE_NOMBREHABITANTS = "NombreHabitants";
    public static final String COLUMN_ENQUETE_NATUREJURIDIQUETIERS = "listItemNatureJuridique";
    public static final String COLUMN_ENQUETE_CATEGORIETIERS = "listItemCategorieTiers";
    public static final String COLUMN_ENQUETE_COMMENTAIRES = "Commentaires";
    public static final String COLUMN_ENQUETE_STATUTPOUB = "StatutPouB";
    public static final String COLUMN_ENQUETE_CIVILITEPOUB = "CivilitePouB";
    public static final String COLUMN_ENQUETE_NOMRAISONSOCIALEPOUB = "NomRaisonSocialePouB";
    public static final String COLUMN_ENQUETE_PRENOMPOUB = "PrenomPouB";
    public static final String COLUMN_ENQUETE_NUMAPPETAGEPOUB = "NumAppEtagePouB";
    public static final String COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB = "EntreeBatimentImmeublePouB";
    public static final String COLUMN_ENQUETE_NUMEROLIBELLEVOIEPOUB = "NumeroLibelleVoiePouB";
    public static final String COLUMN_ENQUETE_COMPLEMENTADRESSEPOUB = "ComplementAdressePouB";
    //public static final String COLUMN_ENQUETE_FRANCEPOUB = "Enquête de Dotation:FrancePouB";
    public static final String COLUMN_ENQUETE_CPFRANCEPOUB = "listItemCodePostalPouB";
    public static final String COLUMN_ENQUETE_VILLEFRANCEPOUB = "listItemVillePouB";
    public static final String COLUMN_ENQUETE_CPHORSFRANCEPOUB = "CPHorsFrancePouB";
    public static final String COLUMN_ENQUETE_VILLEHORSFRANCEPOUB = "VilleHorsFrancePouB";
    public static final String COLUMN_ENQUETE_PAYSHORSFRANCEPOUB = "PaysHorsFrancePouB";
    public static final String COLUMN_ENQUETE_TELEPHONE1POUB = "Telephone1PouB";
    public static final String COLUMN_ENQUETE_TELEPHONE2POUB = "Telephone2PouB";
    public static final String COLUMN_ENQUETE_ADRESSEEMAILPOUB = "AdresseEmailPouB";
    public static final String COLUMN_ENQUETE_DATEEMMENAGEMENT = "DateEmmenagement";
    public static final String COLUMN_ENQUETE_NUMVOIEEXTENSION = "NumVoieExtension";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENTVILLE = "listItemVilleEmplacement";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENTCP = "listItemCodePostalEmplacement";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENTTYPE = "listItemTypeVoieEmplacement";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENTARTICLE = "listItemArticleVoie";
    public static final String COLUMN_ENQUETE_ADRESSEEMPLACEMENTNOM = "listItemNomVoieEmplacement";
    public static final String COLUMN_ENQUETE_RESIDENCEPRINCIPALESECONDAIRE = "ResidencePrincipaleSecondaire";
    public static final String COLUMN_ENQUETE_FACTURATIONAUTREADRESSE = "FacturationAutreAdresse";
    public static final String COLUMN_ENQUETE_NOMCOMPLETRAISONSOCIALE = "NomCompletRaisonSociale";
    public static final String COLUMN_ENQUETE_NUMAPPETAGE = "NumAppEtage";
    public static final String COLUMN_ENQUETE_ENTREEBATIMENTIMMEUBLE = "EntreeBatimentImmeuble";
    public static final String COLUMN_ENQUETE_NUMEROLIBELLEVOIE = "NumeroLibelleVoie";
    public static final String COLUMN_ENQUETE_COMPLEMENTADRESSE = "ComplementAdresse";
    //public static final String COLUMN_ENQUETE_FRANCEFACTURATION = "Enquête de Dotation:FranceFacturation";
    public static final String COLUMN_ENQUETE_CPFRANCEFACTURATION = "listItemCodePostalFacturation";
    public static final String COLUMN_ENQUETE_VILLEFRANCEFACTURATION = "listItemVilleFacturation";
    public static final String COLUMN_ENQUETE_CPHORSFRANCEFACTURATION = "CPHorsFranceFacturation";
    public static final String COLUMN_ENQUETE_VILLEHORSFRANCEFACTURATION = "VilleHorsFranceFacturation";
    public static final String COLUMN_ENQUETE_PAYSHORSFRANCEFACTURATION = "PaysHorsFranceFacturation";
    //public static final String COLUMN_ENQUETE_ATTRIBUTIONCARTE = "Enquête de Dotation:AttributionCarte";
    public static final String COLUMN_ENQUETE_TYPECARTE = "Type";
    public static final String COLUMN_ENQUETE_NUMCARTE = "NumCarte";
    public static final String COLUMN_ENQUETE_DATEATTRIBUTIONCARTE = "DateAttributionCarte";
    //public static final String COLUMN_ENQUETE_TEOMI = "Enquête de Dotation:TEOMI";
    public static final String COLUMN_ENQUETE_MOTIFDISTRIBUTIONCARTE = "MotifDistribution";
    public static final String COLUMN_ENQUETE_NUMINVARIANTFISCAL = "NumInvariantFiscal";
    public static final String COLUMN_ENQUETE_ANNEECONSTRUCTION = "AnneeConstruction";
    public static final String COLUMN_ENQUETE_CODERIVOLI = "CodeRivoli";
    public static final String COLUMN_ENQUETE_NUMEROPARCELLE = "NumeroParcelle";
//    public static final String COLUMN_ENQUETE_CIVILITENONPARTICULIER = "Enquête de Dotation:CiviliteNonParticulier, Civilité";
//    public static final String COLUMN_ENQUETE_NOMNONPARTICULIER = "Enquête de Dotation:NomNonParticulier";
//    public static final String COLUMN_ENQUETE_PRENOMNONPARTICULIER = "Enquête de Dotation:PrenomNonParticulier";
    public static final String COLUMN_ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC = "Enquête de Dotation_CONTENEURISATION:DateDistributionBac";
    public static final String COLUMN_ENQUETE_CONTENEUR_FLUXMATIERE = "FluxMatiere";
    public static final String COLUMN_ENQUETE_CONTENEUR_VOLUMEBAC = "VolumeBac";
    public static final String COLUMN_ENQUETE_CONTENEUR_SERRURE = "Serrure";
    public static final String COLUMN_ENQUETE_CONTENEUR_FOURNISSEUR = "Fournisseur";
    public static final String COLUMN_ENQUETE_CONTENEUR_NUMPUCE = "NumPuce";
    public static final String COLUMN_ENQUETE_CONTENEUR_NUMCUVE = "NumCuve";
    public static final String COLUMN_ENQUETE_CONTENEUR_NUMCAB = "NumCAB";
    public static final String COLUMN_ENQUETE_CONTENEUR_UPDATEMAIL = "UpdateUser.Email";


    //Fichier Mapping
    public static final int USAGER_CODEUSAGER = 0;
    public static final int USAGER_CODEREPRISE = 1;
    public static final int ENQUETE_ACTIVITEPROFESSIONNELLE = 2;
    public static final int ENQUETE_RAISONSOCIALE = 9;
    public static final int ENQUETE_SIRET = 16;
    public static final int ENQUETE_CIVILITENONPARTICULIER =3;
    public static final int ENQUETE_NOMNONPARTICULIER =4;
    public static final int ENQUETE_PRENOMNONPARTICULIER =5;
    public static final int ENQUETE_CIVILITECONTACT1 =3;
    public static final int ENQUETE_NOMCONTACT1 =4;
    public static final int ENQUETE_PRENOMCONTACT1 =5;
    public static final int ENQUETE_TELEPHONE1 =10;
    public static final int ENQUETE_TELEPHONE2 =11;
    public static final int ENQUETE_CIVILITECONTACT2 =6;
    public static final int ENQUETE_NOMCONTACT2 =7;
    public static final int ENQUETE_PRENOMCONTACT2 =8;
    public static final int ENQUETE_PROPRIETAIRELOCATAIRE =14;
    public static final int ENQUETE_NOMBREHABITANTS =13;
    public static final int ENQUETE_NATUREJURIDIQUETIERS =47; //inversés liste Christophe
    public static final int ENQUETE_CATEGORIETIERS = 46; //Pareil
    public static final int ENQUETE_COMMENTAIRES = 15;
    public static final int USAGER_CODEPROPRIETAIRE = 48;
    public static final int ENQUETE_STATUTPOUB = 49;
    public static final int ENQUETE_CIVILITEPOUB = 50;
    public static final int ENQUETE_NOMRAISONSOCIALEPOUB = 51;
    public static final int ENQUETE_PRENOMPOUB = 52;
    public static final int ENQUETE_NUMAPPETAGEPOUB = 53;
    public static final int ENQUETE_ENTREEBATIMENTIMMEUBLEPOUB = 54;
    public static final int ENQUETE_NUMEROLIBELLEVOIEPOUB = 55;
    public static final int ENQUETE_COMPLEMENTADRESSEPOUB = 56;
    public static final int ENQUETE_CPVILLEFRANCEPOUB1 = 57;
    public static final int ENQUETE_CPVILLEFRANCEPOUB2 = 58;
    public static final int ENQUETE_PAYSHORSFRANCEPOUB = 59;
    public static final int ENQUETE_TELEPHONE1POUB = 60;
    public static final int ENQUETE_TELEPHONE2POUB = 61;
    public static final int ENQUETE_ADRESSEEMAILPOUB = 62;
    public static final int ENQUETE_DATEEMMENAGEMENT = 17;
    public static final int ENQUETE_NUMVOIEEXTENSION = 18;
    public static final int ENQUETE_ADRESSEEMPLACEMENTTYPE = 19;
    public static final int ENQUETE_ADRESSEEMPLACEMENTARTICLE = 20;
    public static final int ENQUETE_ADRESSEEMPLACEMENTNOM = 21;
    public static final int ENQUETE_ADRESSEEMPLACEMENTCP = 22;
    public static final int ENQUETE_ADRESSEEMPLACEMENTVILLE = 23;
    public static final int ENQUETE_RESIDENCEPRINCIPALESECONDAIRE = 24;
    public static final int ENQUETE_FACTURATIONADRESSE = 37;
    public static final int ENQUETE_NOMCOMPLETRAISONSOCIALE = 38;
    public static final int ENQUETE_NUMAPPETAGE = 39;
    public static final int ENQUETE_ENTREEBATIMENTIMMEUBLE = 40;
    public static final int ENQUETE_NUMEROLIBELLEVOIE = 41;
    public static final int ENQUETE_COMPLEMENTADRESSE = 42;
    public static final int ENQUETE_CPVILLEFRANCEFACTURATION1 = 43;
    public static final int ENQUETE_CPVILLEFRANCEFACTURATION2 = 44;
    public static final int ENQUETE_PAYSHORSFRANCEFACTURATION = 45;
    public static final int ENQUETE_TYPECARTE = 33;
    public static final int ENQUETE_NUMCARTE = 34;
    public static final int ENQUETE_DATEATTRIBUTIONCARTE = 35;
    public static final int ENQUETE_MOTIFDISTRIBUTIONCARTE = 36;
    public static final int ENQUETE_NUMINVARIANTFISCAL = 63;
    public static final int ENQUETE_ANNEECONSTRUCTION = 64;
    public static final int ENQUETE_ADRESSEEMAIL = 12;
    public static final int ENQUETE_CODERIVOLI = 65;
    public static final int ENQUETE_NUMEROPARCELLE = 66;
    public static final int ENQUETE_CONTENEUR_DATEDISTRIBUTIONBAC = 25;
    public static final int ENQUETE_CONTENEUR_FLUXMATIERE = 26;
    public static final int ENQUETE_CONTENEUR_VOLUMEBAC = 27;
    public static final int ENQUETE_CONTENEUR_SERRURE = 28;
    public static final int ENQUETE_CONTENEUR_FOURNISSEUR = 29;
    public static final int ENQUETE_CONTENEUR_NUMPUCE = 30;
    public static final int ENQUETE_CONTENEUR_NUMCUVE = 31;
    public static final int ENQUETE_CONTENEUR_NUMCAB = 32;
    public static final int USAGER_MODEREGLEMENT = 67;
    public static final int USAGER_DATECREATION = 68;
    public static final int USAGER_RUM = 69;
    public static final int USAGER_DATEMANDAT = 70;
    public static final int USAGER_IBAN = 71;
    public static final int USAGER_TITULAIRE = 72;

    /*
     * Map Headers with their column name and index as value
     * @param row the input row
     * @return
     */

    //TODO Should disappear
    /*public static Map<String, Integer> mapHeaders(String row) {
        // Split row
        Map<String, Integer> headers = new HashMap<>();

        String[] headerStrs = StringUtils.splitRow(row);

        for (int i = 0; i < headerStrs.length; i++) {
            headers.put(headerStrs[i], i);
        }
        if (!(headers.keySet().contains(COLUMN_PARENTID)) || !(headers.size() >= 81)) {
            throw new UnsupportedFileException();
        }
        return headers;
    }*/

    public static void resetCodeUsager() {
        CODEUSAGER = 0;
    }
}
