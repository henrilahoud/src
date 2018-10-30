package handler;

import model.Emplacement;
import parser.util.DataChecker;
import java.util.*;
import static parser.util.Statistics.nbErrors;


public class ErrorHandler {

    private Map<Integer,Set<PivotError>> pivotErrors;

    public ErrorHandler() {
        this.pivotErrors = new HashMap<>();
    }

    public Map<Integer, Set<PivotError>> getPivotErrors() {
        return pivotErrors;
    }

    public void setPivotErrors(Map<Integer, Set<PivotError>> pivotErrors) {
        this.pivotErrors = pivotErrors;
    }

    //Will check for every type of error and add errors to the set
    public void process(Emplacement emp) {
        Set<PivotError> errors = new HashSet<>();

        if (DataChecker.checkMandatoryData(emp)) {
            errors.add(new PivotError(emp.getUsager().getCodeUsager(), PivotError.Type.MISSINGMANDATORYDATA, "Manque données obligatoires (orange)"));
            ++nbErrors;
        }
        if (DataChecker.checkConditionalMandatory(emp)) {
            errors.add(new PivotError(emp.getUsager().getCodeUsager(), PivotError.Type.MISSINGCONDITIONALDATA, "Manque données obligatoires (vert)"));
            ++nbErrors;
        }
        if (DataChecker.checkTelFormat(emp)) {
            errors.add(new PivotError(emp.getUsager().getCodeUsager(), PivotError.Type.TELFORMAT, "Numéro de téléphone non conforme"));
            ++nbErrors;
        }
        if (DataChecker.checkEmailFormat(emp)) {
            errors.add(new PivotError(emp.getUsager().getCodeUsager(), PivotError.Type.EMAILFORMAT, "Email non conforme"));
            ++nbErrors;
        }
        if (DataChecker.checkHabitantsNumber(emp)) {
            errors.add(new PivotError(emp.getUsager().getCodeUsager(), PivotError.Type.TOOMANYHABITANTS, "Nombre important d'habitants"));
            ++nbErrors;
        }
        if (DataChecker.checkSiretFormat(emp) ) {
            errors.add(new PivotError(emp.getUsager().getCodeUsager(), PivotError.Type.SIRETFORMAT, "SIRET non conforme"));
            ++nbErrors;
        }
        if (DataChecker.checkCpFormat(emp) ) {
            errors.add(new PivotError(emp.getUsager().getCodeUsager(), PivotError.Type.CPFORMAT, "Code postal non conforme"));
            ++nbErrors;
        }


        pivotErrors.put(new Integer(emp.getUsager().getCodeUsager()), errors);
    }


}
