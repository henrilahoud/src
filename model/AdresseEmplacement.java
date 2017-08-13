package model;

public class AdresseEmplacement extends Adresse {
    private String numero;
    private String type;
    private String article;
    private String nom;
    private String cp;
    private String ville;
    private String facturation; // Facturer a l'adresse de l'emplacement ?

    public String getFacturation() {
        return facturation;
    }

    public void setFacturation(String facturation) {
        this.facturation = facturation;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdresseEmplacement that = (AdresseEmplacement) o;

        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (article != null ? !article.equals(that.article) : that.article != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (cp != null ? !cp.equals(that.cp) : that.cp != null) return false;
        if (ville != null ? !ville.equals(that.ville) : that.ville != null) return false;
        return facturation != null ? facturation.equals(that.facturation) : that.facturation == null;
    }

    @Override
    public int hashCode() {
        int result = numero != null ? numero.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        result = 31 * result + (facturation != null ? facturation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AdresseEmplacement{" +
                "numero='" + numero + '\'' +
                ", type='" + type + '\'' +
                ", article='" + article + '\'' +
                ", nom='" + nom + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                ", facturation='" + facturation + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

}
