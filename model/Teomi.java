package model;

public class Teomi {
    private String numInvariant;
    private String anneeConstruction;
    private String codeRivoli;
    private String numParcelle;

    public String getNumInvariant() {
        return numInvariant;
    }

    public void setNumInvariant(String numInvariant) {
        this.numInvariant = numInvariant;
    }

    public String getAnneeConstruction() {
        return anneeConstruction;
    }

    public void setAnneeConstruction(String anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public String getCodeRivoli() {
        return codeRivoli;
    }

    public void setCodeRivoli(String codeRivoli) {
        this.codeRivoli = codeRivoli;
    }

    public String getNumParcelle() {
        return numParcelle;
    }

    public void setNumParcelle(String numParcelle) {
        this.numParcelle = numParcelle;
    }

    @Override
    public String toString() {
        return "Teomi{" +
                "numInvariant='" + numInvariant + '\'' +
                ", anneeConstruction='" + anneeConstruction + '\'' +
                ", codeRivoli='" + codeRivoli + '\'' +
                ", numParcelle='" + numParcelle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teomi teomi = (Teomi) o;

        if (numInvariant != null ? !numInvariant.equals(teomi.numInvariant) : teomi.numInvariant != null) return false;
        if (anneeConstruction != null ? !anneeConstruction.equals(teomi.anneeConstruction) : teomi.anneeConstruction != null)
            return false;
        if (codeRivoli != null ? !codeRivoli.equals(teomi.codeRivoli) : teomi.codeRivoli != null) return false;
        return numParcelle != null ? numParcelle.equals(teomi.numParcelle) : teomi.numParcelle == null;
    }

    @Override
    public int hashCode() {
        int result = numInvariant != null ? numInvariant.hashCode() : 0;
        result = 31 * result + (anneeConstruction != null ? anneeConstruction.hashCode() : 0);
        result = 31 * result + (codeRivoli != null ? codeRivoli.hashCode() : 0);
        result = 31 * result + (numParcelle != null ? numParcelle.hashCode() : 0);
        return result;
    }
}
