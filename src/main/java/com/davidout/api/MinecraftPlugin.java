package com.davidout.api;

import com.davidout.api.custom.command.CommandManager;
import com.davidout.api.custom.command.CustomCommand;
import com.davidout.api.custom.enchantment.EnchantmentManager;
import com.davidout.api.custom.file.FileManager;
import com.davidout.api.custom.file.PluginFile;
import com.davidout.api.custom.file.PluginFolder;
import com.davidout.api.custom.gui.GUIManager;
import com.davidout.api.custom.language.LanguageManager;
import com.davidout.api.custom.language.TranslationBundle;
import com.davidout.api.listener.ArmorListener;
import com.davidout.api.listener.LeaveListener;
import com.davidout.api.custom.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Arrays;
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
    private GUIManager guiManager;
    private PluginManager pm;


    @Override
    public void onEnable() {
        // Register all necesarry managers
        plugin = this;

        this.loadManagers();;
        this.register();
        this.createFiles();

        LanguageManager.loadTranslations();
        LanguageManager.setLanguageBundle(LanguageManager.getCurrentLanguage(), getDefaultTranslationBundle());

        this.onStartup();
    }

    @Override
    public void onDisable() {
        this.enchantmentManager.unRegisterEnchantments();
        this.onShutdown();
    }



    /**
     *
     *  Onenable methods
     *
     */

    private void loadManagers() {
        this.enchantmentManager = new EnchantmentManager(this);
        this.commandManager = new CommandManager(this);
        this.scoreboardManager = new ScoreboardManager();
        this.guiManager = new GUIManager();
        this.pm = Bukkit.getPluginManager();
    }

    private void register() {
        this.registerNecesarryListeners();
        this.registerEvents().forEach (eventListener -> this.pm.registerEvents(eventListener, this) );
        this.commandManager.registerCommands ( this.registerCommands() );
        this.enchantmentManager.registerEnchantments();
    }

    private void createFiles() {
        FileManager.setFiles(filesToCreate());
        FileManager.setFolderList(foldersToCreate());

        FileManager.createFolders();
        FileManager.createFiles();

        addLanguageFiles();
    }

    private void addLanguageFiles() {
        PluginFolder folder = LanguageManager.getFolder();
        PluginFile file = new PluginFile(LanguageManager.getFolder(), LanguageManager.getCurrentLanguage());

        FileManager.addFolder(folder);
        FileManager.addFile(file);

        try {
            file.setData("message.example", "This is a test message.");
            file.saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     *
     *  Sub methods
     *
     */

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
    public GUIManager getGuiManager() {return this.guiManager;}
    public PluginManager getPluginManager() {return this.pm;}


    /**
     *
     * Methods which can be overidden
     *
     */

    public TranslationBundle getDefaultTranslationBundle() {
        TranslationBundle bundle = new TranslationBundle("en");
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
