package model;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import parser.util.Statistics;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static parser.util.HeaderUtils.CODEUSAGER;

public class Emplacement {

    private String identifier;

    private String activitePro;
    private LocalDate dateEmmenagement;
    private AdresseEmplacement adresseEmplacement;

    private Usager usager;
    private Set<Conteneur> conteneurs;
    private Proprietaire proprietaire;
    private Teomi teomi;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getActivitePro() {
        return activitePro;
    }

    public void setActivitePro(String activitePro) {
        this.activitePro = activitePro;
    }

    public LocalDate getDateEmmenagement() {
        return dateEmmenagement;
    }

    public void setDateEmmenagement(LocalDate dateEmmenagement) {
        this.dateEmmenagement = dateEmmenagement;
    }

    public AdresseEmplacement getAdresseEmplacement() {
        return adresseEmplacement;
    }

    public void setAdresseEmplacement(AdresseEmplacement adresseEmplacement) {
        this.adresseEmplacement = adresseEmplacement;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public Set<Conteneur> getConteneurs() {
        return conteneurs;
    }

    public void setConteneurs(Set<Conteneur> conteneurs) {
        this.conteneurs = conteneurs;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Teomi getTeomi() {
        return teomi;
    }

    public void setTeomi(Teomi teomi) {
        this.teomi = teomi;
    }

    public Emplacement() {
        ++Statistics.nbEmplacements;
        if (Statistics.nbEmplacements - CODEUSAGER > 1) {
            int i= 0;
        }
    }

    public boolean isValid() {
        if (conteneurs.size() > 0) {
            Conteneur[] mySet = conteneurs.toArray(new Conteneur[conteneurs.size()]);
            for (int i = 0 ; i < mySet.length-1 ; i++) {
                if (mySet[i].isFilled() ^ mySet[i+1].isFilled()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Emplacement{" +
                "identifier='" + identifier + '\'' +
                ", activitePro='" + activitePro + '\'' +
                ", dateEmmenagement=" + dateEmmenagement +
                ", adresseEmplacement=" + adresseEmplacement +
                ", usager=" + usager +
                ", conteneurs=" + conteneurs +
                ", proprietaire=" + proprietaire +
                ", teomi=" + teomi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emplacement that = (Emplacement) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        if (activitePro != null ? !activitePro.equals(that.activitePro) : that.activitePro != null) return false;
        if (dateEmmenagement != null ? !dateEmmenagement.equals(that.dateEmmenagement) : that.dateEmmenagement != null)
            return false;
        if (adresseEmplacement != null ? !adresseEmplacement.equals(that.adresseEmplacement) : that.adresseEmplacement != null)
            return false;
        if (usager != null ? !usager.equals(that.usager) : that.usager != null) return false;
        if (conteneurs != null ? !conteneurs.equals(that.conteneurs) : that.conteneurs != null) return false;
        if (proprietaire != null ? !proprietaire.equals(that.proprietaire) : that.proprietaire != null) return false;
        return teomi != null ? teomi.equals(that.teomi) : that.teomi == null;
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (activitePro != null ? activitePro.hashCode() : 0);
        result = 31 * result + (dateEmmenagement != null ? dateEmmenagement.hashCode() : 0);
        result = 31 * result + (adresseEmplacement != null ? adresseEmplacement.hashCode() : 0);
        result = 31 * result + (usager != null ? usager.hashCode() : 0);
        result = 31 * result + (conteneurs != null ? conteneurs.hashCode() : 0);
        result = 31 * result + (proprietaire != null ? proprietaire.hashCode() : 0);
        result = 31 * result + (teomi != null ? teomi.hashCode() : 0);
        return result;
    }
}
