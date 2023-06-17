package com.davidout.api.custom.language;

import com.davidout.api.custom.file.FileManager;
import com.davidout.api.custom.file.PluginFile;
import com.davidout.api.custom.file.PluginFolder;
import com.davidout.api.utillity.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LanguageManager {

    private static final Map<String, TranslationBundle> languageBundles = new HashMap<>();
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


    public static void loadTranslations() {
        if(folder.getFolder() == null) return;

        folder.getFilesInFolder().forEach(cf -> {
            if(!cf.getName().endsWith(".yml")) return;
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(cf);
            String languageName = cf.getName().replace(".yml", "");
            TranslationBundle bundle = new TranslationBundle(languageName);

            if(yamlConfiguration.getConfigurationSection("message") == null) return;
            for(String currentKey : yamlConfiguration.getConfigurationSection("message").getKeys(false)) {
                String value = yamlConfiguration.getString("message." + currentKey);
                bundle.setMessage(currentKey, value);
            }

            languageBundles.put(languageName, bundle);
        });
    }

    public static void saveTranslations() {
        languageBundles.forEach((s, translationBundle) -> {
            PluginFile file = FileManager.getFile(s);
            if(file == null) {
                file = FileManager.createFile(folder, s);
                file.getFileName();
            }

            try {
                for(Map.Entry<String, String> currentMessage : translationBundle.getMessages().entrySet()) {
                    file.setData("message." + currentMessage.getKey(), currentMessage.getValue());
                }
                file.saveFile();
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage(TextUtils.formatColorCodes("&cCould not save language file: " + s + ".yml"));
                ex.printStackTrace();
            }

        });
    }
}
