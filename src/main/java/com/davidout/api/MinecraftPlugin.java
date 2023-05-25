package com.davidout.api;

import com.davidout.api.command.CommandManager;
import com.davidout.api.command.CustomCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;
import java.util.List;

public abstract class MinecraftPlugin extends JavaPlugin {

    /***
     *
     *   Class created by David Out
     *
     */

    private PluginManager pm;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        this.pm = Bukkit.getPluginManager();
        this.commandManager = new CommandManager(this);

        // register all the events and commands.
        this.registerEvents().forEach (eventListener -> this.pm.registerEvents(eventListener, this) );
        this.commandManager.registerCommands ( this.registerCommands() );

        super.onEnable();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    // This method is called when the plugin is started by the server.
    public abstract void onStartup();

    // this method is called when the plugin is stopped or disabled by the server.
    public abstract void onShutdown();

    // This method is called on startup and automaticly adds
    public abstract List<Listener> registerEvents();

    // This method is called on startup to register the commands;
    public abstract List<CustomCommand> registerCommands();




}
