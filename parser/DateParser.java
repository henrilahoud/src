package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static parser.util.HeaderUtils.INPUTDATEPATTERN;

public class DateParser implements GenericParser<LocalDate, String> {
    @Override
    public LocalDate parse(String dateStr) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(INPUTDATEPATTERN);

        try {
            LocalDate date = LocalDate.parse(dateStr,f);
            return date ;
        }
        catch (Exception e) {
            //e.getCause().printStackTrace();
            return null;
        }
    }
}
