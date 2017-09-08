package model;

public class Facturation {
    private String adresseEmplacement;
    private String nomOuRaisonSociale;
    private String numAppEtage;
    private String entreeBatImmeuble;
    private String numLibelleVoie;
    private String complement;
    private String cp;
    private String ville;
    private String pays;
    private String facturerAdresseEmplacement;

    public String getFacturerAdresseEmplacement() {
        return facturerAdresseEmplacement;
    }

    public void setFacturerAdresseEmplacement(String facturerAdresseEmplacement) {
        this.facturerAdresseEmplacement = facturerAdresseEmplacement;
    }

    public String getAdresseEmplacement() {
        return adresseEmplacement;
    }

    public void setAdresseEmplacement(String adresseEmplacement) {
        this.adresseEmplacement = adresseEmplacement;
    }

    public String getNomOuRaisonSociale() {
        return nomOuRaisonSociale;
    }

    public void setNomOuRaisonSociale(String nomOuRaisonSociale) {
        this.nomOuRaisonSociale = nomOuRaisonSociale;
    }

    public String getNumAppEtage() {
        return numAppEtage;
    }

    public void setNumAppEtage(String numAppEtage) {
        numAppEtage = numAppEtage;
    }

    public String getEntreeBatImmeuble() {
        return entreeBatImmeuble;
    }

    public void setEntreeBatImmeuble(String entreeBatImmeuble) {
        entreeBatImmeuble = entreeBatImmeuble;
    }

    public String getNumLibelleVoie() {
        return numLibelleVoie;
    }

    public void setNumLibelleVoie(String numLibelleVoie) {
        numLibelleVoie = numLibelleVoie;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Facturation{" +
                "adresseEmplacement='" + adresseEmplacement + '\'' +
                ", nomOuRaisonSociale='" + nomOuRaisonSociale + '\'' +
                ", NumAppEtage='" + numAppEtage + '\'' +
                ", EntreeBatImmeuble='" + entreeBatImmeuble + '\'' +
                ", NumLibelleVoie='" + numLibelleVoie + '\'' +
                ", complement='" + complement + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facturation that = (Facturation) o;

        if (adresseEmplacement != null ? !adresseEmplacement.equals(that.adresseEmplacement) : that.adresseEmplacement != null)
            return false;
        if (nomOuRaisonSociale != null ? !nomOuRaisonSociale.equals(that.nomOuRaisonSociale) : that.nomOuRaisonSociale != null)
            return false;
        if (numAppEtage != null ? !numAppEtage.equals(that.numAppEtage) : that.numAppEtage != null) return false;
        if (entreeBatImmeuble != null ? !entreeBatImmeuble.equals(that.entreeBatImmeuble) : that.entreeBatImmeuble != null)
            return false;
        if (numLibelleVoie != null ? !numLibelleVoie.equals(that.numLibelleVoie) : that.numLibelleVoie != null)
            return false;
        if (complement != null ? !complement.equals(that.complement) : that.complement != null) return false;
        if (cp != null ? !cp.equals(that.cp) : that.cp != null) return false;
        if (ville != null ? !ville.equals(that.ville) : that.ville != null) return false;
        return pays != null ? pays.equals(that.pays) : that.pays == null;
    }

    @Override
    public int hashCode() {
        int result = adresseEmplacement != null ? adresseEmplacement.hashCode() : 0;
        result = 31 * result + (nomOuRaisonSociale != null ? nomOuRaisonSociale.hashCode() : 0);
        result = 31 * result + (numAppEtage != null ? numAppEtage.hashCode() : 0);
        result = 31 * result + (entreeBatImmeuble != null ? entreeBatImmeuble.hashCode() : 0);
        result = 31 * result + (numLibelleVoie != null ? numLibelleVoie.hashCode() : 0);
        result = 31 * result + (complement != null ? complement.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        result = 31 * result + (pays != null ? pays.hashCode() : 0);
        return result;
    }
}

