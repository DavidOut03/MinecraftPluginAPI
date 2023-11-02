package mcapi.davidout;

import mcapi.davidout.controller.manager.PluginManagers;
import mcapi.davidout.model.custom.file.PluginFolder;
import mcapi.davidout.model.custom.command.CommandManager;
import mcapi.davidout.controller.manager.EnchantmentManager;
import mcapi.davidout.controller.manager.GUIManager;
import mcapi.davidout.controller.manager.ScoreboardManager;
import mcapi.davidout.model.plugin.PluginDetails;
import mcapi.davidout.model.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;

public abstract class MinecraftPlugin extends PluginDetails {

    public static MinecraftPlugin getPlugin() {return plugin;}
    private static MinecraftPlugin plugin;

    public static PluginFolder getPluginFolder() {return new PluginFolder("");}

    /***
     *
     *   Class created by David Out
     *
     */

    private PluginManagers pluginManagers;
    private PluginLoader pluginLoader;


    @Override
    public void onEnable() {
        // Register all necesarry managers
        plugin = this;
        pluginManagers = new PluginManagers(this);
        pluginLoader = new PluginLoader(this);

        this.pluginLoader.loadPlugin();
        this.onStartup();
    }

    @Override
    public void onDisable() {
        this.pluginLoader.unLoadPlugin();
        this.onShutdown();
    }


    public EnchantmentManager getEnchantmentManager() {return pluginManagers.getEnchantmentManager();}
    public ScoreboardManager getScoreboardManager() {return pluginManagers.getScoreboardManager();}
    public CommandManager getCommandManager() {return pluginManagers.getCommandManager();}
    public GUIManager getGuiManager() {return pluginManagers.getGuiManager();}
    public PluginManager getPluginManager() {return pluginManagers.getPluginManager();}


}
