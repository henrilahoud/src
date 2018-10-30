package model;

import java.time.LocalDate;
import java.util.Objects;

import static parser.util.Statistics.nbConteneurs;

public class Conteneur {
    private String identifier;
    private boolean filled = false;
    private LocalDate dateDistribution;
    private String fluxOuMatiere;
    private String volumeBac;
    private String serrure;
    private String fournisseur;
    private String numeroPuce;
    private String numeroCuve;
    private String numeroCab;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

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
        if (fluxOuMatiere.equalsIgnoreCase("SAC")) this.filled = true; //Only for Pays de Valois
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
        if (numeroPuce.length() > 0) this.filled = true;
    }

    public String getNumeroCuve() {
        return numeroCuve;
    }

    public void setNumeroCuve(String numeroCuve) {
        this.numeroCuve = numeroCuve;
        if (numeroCuve.length() > 0) this.filled = true; //Only for Pays de Valois
    }

    public String getNumeroCab() {
        return numeroCab;
    }

    public void setNumeroCab(String numeroCab) {
        this.numeroCab = numeroCab;
        if (numeroCab.length() > 0) this.filled = true; //Only for Pays de Valois
    }

    @Override
    public String toString() {
        return "Conteneur{" +
                "identifier='" + identifier + '\'' +
                ", filled=" + filled +
                ", dateDistribution=" + dateDistribution +
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
        return filled == conteneur.filled &&
                Objects.equals(identifier, conteneur.identifier) &&
                Objects.equals(dateDistribution, conteneur.dateDistribution) &&
                Objects.equals(fluxOuMatiere, conteneur.fluxOuMatiere) &&
                Objects.equals(volumeBac, conteneur.volumeBac) &&
                Objects.equals(serrure, conteneur.serrure) &&
                Objects.equals(fournisseur, conteneur.fournisseur) &&
                Objects.equals(numeroPuce, conteneur.numeroPuce) &&
                Objects.equals(numeroCuve, conteneur.numeroCuve) &&
                Objects.equals(numeroCab, conteneur.numeroCab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, filled, dateDistribution, fluxOuMatiere, volumeBac, serrure, fournisseur, numeroPuce, numeroCuve, numeroCab);
    }


    /* Is not supposed to exist
    public Conteneur(boolean isCounted) {
        if (isCounted) {
            this.conteneurInternalId = ++nbConteneurs; //Only for Pays de Valois
        }
    }*/

}
