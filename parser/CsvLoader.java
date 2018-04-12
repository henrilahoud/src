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
    // TODO henri regarde ici comment on gère une exception
    if (!isFileSelected()) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error
      throw new FileNotFoundException();
    }
    if (!isCsv()) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error
      throw new WrongFileTypeException();
    }
    updateProgress(10);
    // TODO henri ici on sait que tout va bien, on continue et on n'est pas dans un sapin de noel de if
    CsvParser parser = new CsvParser((progress) -> {
      // nous sommes à l'intérieur d'une lambda, c'est une fonction qu'on passe en paramètre.
      updateProgress(Math.max(10, progress));
    });
    DataWrapper wrapper = parser.parse(csvFile);

    if (wrapper == null) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error instead.
      throw new NoDataParsedException();
    }
    return wrapper;
  }
}
      


