package ui;

import javafx.concurrent.Task;
import parser.CsvLoader;

import java.io.File;

public class LoadTask extends Task<parser.DataWrapper> {


    private final File input;

    private LoadTask(File f) {
        this.input = f;
    }
    
    public static LoadTask create(File f) {
        return new LoadTask(f);
    }
    
    @Override
    public parser.DataWrapper call() throws Exception {
        CsvLoader loader = new CsvLoader(input, (progress) -> {
            updateProgress(progress, 100);
        });
        return loader.load();
    }
}
