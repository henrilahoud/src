package ui;

import javafx.concurrent.Task;
import parser.CsvWriter;
import parser.DataWrapper;

import java.io.File;

public class WriteTask extends Task<Void> {


    private final DataWrapper wrapper;
    private final File output;

    private WriteTask(File output, DataWrapper wrapper) {
        this.output = output;
        this.wrapper = wrapper;
    }
    
    public static WriteTask create(File output, DataWrapper w) {
        return new WriteTask(output, w);
    }
    
    @Override
    public Void call() {
        CsvWriter writer = new CsvWriter(output, wrapper, (progress) -> {
            updateProgress(progress, 100);
        });
        writer.write();
        return null;
    }
}
