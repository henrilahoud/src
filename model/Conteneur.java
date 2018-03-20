package model;

import parser.util.Statistics;

import java.time.LocalDate;

public class Conteneur {
    private LocalDate dateDistribution;
    private String fluxOuMatiere;
    private String volumeBac;
    private String serrure;
    private String fournisseur;
    private String numeroPuce;
    private String numeroCuve;
    private String numeroCab;

    public LocalDate getDateDistribution() {
        return dateDistribution;
    }

    public void setDateDistribution(LocalDate dateDistribution) {
        this.dateDistribution = dateDistribution;
    }

    public String getFluxOuMatiere() {
        return fluxOuMatiere;
    }

    public void setFluxOuMatiere(String fluxOuMatiere) {
        this.fluxOuMatiere = fluxOuMatiere;
    }

    public String getVolumeBac() {
        return volumeBac;
    }

    public void setVolumeBac(String volumeBac) {
        this.volumeBac = volumeBac;
    }

    public String getSerrure() {
        return serrure;
    }

    public void setSerrure(String serrure) {
        this.serrure = serrure;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getNumeroPuce() {
        return numeroPuce;
    }

    public void setNumeroPuce(String numeroPuce) {
        this.numeroPuce = numeroPuce;
    }

    public String getNumeroCuve() {
        return numeroCuve;
    }

    public void setNumeroCuve(String numeroCuve) {
        this.numeroCuve = numeroCuve;
    }

    public String getNumeroCab() {
        return numeroCab;
    }

    public void setNumeroCab(String numeroCab) {
        this.numeroCab = numeroCab;
    }

    public Conteneur() {
        Statistics.nbConteneurs ++;
    }

    @Override
    public String toString() {
        return "Conteneur{" +
                "dateDistribution=" + dateDistribution +
                ", fluxOuMatiere='" + fluxOuMatiere + '\'' +
                ", volumeBac='" + volumeBac + '\'' +
                ", serrure='" + serrure + '\'' +
                ", fournisseur='" + fournisseur + '\'' +
                ", numeroPuce='" + numeroPuce + '\'' +
                ", numeroCuve='" + numeroCuve + '\'' +
                ", numeroCab='" + numeroCab + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conteneur conteneur = (Conteneur) o;

        if (dateDistribution != null ? !dateDistribution.equals(conteneur.dateDistribution) : conteneur.dateDistribution != null)
            return false;
        if (fluxOuMatiere != null ? !fluxOuMatiere.equals(conteneur.fluxOuMatiere) : conteneur.fluxOuMatiere != null)
            return false;
        if (volumeBac != null ? !volumeBac.equals(conteneur.volumeBac) : conteneur.volumeBac != null) return false;
        if (serrure != null ? !serrure.equals(conteneur.serrure) : conteneur.serrure != null) return false;
        if (fournisseur != null ? !fournisseur.equals(conteneur.fournisseur) : conteneur.fournisseur != null)
            return false;
        if (numeroPuce != null ? !numeroPuce.equals(conteneur.numeroPuce) : conteneur.numeroPuce != null) return false;
        if (numeroCuve != null ? !numeroCuve.equals(conteneur.numeroCuve) : conteneur.numeroCuve != null) return false;
        return numeroCab != null ? numeroCab.equals(conteneur.numeroCab) : conteneur.numeroCab == null;
    }

    @Override
    public int hashCode() {
        int result = dateDistribution != null ? dateDistribution.hashCode() : 0;
        result = 31 * result + (fluxOuMatiere != null ? fluxOuMatiere.hashCode() : 0);
        result = 31 * result + (volumeBac != null ? volumeBac.hashCode() : 0);
        result = 31 * result + (serrure != null ? serrure.hashCode() : 0);
        result = 31 * result + (fournisseur != null ? fournisseur.hashCode() : 0);
        result = 31 * result + (numeroPuce != null ? numeroPuce.hashCode() : 0);
        result = 31 * result + (numeroCuve != null ? numeroCuve.hashCode() : 0);
        result = 31 * result + (numeroCab != null ? numeroCab.hashCode() : 0);
        return result;
    }
}
