package mcapi.davidout;

import mcapi.davidout.manager.enchantment.IEnchantmentManager;
import mcapi.davidout.manager.file.IFileManager;
import mcapi.davidout.manager.language.IMessageManager;

public class MinecraftPluginManager {

    private final IFileManager fileManager;
    private final IMessageManager messageManager;
    private final IEnchantmentManager enchantmentManager;

    public MinecraftPluginManager(IFileManager fileManager, IMessageManager messageManager, IEnchantmentManager enchantmentManager) {
        this.fileManager = fileManager;
        this.messageManager = messageManager;
        this.enchantmentManager = enchantmentManager;
    }

    public IFileManager getFileManager() {
        return this.fileManager;
    }
    public IMessageManager getMessageManager() {return this.messageManager;}
    public IEnchantmentManager getEnchantmentManager() {return this.enchantmentManager;}
}
