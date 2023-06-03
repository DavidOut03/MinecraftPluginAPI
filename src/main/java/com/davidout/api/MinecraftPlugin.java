package com.davidout.api;

import com.davidout.api.custom.command.CommandManager;
import com.davidout.api.custom.command.CustomCommand;
import com.davidout.api.custom.command.enchantment.EnchantmentManager;
import com.davidout.api.custom.file.FileManager;
import com.davidout.api.custom.file.PluginFile;
import com.davidout.api.custom.gui.GUIManager;
import com.davidout.api.listener.ArmorListener;
import com.davidout.api.listener.LeaveListener;
import com.davidout.api.custom.scoreboard.ScoreboardManager;
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
    private ScoreboardManager scoreboardManager;
    private CommandManager commandManager;
    private FileManager fileManager;
    private GUIManager guiManager;
    private PluginManager pm;


    @Override
    public void onEnable() {
        plugin = this;

        this.enchantmentManager = new EnchantmentManager(this);
        this.commandManager = new CommandManager(this);
        this.scoreboardManager = new ScoreboardManager();
        this.fileManager = new FileManager();
        this.guiManager = new GUIManager();
        this.pm = Bukkit.getPluginManager();



        // register all necesarry listeners
        this.registerNecesarryListeners();

        // register all the events and commands.
        this.registerEvents().forEach (eventListener -> this.pm.registerEvents(eventListener, this) );
        this.commandManager.registerCommands ( this.registerCommands() );
        this.enchantmentManager.registerEnchantments();

        this.fileManager.setFiles(filesToCreate());
        this.fileManager.createFiles();
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
        this.pm.registerEvents(new LeaveListener(), this);
    }


    /**
     *
     *  GETTERS
     *
     */

    public EnchantmentManager getEnchantmentManager() {return this.enchantmentManager;}
    public ScoreboardManager getScoreboardManager() {return this.scoreboardManager;}
    public CommandManager getCommandManager() {return this.commandManager;}
    public FileManager getFileManager() {return this.fileManager;}
    public GUIManager getGuiManager() {return this.guiManager;}
    public PluginManager getPluginManager() {return this.pm;}



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
    public abstract List<PluginFile> filesToCreate();




}
