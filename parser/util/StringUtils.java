package parser.util;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import static parser.util.HeaderUtils.*;

public abstract class StringUtils {
    public static final String DEFAULTVALUE = "";
    public static final String CSVREGEX = "(.+\\.csv)$";
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

    public static String reformat(String s) {
        if (s == null || s.equalsIgnoreCase("vide")) {
            return DEFAULTVALUE;
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
}
