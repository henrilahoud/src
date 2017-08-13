package model;

import java.time.LocalDate;

public class Reglement {
    private String categorieTiers;
    private String natureJuridique;
    private String modeReglement;
    private LocalDate dateModeReglement;
    private String rum;
    private LocalDate dateMandat;
    private String iban;
    private String titulaireCompte;

    public String getModeReglement() {
        return modeReglement;
    }

    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

    public LocalDate getDateModeReglement() {
        return dateModeReglement;
    }

    public void setDateModeReglement(LocalDate dateModeReglement) {
        this.dateModeReglement = dateModeReglement;
    }

    public String getRum() {
        return rum;
    }

    public void setRum(String rum) {
        this.rum = rum;
    }

    public LocalDate getDateMandat() {
        return dateMandat;
    }

    public void setDateMandat(LocalDate dateMandat) {
        this.dateMandat = dateMandat;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getTitulaireCompte() {
        return titulaireCompte;
    }

    public void setTitulaireCompte(String titulaireCompte) {
        this.titulaireCompte = titulaireCompte;
    }

    public String getCategorieTiers() {
        return categorieTiers;
    }

    public void setCategorieTiers(String categorieTiers) {
        this.categorieTiers = categorieTiers;
    }

    public String getNatureJuridique() {
        return natureJuridique;
    }

    public void setNatureJuridique(String natureJuridique) {
        this.natureJuridique = natureJuridique;
    }

    @Override
    public String toString() {
        return "Reglement{" +
                "categorieTiers='" + categorieTiers + '\'' +
                ", natureJuridique='" + natureJuridique + '\'' +
                ", modeReglement='" + modeReglement + '\'' +
                ", dateModeReglement=" + dateModeReglement +
                ", rum='" + rum + '\'' +
                ", dateMandat=" + dateMandat +
                ", iban='" + iban + '\'' +
                ", titulaireCompte='" + titulaireCompte + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reglement reglement = (Reglement) o;

        if (categorieTiers != null ? !categorieTiers.equals(reglement.categorieTiers) : reglement.categorieTiers != null)
            return false;
        if (natureJuridique != null ? !natureJuridique.equals(reglement.natureJuridique) : reglement.natureJuridique != null)
            return false;
        if (modeReglement != null ? !modeReglement.equals(reglement.modeReglement) : reglement.modeReglement != null)
            return false;
        if (dateModeReglement != null ? !dateModeReglement.equals(reglement.dateModeReglement) : reglement.dateModeReglement != null)
            return false;
        if (rum != null ? !rum.equals(reglement.rum) : reglement.rum != null) return false;
        if (dateMandat != null ? !dateMandat.equals(reglement.dateMandat) : reglement.dateMandat != null) return false;
        if (iban != null ? !iban.equals(reglement.iban) : reglement.iban != null) return false;
        return titulaireCompte != null ? titulaireCompte.equals(reglement.titulaireCompte) : reglement.titulaireCompte == null;
    }

    @Override
    public int hashCode() {
        int result = categorieTiers != null ? categorieTiers.hashCode() : 0;
        result = 31 * result + (natureJuridique != null ? natureJuridique.hashCode() : 0);
        result = 31 * result + (modeReglement != null ? modeReglement.hashCode() : 0);
        result = 31 * result + (dateModeReglement != null ? dateModeReglement.hashCode() : 0);
        result = 31 * result + (rum != null ? rum.hashCode() : 0);
        result = 31 * result + (dateMandat != null ? dateMandat.hashCode() : 0);
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        result = 31 * result + (titulaireCompte != null ? titulaireCompte.hashCode() : 0);
        return result;
    }
}
