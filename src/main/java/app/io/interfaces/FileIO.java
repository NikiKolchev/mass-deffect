package app.io.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by niksun on 12/9/16.
 */
public interface FileIO {

    String read(String file) throws IOException;

    void write(String fileContent, String file) throws FileNotFoundException;
}
