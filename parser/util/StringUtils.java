package parser.util;

import handler.MandatoryDataMissingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.File;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import static parser.util.HeaderUtils.*;

public abstract class StringUtils {
    public static final String DEFAULTVALUE = "";
    public static final String CSVREGEX = "(.+\\.csv)$";
    public static final String TXTREGEX = "(.+\\.txt)$";
    public static final String XLSXREGEX = "(.+\\.xlsx)$";
    public static File savePath;

    public static String[] splitRow(String rowStr) {
        String row[] = rowStr.split(";",-1);

        for (int i=0 ; i<row.length ; i++) {
            String s = row[i].replace("\"","");
            row[i] = s;
        }

        return row;
    }

    //public static String[] splitRow(String rowStr, int colNb) {

    //}

    public static String joinRow(String[] row) {
        StringJoiner joiner = new StringJoiner(";","","\n");

        for (String cell : row) {
            joiner.add(cell);
        }

        return joiner.toString();
    }

    public static String reformat(LocalDate date) {
        if (date == null) {
            return DEFAULTVALUE;
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern(OUTPUTDATEPATTERN);

        String dateStr = date.format(f);
        return dateStr;
    }

    public static String reformat(String s, boolean normalized) {
        if (s == null || s.equalsIgnoreCase("vide")) {
            return DEFAULTVALUE;
        }
        if (normalized) {
            String normalizedstring = Normalizer.normalize(s, Normalizer.Form.NFD);

            return normalizedstring
                    .toUpperCase()
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "") //Replaces accentuated chars by their generic letter
                    .replaceAll("[^\\w]"," ")
                    .replaceAll("[\\s]+"," ")
                    .trim();
        }
        return s;
    }

    public static String reformat(int s) {
        return Integer.toString(s);
    }

    public static String reformatLastColumn(boolean b) {
        if (!b) {
            return "A SUPPRIMER";
        }
        else return DEFAULTVALUE;
    }

    public static String XlsxStringValue(Cell cell) {

        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return DEFAULTVALUE;
            default:
                return "ERREUR";
                //TODO throw new InvalidStateException("Valeur de cellule invalide :\n" + cell.toString());
        }

    }
}
