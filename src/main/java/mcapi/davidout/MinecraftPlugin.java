package mcapi.davidout;

import mcapi.davidout.manager.file.YamlFileManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MinecraftPlugin extends JavaPlugin {

    private static MinecraftPlugin instance;
    private static MinecraftPluginManager pluginManager;

    @Override
    public void onEnable() {
        instance = this;


        this.onStartup();
    }

    @Override
    public void onDisable() {

        this.onShutdown();
    }

    public abstract void onStartup();
    public abstract void onShutdown();

    public static MinecraftPlugin getInstance() {
        return instance;
    }

    public static MinecraftPluginManager getPluginManager() {
        if(pluginManager == null) {
            pluginManager = createPluginManager();
        }

        return pluginManager;
    }

    private static MinecraftPluginManager createPluginManager() {
        return new MinecraftPluginManager(
                new YamlFileManager(getInstance().getDataFolder())
        );
    }


}
