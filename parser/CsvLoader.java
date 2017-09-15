package parser;

import javafx.stage.FileChooser;
import handler.*;

import java.io.File;

import static handler.exceptionWrapper.*;
import static parser.util.StringUtils.*;
import static ui.UiUtils.*;

public class CsvLoader {
    private File CsvFile;

    public CsvLoader() {
        FileChooser OpenFileWindow = new FileChooser();
        CsvFile = OpenFileWindow.showOpenDialog(null);

        //
        savePath = CsvFile.getParentFile();
    }

    private boolean isFileSelected() {
        return !(CsvFile == null);
    }

    private boolean isCsv() {
        return CsvFile.getPath().toLowerCase().matches(CSVREGEX);
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

