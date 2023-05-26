package com.davidout.api;

import com.davidout.api.command.CommandManager;
import com.davidout.api.command.CustomCommand;
import com.davidout.api.gui.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class MinecraftPlugin extends JavaPlugin {

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
        this.pm = Bukkit.getPluginManager();
        this.commandManager = new CommandManager(this);
        this.guiManager = new GUIManager();

        // register all the events and commands.
        this.registerEvents().forEach (eventListener -> this.pm.registerEvents(eventListener, this) );
        this.commandManager.registerCommands ( this.registerCommands() );

        // register the click event for the guis.
        this.pm.registerEvents(this.guiManager, this);

        this.onStartup();
    }

    @Override
    public void onDisable() {
        this.onShutdown();
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
