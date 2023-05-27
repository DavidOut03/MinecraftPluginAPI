package com.davidout.api;

import com.davidout.api.command.CommandManager;
import com.davidout.api.command.CustomCommand;
import com.davidout.api.gui.GUIManager;
import com.davidout.api.listeners.ArmorListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class MinecraftPlugin extends JavaPlugin {

    public static MinecraftPlugin getInstance() {return instance;}
    private static MinecraftPlugin instance;

    /***
     *
     *   Class created by David Out
     *
     */

    private CommandManager commandManager;
    private GUIManager guiManager;
    private PluginManager pm;


    @Override
    public void onEnable() {
        instance = this;

        this.pm = Bukkit.getPluginManager();
        this.commandManager = new CommandManager(this);
        this.guiManager = new GUIManager();


        // register all necesarry listeners
        this.registerNecesarryListeners();

        // register all the events and commands.
        this.registerEvents().forEach (eventListener -> this.pm.registerEvents(eventListener, this) );
        this.commandManager.registerCommands ( this.registerCommands() );


        this.onStartup();
    }

    @Override
    public void onDisable() {
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

    public CommandManager getCommandManager() {return this.commandManager;}
    public PluginManager getPluginManager() {return this.pm;}
    public GUIManager getGuiManager() {return this.guiManager;}

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
