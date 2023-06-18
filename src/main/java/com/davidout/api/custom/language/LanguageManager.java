package com.davidout.api.custom.language;

import com.davidout.api.custom.file.FileManager;
import com.davidout.api.custom.file.PluginFile;
import com.davidout.api.custom.file.PluginFolder;
import com.davidout.api.utillity.text.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

public class LanguageManager {

    private static final Map<String, LanguageBundle> languageBundles = new HashMap<>();
    private static final PluginFolder folder = new PluginFolder("/language");
    private static String currentLanguage = "default";


    public static String getCurrentLanguage() {return currentLanguage;}
    public static void setLanguage(String lang) {currentLanguage = lang;}

    public static String getTranslation(String key) {
        return getTranslation(currentLanguage, key);
    }
    public static String getTranslation(String language, String key) {
        String returned = (languageBundles.get(language) == null || languageBundles.get(language).getMessage(key) == null) ? "No Translation" : languageBundles.get(language).getMessage(key);
        return returned;
    }

    public static PluginFolder getFolder() {return folder;}

    public static void setLanguageBundle(String languageBundle, LanguageBundle translationBundle) {
        languageBundles.put(languageBundle, translationBundle);
        saveTranslationBundle(languageBundle, translationBundle);
    }

    public static void loadTranslations() {
        if(folder.getFolder() == null) return;

        folder.getFilesInFolder().forEach(cf -> {
            if(!cf.getName().endsWith(".yml")) return;
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(cf);
            String languageName = cf.getName().replace(".yml", "");
            LanguageBundle bundle = new LanguageBundle(languageName);

            if(yamlConfiguration.getConfigurationSection("message") == null) return;
            for(String currentKey : yamlConfiguration.getConfigurationSection("message").getKeys(false)) {
                String value = yamlConfiguration.getString("message." + currentKey);
                bundle.setMessage(currentKey, value);
            }

            languageBundles.put(languageName, bundle);
        });
    }

    protected static void saveTranslationBundle(String language, LanguageBundle bundle) {
        PluginFile file = FileManager.getFile(language);
        if(file == null) {
            file = FileManager.createFile(folder, language);
            file.getFileName();
        }

        try {
            for(Map.Entry<String, String> currentMessage : bundle.getMessages().entrySet()) {
                file.setData("message." + currentMessage.getKey(), currentMessage.getValue());
            }
            file.saveFile();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(TextUtils.formatColorCodes("&cCould not save language file: " + language + ".yml"));
            ex.printStackTrace();
        }
    }

}
