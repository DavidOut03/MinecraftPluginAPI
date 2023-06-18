package com.davidout.api.plugin;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.custom.file.FileManager;
import com.davidout.api.custom.file.PluginFile;
import com.davidout.api.custom.file.PluginFolder;
import com.davidout.api.custom.language.LanguageManager;
import com.davidout.api.listener.ArmorListener;
import com.davidout.api.listener.LeaveListener;

import java.io.IOException;

public class PluginLoader {

    private MinecraftPlugin plugin;

    public PluginLoader(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }


    public void loadPlugin() {
        this.register();
        this.createFiles();

        LanguageManager.loadTranslations();
        LanguageManager.setLanguageBundle(plugin.getDefaultTranslationBundle().getLanguage(), plugin.getDefaultTranslationBundle());
        LanguageManager.setLanguage(plugin.getDefaultTranslationBundle().getLanguage());
    }

    public void unLoadPlugin() {
        plugin.getEnchantmentManager().unRegisterEnchantments();
    }


    /**
     *  Register everyting
     */


    private void register() {
        this.registerNecesarryListeners();
        plugin.registerEvents().forEach (eventListener -> plugin.getPluginManager().registerEvents(eventListener, plugin) );
        plugin.getCommandManager().registerCommands ( plugin.registerCommands() );
        plugin.getEnchantmentManager().registerEnchantments();
    }

    public void registerNecesarryListeners() {
        plugin.getPluginManager().registerEvents(plugin.getGuiManager(), plugin);
        plugin.getPluginManager().registerEvents(new ArmorListener(), plugin);
        plugin.getPluginManager().registerEvents(new LeaveListener(), plugin);
    }

    /**
     *
     * Create files and folders;
     *
     */

    private void createFiles() {
        FileManager.setFiles(plugin.filesToCreate());
        FileManager.setFolderList(plugin.foldersToCreate());

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

}
