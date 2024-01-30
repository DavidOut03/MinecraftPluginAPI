package mcapi.davidout.manager.file;

import mcapi.davidout.MinecraftPlugin;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.FileSystems;

public class YamlFileManager implements IFileManager {

    private final String relativePath;
    private final File baseFolder;


    public YamlFileManager(File folder) {
        this.baseFolder = folder;

        if(!folder.exists()) {
            folder.mkdirs();
        }

        String separator = FileSystems.getDefault().getSeparator();
        relativePath = String.format("%s%s$fileName.yaml",
                folder.getPath(), separator);
    }

    public YamlFileManager() {
        this.baseFolder = new File("./");
        String separator = FileSystems.getDefault().getSeparator();
        relativePath = String.format("%s%s$fileName.yaml",
                "./", separator);
    }

    @Override
    public <T> T loadFile(Class<T> fileClass, String path) {
        try (Reader reader = new FileReader(relativePath.replace("$fileName", path.replace(".yaml", "")))) {
            Yaml yaml = new Yaml();
            return yaml.loadAs(reader, fileClass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> boolean saveFile(T fileClass, String path) throws IOException {
        try (Writer writer = new FileWriter(relativePath.replace("$fileName", path))) {

            DumperOptions dumperOptions = new DumperOptions();
            dumperOptions.setExplicitStart(false);
            dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);

            FileRepresenter customRepresenter = new FileRepresenter(fileClass, dumperOptions);
            Yaml yaml = new Yaml(customRepresenter, dumperOptions);
            yaml.dump(fileClass, writer);
            return true;
        }
    }

    @Override
    public File getBaseFolder() {
        return baseFolder;
    }
}