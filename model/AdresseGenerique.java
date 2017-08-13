package model;

public class AdresseGenerique extends Adresse {
    private String numAptOuEtage;
    private String entreeBatImmeuble;
    private String numEtLabelVoie;
    private String complementAdresse;
    private String cp;
    private String ville;
    private String pays;

    public String getNumAptOuEtage() {
        return numAptOuEtage;
    }

    public void setNumAptOuEtage(String numAptOuEtage) {
        this.numAptOuEtage = numAptOuEtage;
    }

    public String getEntreeBatImmeuble() {
        return entreeBatImmeuble;
    }

    public void setEntreeBatImmeuble(String entreeBatImmeuble) {
        this.entreeBatImmeuble = entreeBatImmeuble;
    }

    public String getNumEtLabelVoie() {
        return numEtLabelVoie;
    }

    public void setNumEtLabelVoie(String numEtLabelVoie) {
        this.numEtLabelVoie = numEtLabelVoie;
    }

    public String getComplementAdresse() {
        return complementAdresse;
    }

    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }

    @Override
    public String getCp() {
        return cp;
    }

    @Override
    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String getVille() {
        return ville;
    }

    @Override
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
        return "AdresseGenerique{" +
                "numAptOuEtage='" + numAptOuEtage + '\'' +
                ", entreeBatImmeuble='" + entreeBatImmeuble + '\'' +
                ", numEtLabelVoie='" + numEtLabelVoie + '\'' +
                ", complementAdresse='" + complementAdresse + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdresseGenerique that = (AdresseGenerique) o;

        if (numAptOuEtage != null ? !numAptOuEtage.equals(that.numAptOuEtage) : that.numAptOuEtage != null)
            return false;
        if (entreeBatImmeuble != null ? !entreeBatImmeuble.equals(that.entreeBatImmeuble) : that.entreeBatImmeuble != null)
            return false;
        if (numEtLabelVoie != null ? !numEtLabelVoie.equals(that.numEtLabelVoie) : that.numEtLabelVoie != null)
            return false;
        if (complementAdresse != null ? !complementAdresse.equals(that.complementAdresse) : that.complementAdresse != null)
            return false;
        if (cp != null ? !cp.equals(that.cp) : that.cp != null) return false;
        if (ville != null ? !ville.equals(that.ville) : that.ville != null) return false;
        return pays != null ? pays.equals(that.pays) : that.pays == null;
    }

    @Override
    public int hashCode() {
        int result = numAptOuEtage != null ? numAptOuEtage.hashCode() : 0;
        result = 31 * result + (entreeBatImmeuble != null ? entreeBatImmeuble.hashCode() : 0);
        result = 31 * result + (numEtLabelVoie != null ? numEtLabelVoie.hashCode() : 0);
        result = 31 * result + (complementAdresse != null ? complementAdresse.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        result = 31 * result + (pays != null ? pays.hashCode() : 0);
        return result;
    }
}
