package com.davidout.api.minecraft;

import com.davidout.api.minecraft.custom.file.PluginFolder;
import com.davidout.api.minecraft.custom.command.CommandManager;
import com.davidout.api.minecraft.custom.enchantment.EnchantmentManager;
import com.davidout.api.minecraft.custom.gui.GUIManager;
import com.davidout.api.minecraft.custom.scoreboard.ScoreboardManager;
import com.davidout.api.minecraft.database.DatabaseManager;
import com.davidout.api.minecraft.plugin.PluginDetails;
import com.davidout.api.minecraft.plugin.PluginLoader;
import com.davidout.api.minecraft.plugin.PluginManagers;
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
