package parser;

import javafx.stage.FileChooser;
import handler.*;
import ui.LoadTask;

import java.io.File;

import static handler.exceptionWrapper.*;
import static parser.util.StringUtils.*;
import static ui.UiUtils.*;

public class CsvLoader {
    private File csvFile;

    public CsvLoader(File f) {
        csvFile = f;
        savePath = f.getParentFile();
    }

    private boolean isFileSelected() {
        return !(csvFile == null);
    }

    private boolean isCsv() {
        return csvFile.getPath().toLowerCase().matches(CSVREGEX);
    }

    public void load() {
        try {
            if (isFileSelected()) {
                if (isCsv()) {
                    CsvParser parser = new CsvParser();
                    DataWrapper wrapper = parser.parse(csvFile);



                    if (wrapper != null) {
                       // CsvWriter writer = new CsvWriter(wrapper);
                       // writer.write();
                    }
                    else {
                        warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
                    }
                }
                else {
                    warnUser(ERRORTITLE, WRONGTYPEHEADER, WRONGTYPECONTENT) ;
                }
            }
        }
        catch (NullValueRunTimeException e) {
            exceptions.add(e);
            warnUser("Erreur fatale", "Veuillez transmettre cette erreur à l'équipe RIVP :",e.getMessage());
        }
    }
}

