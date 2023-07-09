package com.davidout.api.plugin;

import com.davidout.api.custom.command.CustomCommand;
import com.davidout.api.custom.file.PluginFile;
import com.davidout.api.custom.file.PluginFolder;
import com.davidout.api.custom.language.LanguageBundle;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public abstract class PluginDetails extends JavaPlugin {

    /**
     *
     *  ABSTRACT METHODS which needs to be inplemented
     *
     */

    public abstract void onStartup();

    // this method is called when the plugin is stopped or disabled by the server.
    public abstract void onShutdown();

    // This method is called on startup and automatically adds
    public abstract List<Listener> registerEvents();

    // This method is called on startup to register the commands;
    public abstract List<CustomCommand> registerCommands();


    /**
     *
     *  METHODS which can be overridden.
     *
     */

    public LanguageBundle getDefaultTranslationBundle() {
        LanguageBundle bundle = new LanguageBundle("en");
        bundle.setMessage("example", "This is an example message.");
        bundle.setMessage("onEnable", "This plugin enabled.");
        bundle.setMessage("onDisable", "This plugin disabled.");

        return bundle;
    }

    public List<PluginFile> filesToCreate() {
        return Arrays.asList();
    }

    public List<PluginFolder> foldersToCreate() {
        return Arrays.asList();
    }
}
