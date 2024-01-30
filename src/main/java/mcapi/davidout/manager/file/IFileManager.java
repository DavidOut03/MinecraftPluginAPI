package mcapi.davidout.manager.file;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileManager {

    <T> T loadFile(Class<T> fileClass, String path) throws IOException;
    <T> boolean saveFile(T fileClass, String path) throws IOException;
}
