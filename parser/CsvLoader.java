package parser;

import javafx.stage.FileChooser;
import exceptionHandler.*;
import ui.UiUtils;

import java.io.File;

import static exceptionHandler.NullValueRunTimeException.*;
import static exceptionHandler.exceptionWrapper.errors;
import static ui.UiUtils.*;

public class CsvLoader {
    private File CsvFile;

    public CsvLoader() {
        FileChooser OpenFileWindow = new FileChooser();
        CsvFile = OpenFileWindow.showOpenDialog(null);
    }

    private boolean isFileSelected() {
        return !(CsvFile == null);
    }

    private boolean isCsv() {
        return CsvFile.getPath().toLowerCase().matches("(.+\\.csv)$");
    }

    public void load() {
        try {
            if (isFileSelected()) {
                if (isCsv()) {
                    CsvParser parser = new CsvParser();
                    DataWrapper wrapper = parser.parse(CsvFile);

                    if (wrapper != null) {
                        CsvWriter writer = new CsvWriter(wrapper);
                        writer.write();
                    }
                    warnUser(ERRORTITLE, NODATAHEADER, NODATACONTENT);
                }
                else {
                    warnUser(ERRORTITLE, WRONGTYPEHEADER, WRONGTYPECONTENT) ;
                }
            }
        }
        catch (NullValueRunTimeException e) {
            errors.add(e);
            warnUser("Exception chopee", "","");
        }
    }
}

