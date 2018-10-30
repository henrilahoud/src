package parser;

import handler.NoDataParsedException;
import handler.WrongFileTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

import static parser.util.StringUtils.*;

public class DaxiumLoader {

  private File file;
  private Consumer<Integer> onProgressUpdateListener;

  public DaxiumLoader(File f) {
    file = f;
    savePath = f.getParentFile();
  }

  public DaxiumLoader(File f, Consumer<Integer> onProgressUpdateListener) {
    this(f);
    this.onProgressUpdateListener = onProgressUpdateListener;
  }

  private void updateProgress(Integer progress) {
    if (onProgressUpdateListener != null) {
      onProgressUpdateListener.accept(progress);
    }
  }

  private boolean isFileSelected() {
    return !(file == null);
  }

  private boolean isXlsx() {
    return file.getPath().toLowerCase().matches(XLSXREGEX);
  }

  public DataWrapper load() throws Exception {
    if (!isFileSelected()) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error
      throw new FileNotFoundException();
    }

    if (!isXlsx()) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error
      throw new WrongFileTypeException();
    }

    updateProgress(0);

    XlsxParser parser = new XlsxParser((progress) -> {
      // nous sommes à l'intérieur d'une lambda, c'est une fonction qu'on passe en paramètre.
      updateProgress(progress);
    });
    DataWrapper wrapper = parser.parse(file);

    if (wrapper == null) {
      // Something bad happened. We cannot display UI here. This is a business component. Notify error instead.
      throw new NoDataParsedException();
    }
    return wrapper;
  }
}
      


