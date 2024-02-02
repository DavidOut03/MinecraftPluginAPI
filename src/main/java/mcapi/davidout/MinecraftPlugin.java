package mcapi.davidout;

import mcapi.davidout.manager.enchantment.EnchantmentManager;
import mcapi.davidout.manager.enchantment.enchanter.ItemEnchanter;
import mcapi.davidout.manager.file.IFileManager;
import mcapi.davidout.manager.file.YamlFileManager;
import mcapi.davidout.manager.language.MessageManager;
import mcapi.davidout.manager.language.bundle.IMessageBundle;
import mcapi.davidout.manager.language.bundle.MessageBundle;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class MinecraftPlugin extends JavaPlugin {

    private static MinecraftPluginManager pluginManager;
    private static MinecraftPlugin instance;


    @Override
    public void onEnable() {
        instance = this;
        pluginManager = createPluginManager(this);

        this.setUp();
        this.onStartup();
    }

    @Override
    public void onDisable() {
        this.shutdown();
        this.onShutdown();
    }

    void setUp() {
        if(getPluginManager() == null) {
            System.err.println("Could not set up the Minecraft Plugin Api.");
            return;
        }

        getPluginManager().getMessageManager().loadMessageBundles();
        this.getDefaultMessageBundles().forEach(messageBundle -> {
            boolean present = getPluginManager().getMessageManager().getBundles().stream().anyMatch(iMessageBundle -> iMessageBundle.getName().equalsIgnoreCase(messageBundle.getName()));
            if (present) return;
            getPluginManager().getMessageManager().addMessageBundle(messageBundle);
            getPluginManager().getMessageManager().saveMessageBundles();
        });
    }

    public void shutdown() {
        getPluginManager().getMessageManager().saveMessageBundles();
    }


    public abstract void onStartup();
    public abstract void onShutdown();
    public abstract List<IMessageBundle> getDefaultMessageBundles();

    public static MinecraftPlugin getInstance() {
        return instance;
    }

    public static MinecraftPluginManager getPluginManager() {
        if(pluginManager == null) {
            pluginManager = createPluginManager(instance);
        }

        return pluginManager;
    }

    public static MinecraftPluginManager createPluginManager(Plugin plugin) {
        IFileManager fileManager = new YamlFileManager(plugin.getDataFolder());

        return new MinecraftPluginManager(
                fileManager,
                new MessageManager(fileManager),
                new EnchantmentManager(plugin, new ItemEnchanter())
        );
    }


}
