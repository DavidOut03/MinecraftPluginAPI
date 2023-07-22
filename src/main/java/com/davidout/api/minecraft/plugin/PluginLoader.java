package com.davidout.api.minecraft.plugin;

import com.davidout.api.minecraft.MinecraftPlugin;
import com.davidout.api.minecraft.custom.file.FileManager;
import com.davidout.api.minecraft.custom.file.PluginFile;
import com.davidout.api.minecraft.custom.file.PluginFolder;
import com.davidout.api.minecraft.custom.language.LanguageBundle;
import com.davidout.api.minecraft.custom.language.LanguageManager;
import com.davidout.api.minecraft.listener.ArmorListener;
import com.davidout.api.minecraft.listener.LeaveListener;

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
        LanguageBundle bundle = plugin.getDefaultTranslationBundle();
        LanguageManager.setLanguageBundle(bundle.getLanguage(), bundle);
        LanguageManager.setLanguage(bundle.getLanguage());
    }

    public void unLoadPlugin() {
        plugin.getEnchantmentManager().unRegisterEnchantments();
    }


    /**
     *  Register everyting
     */


    private void register() {
        this.registerNecesarryListeners();
        if(plugin.registerEvents() != null) plugin.registerEvents().forEach (eventListener -> plugin.getPluginManager().registerEvents(eventListener, plugin) );
        if(plugin.registerCommands() != null) plugin.getCommandManager().registerCommands ( plugin.registerCommands() );
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
