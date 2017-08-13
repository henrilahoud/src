package model;

import java.time.LocalDate;

public class Carte {
    private String type;
    private String numero;
    private LocalDate dateAttribution;
    private String motifDistribution;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDateAttribution() {
        return dateAttribution;
    }

    public void setDateAttribution(LocalDate dateAttribution) {
        this.dateAttribution = dateAttribution;
    }

    public String getMotifDistribution() {
        return motifDistribution;
    }

    public void setMotifDistribution(String motifDistribution) {
        this.motifDistribution = motifDistribution;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "type='" + type + '\'' +
                ", numero='" + numero + '\'' +
                ", dateAttribution='" + dateAttribution + '\'' +
                ", motifDistribution='" + motifDistribution + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carte carte = (Carte) o;

        if (type != null ? !type.equals(carte.type) : carte.type != null) return false;
        if (numero != null ? !numero.equals(carte.numero) : carte.numero != null) return false;
        if (dateAttribution != null ? !dateAttribution.equals(carte.dateAttribution) : carte.dateAttribution != null)
            return false;
        return motifDistribution != null ? motifDistribution.equals(carte.motifDistribution) : carte.motifDistribution == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (dateAttribution != null ? dateAttribution.hashCode() : 0);
        result = 31 * result + (motifDistribution != null ? motifDistribution.hashCode() : 0);
        return result;
    }
}
