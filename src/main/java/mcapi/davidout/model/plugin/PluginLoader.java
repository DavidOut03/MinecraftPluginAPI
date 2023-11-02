package mcapi.davidout.model.plugin;

import mcapi.davidout.MinecraftPlugin;
import mcapi.davidout.controller.manager.FileManager;
import mcapi.davidout.model.custom.file.PluginFile;
import mcapi.davidout.model.custom.file.PluginFolder;
import mcapi.davidout.model.custom.language.LanguageBundle;
import mcapi.davidout.controller.manager.LanguageManager;
import mcapi.davidout.controller.listener.ArmorListener;
import mcapi.davidout.controller.listener.LeaveListener;

import java.io.IOException;
import java.util.Map;

public class PluginLoader {

    private MinecraftPlugin plugin;

    public PluginLoader(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }


    public void loadPlugin() {
        this.register();
        this.createFiles();
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

        addLanguageFiles();

        FileManager.createFolders();
        FileManager.createFiles();
    }

    private void addLanguageFiles() {
        LanguageBundle bundle = plugin.getDefaultTranslationBundle();
        LanguageManager.setLanguage(bundle.getLanguage());
        LanguageManager.setLanguageBundle(bundle.getLanguage(), bundle);


        PluginFolder folder = LanguageManager.getFolder();

        if(FileManager.getFile(bundle.getLanguage()) == null) {
            PluginFile file = FileManager.createFile(folder, bundle.getLanguage());

            try {
                for(Map.Entry<String, String> messages : bundle.getMessages().entrySet()) {
                    file.setData("message." + messages.getKey(), messages.getValue());
                }
                file.saveFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        LanguageManager.loadTranslations();
    }

}
