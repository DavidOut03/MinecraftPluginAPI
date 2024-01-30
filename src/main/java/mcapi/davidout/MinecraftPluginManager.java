package mcapi.davidout;

import mcapi.davidout.manager.file.IFileManager;
import mcapi.davidout.manager.language.IMessageManager;

public class MinecraftPluginManager {

    private final IFileManager fileManager;
    private final IMessageManager messageManager;

    public MinecraftPluginManager(IFileManager fileManager, IMessageManager messageManager) {
        this.fileManager = fileManager;
        this.messageManager = messageManager;
    }

    public IFileManager getFileManager() {
        return this.fileManager;
    }
    public IMessageManager getMessageManager() {return this.messageManager;}
}
