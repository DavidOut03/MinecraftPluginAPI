package com.davidout.api;

import com.davidout.api.command.CommandManager;
import com.davidout.api.command.CustomCommand;
import com.davidout.api.enchantment.EnchantmentManager;
import com.davidout.api.gui.GUIManager;
import com.davidout.api.listener.ArmorListener;
import com.davidout.api.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class MinecraftPlugin extends JavaPlugin {

    public static MinecraftPlugin getPlugin() {return plugin;}
    private static MinecraftPlugin plugin;

    /***
     *
     *   Class created by David Out
     *
     */

    private EnchantmentManager enchantmentManager;
    private CommandManager commandManager;
    private ScoreboardManager scoreboardManager;
    private GUIManager guiManager;
    private PluginManager pm;


    @Override
    public void onEnable() {
        plugin = this;

        this.pm = Bukkit.getPluginManager();
        this.commandManager = new CommandManager(this);
        this.guiManager = new GUIManager();
        this.enchantmentManager = new EnchantmentManager(this);
        this.scoreboardManager = new ScoreboardManager();


        // register all necesarry listeners
        this.registerNecesarryListeners();

        // register all the events and commands.
        this.registerEvents().forEach (eventListener -> this.pm.registerEvents(eventListener, this) );
        this.commandManager.registerCommands ( this.registerCommands() );
        this.enchantmentManager.registerEnchantments();

        this.onStartup();
    }

    @Override
    public void onDisable() {
        this.enchantmentManager.unRegisterEnchantments();
        this.onShutdown();
    }

    public void registerNecesarryListeners() {
        this.pm.registerEvents(this.guiManager, this);
        this.pm.registerEvents(new ArmorListener(), this);
    }


    /**
     *
     *  GETTERS
     *
     */

    public EnchantmentManager getEnchantmentManager() {return this.enchantmentManager;}
    public CommandManager getCommandManager() {return this.commandManager;}
    public PluginManager getPluginManager() {return this.pm;}
    public GUIManager getGuiManager() {return this.guiManager;}
    public ScoreboardManager getScoreboardManager() {return this.scoreboardManager;}

    /**
     *
     *  ABSTRACT METHODS
     *
     */

    // This method is called when the plugin is started by the server.
    public abstract void onStartup();

    // this method is called when the plugin is stopped or disabled by the server.
    public abstract void onShutdown();

    // This method is called on startup and automatically adds
    public abstract List<Listener> registerEvents();

    // This method is called on startup to register the commands;
    public abstract List<CustomCommand> registerCommands();




}
