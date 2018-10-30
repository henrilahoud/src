package handler;

import model.Emplacement;

public class PivotError {
    public enum Type {
        MISSINGMANDATORYDATA,
        MISSINGCONDITIONALDATA,
        TELFORMAT,
        EMAILFORMAT,
        SIRETFORMAT,
        CPFORMAT,
        TOOMANYHABITANTS,
    }
    public static final int NBOFERRORTYPES = Type.values().length;

    private int idEmplacement;
    private Type type;
    private String content;

    public int getIdEmplacement() {
        return idEmplacement;
    }

    public void setIdEmplacement(int idEmplacement) {
        this.idEmplacement = idEmplacement;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PivotError (int idEmplacement, Type type, String content) {
        this.idEmplacement = idEmplacement;
        this.type = type;
        this.content = content;
    }
}
