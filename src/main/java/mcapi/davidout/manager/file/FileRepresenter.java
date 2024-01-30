package mcapi.davidout.manager.file;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class FileRepresenter extends Representer {

    public <T> FileRepresenter(T fileClass, DumperOptions options) {
        super(options);
        addClassTag(fileClass.getClass(), Tag.MAP);
    }
}
