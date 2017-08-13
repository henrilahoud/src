package model;

public class Proprietaire {

    private String codeProprietaire;
    private String statut; // Proprietaire ou bailleur
    private AdresseGenerique adresse;
    private Contact contact;

    public String getCodeProprietaire() {
        return codeProprietaire;
    }

    public void setCodeProprietaire(String codeProprietaire) {
        this.codeProprietaire = codeProprietaire;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public AdresseGenerique getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseGenerique adresse) {
        this.adresse = adresse;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Proprietaire{" +
                "codeProprietaire='" + codeProprietaire + '\'' +
                ", statut='" + statut + '\'' +
                ", adresse=" + adresse +
                ", contact=" + contact +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proprietaire that = (Proprietaire) o;

        if (codeProprietaire != null ? !codeProprietaire.equals(that.codeProprietaire) : that.codeProprietaire != null)
            return false;
        if (statut != null ? !statut.equals(that.statut) : that.statut != null) return false;
        if (adresse != null ? !adresse.equals(that.adresse) : that.adresse != null) return false;
        return contact != null ? contact.equals(that.contact) : that.contact == null;
    }

    @Override
    public int hashCode() {
        int result = codeProprietaire != null ? codeProprietaire.hashCode() : 0;
        result = 31 * result + (statut != null ? statut.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        return result;
    }
}
