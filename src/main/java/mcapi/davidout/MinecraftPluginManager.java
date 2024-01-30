package mcapi.davidout;

import mcapi.davidout.manager.file.IFileManager;

public class MinecraftPluginManager {

    private final IFileManager fileManager;

    public MinecraftPluginManager(IFileManager fileManager) {
        this.fileManager = fileManager;
    }

    public IFileManager getFileManager() {
        return this.fileManager;
    }
}
