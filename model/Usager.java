package model;

public class Usager {


    private int codeUsager;
    private Contact contact1;
    private Contact contact2;
    private String nombreHabitants;
    private String statutHabitat;
    private String commentaires;

    // Entreprise only
    private String raisonSociale;
    private String siret;

    // Carte
    private Carte carte;

    //Facturation
    private String nomOuRaisonSocialeFacturation;
    private AdresseGenerique adresseFacturation;
    private Reglement reglement;

    public int getCodeUsager() {
        return codeUsager;
    }

    public void setCodeUsager(int codeUsager) {
        this.codeUsager = codeUsager;
    }

    public Contact getContact1() {
        return contact1;
    }

    public void setContact1(Contact contact1) {
        this.contact1 = contact1;
    }

    public Contact getContact2() {
        return contact2;
    }

    public void setContact2(Contact contact2) {
        this.contact2 = contact2;
    }

    public String getNombreHabitants() {
        return nombreHabitants;
    }

    public void setNombreHabitants(String nombreHabitants) {
        this.nombreHabitants = nombreHabitants;
    }

    public String getStatutHabitat() {
        return statutHabitat;
    }

    public void setStatutHabitat(String statutHabitat) {
        this.statutHabitat = statutHabitat;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public String getNomOuRaisonSocialeFacturation() {
        return nomOuRaisonSocialeFacturation;
    }

    public void setNomOuRaisonSocialeFacturation(String nomOuRaisonSocialeFacturation) {
        this.nomOuRaisonSocialeFacturation = nomOuRaisonSocialeFacturation;
    }

    public AdresseGenerique getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(AdresseGenerique adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    public Reglement getReglement() {
        return reglement;
    }

    public void setReglement(Reglement reglement) {
        this.reglement = reglement;
    }

    @Override
    public String toString() {
        return "Usager{" +
                "codeUsager=" + codeUsager +
                ", contact1=" + contact1 +
                ", contact2=" + contact2 +
                ", nombreHabitants='" + nombreHabitants + '\'' +
                ", statutHabitat='" + statutHabitat + '\'' +
                ", commentaires='" + commentaires + '\'' +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", siret='" + siret + '\'' +
                ", carte=" + carte +
                ", nomOuRaisonSocialeFacturation='" + nomOuRaisonSocialeFacturation + '\'' +
                ", adresseFacturation=" + adresseFacturation +
                ", reglement=" + reglement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usager usager = (Usager) o;

        if (codeUsager != usager.codeUsager) return false;
        if (contact1 != null ? !contact1.equals(usager.contact1) : usager.contact1 != null) return false;
        if (contact2 != null ? !contact2.equals(usager.contact2) : usager.contact2 != null) return false;
        if (nombreHabitants != null ? !nombreHabitants.equals(usager.nombreHabitants) : usager.nombreHabitants != null)
            return false;
        if (statutHabitat != null ? !statutHabitat.equals(usager.statutHabitat) : usager.statutHabitat != null)
            return false;
        if (commentaires != null ? !commentaires.equals(usager.commentaires) : usager.commentaires != null)
            return false;
        if (raisonSociale != null ? !raisonSociale.equals(usager.raisonSociale) : usager.raisonSociale != null)
            return false;
        if (siret != null ? !siret.equals(usager.siret) : usager.siret != null) return false;
        if (carte != null ? !carte.equals(usager.carte) : usager.carte != null) return false;
        if (nomOuRaisonSocialeFacturation != null ? !nomOuRaisonSocialeFacturation.equals(usager.nomOuRaisonSocialeFacturation) : usager.nomOuRaisonSocialeFacturation != null)
            return false;
        if (adresseFacturation != null ? !adresseFacturation.equals(usager.adresseFacturation) : usager.adresseFacturation != null)
            return false;
        return reglement != null ? reglement.equals(usager.reglement) : usager.reglement == null;
    }

    @Override
    public int hashCode() {
        int result = codeUsager;
        result = 31 * result + (contact1 != null ? contact1.hashCode() : 0);
        result = 31 * result + (contact2 != null ? contact2.hashCode() : 0);
        result = 31 * result + (nombreHabitants != null ? nombreHabitants.hashCode() : 0);
        result = 31 * result + (statutHabitat != null ? statutHabitat.hashCode() : 0);
        result = 31 * result + (commentaires != null ? commentaires.hashCode() : 0);
        result = 31 * result + (raisonSociale != null ? raisonSociale.hashCode() : 0);
        result = 31 * result + (siret != null ? siret.hashCode() : 0);
        result = 31 * result + (carte != null ? carte.hashCode() : 0);
        result = 31 * result + (nomOuRaisonSocialeFacturation != null ? nomOuRaisonSocialeFacturation.hashCode() : 0);
        result = 31 * result + (adresseFacturation != null ? adresseFacturation.hashCode() : 0);
        result = 31 * result + (reglement != null ? reglement.hashCode() : 0);
        return result;
    }
}

