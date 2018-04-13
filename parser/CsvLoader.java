package parser;

import handler.NoDataParsedException;
import handler.WrongFileTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

import static parser.util.StringUtils.CSVREGEX;
import static parser.util.StringUtils.savePath;

public class CsvLoader {

  private File csvFile;
  private Consumer<Integer> onProgressUpdateListener;

  public CsvLoader(File f) {
    csvFile = f;
    savePath = f.getParentFile();
  }

  public CsvLoader(File f, Consumer<Integer> onProgressUpdateListener) {
    this(f);
    this.onProgressUpdateListener = onProgressUpdateListener;
  }

  private void updateProgress(Integer progress) {
    if (onProgressUpdateListener != null) {
      onProgressUpdateListener.accept(progress);
    }
  }

  private boolean isFileSelected() {
    return !(csvFile == null);
  }

  private boolean isCsv() {
    return csvFile.getPath().toLowerCase().matches(CSVREGEX);
  }

  public DataWrapper load() throws Exception {
    if (!isFileSelected()) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error
      throw new FileNotFoundException();
    }
    if (!isCsv()) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error
      throw new WrongFileTypeException();
    }

    updateProgress(0);

    CsvParser parser = new CsvParser((progress) -> {
      // nous sommes à l'intérieur d'une lambda, c'est une fonction qu'on passe en paramètre.
      updateProgress(progress);
    });
    DataWrapper wrapper = parser.parse(csvFile);

    if (wrapper == null) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error instead.
      throw new NoDataParsedException();
    }
    return wrapper;
  }
}
      


