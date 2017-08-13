package model;

public class Contact {
    private String civilite;
    private String nomOuRaisonSociale;
    private String prenom;
    private String telephone1;
    private String telephone2;
    private String email;

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNomOuRaisonSociale() {
        return nomOuRaisonSociale;
    }

    public void setNomOuRaisonSociale(String nom) {
        this.nomOuRaisonSociale = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "civilite='" + civilite + '\'' +
                ", nom='" + nomOuRaisonSociale + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone1='" + telephone1 + '\'' +
                ", telephone2='" + telephone2 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (civilite != null ? !civilite.equals(contact.civilite) : contact.civilite != null) return false;
        if (nomOuRaisonSociale != null ? !nomOuRaisonSociale.equals(contact.nomOuRaisonSociale) : contact.nomOuRaisonSociale != null) return false;
        if (prenom != null ? !prenom.equals(contact.prenom) : contact.prenom != null) return false;
        if (telephone1 != null ? !telephone1.equals(contact.telephone1) : contact.telephone1 != null) return false;
        if (telephone2 != null ? !telephone2.equals(contact.telephone2) : contact.telephone2 != null) return false;
        return email != null ? email.equals(contact.email) : contact.email == null;
    }

    @Override
    public int hashCode() {
        int result = civilite != null ? civilite.hashCode() : 0;
        result = 31 * result + (nomOuRaisonSociale != null ? nomOuRaisonSociale.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (telephone1 != null ? telephone1.hashCode() : 0);
        result = 31 * result + (telephone2 != null ? telephone2.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
