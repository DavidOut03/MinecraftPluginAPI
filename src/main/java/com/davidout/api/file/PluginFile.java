package com.davidout.api.file;

import com.davidout.api.MinecraftPlugin;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PluginFile {

    private YamlConfiguration yamlConfiguration;
    private String folderPath;
    private String fileName;

    private File file;
    private File dataFolder;

    public String getFileName() {return fileName;}
    public String getFolderPath() {return folderPath;}
    public String getAbsoluteFolderPath() {return this.file.getAbsolutePath();}


    public PluginFile(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.fileName = fileName;
    }

    public void generateFile() throws IOException {
        this.dataFolder = MinecraftPlugin.getPlugin().getDataFolder();


        if(!dataFolder.exists()) dataFolder.mkdir();
        this.file = new File(dataFolder, folderPath + "/" + fileName + ".yml");
        if(file.exists()) return;
        file.createNewFile();

        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void reloadFile() {this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);}

    public void saveFile() throws IOException {
        if(!dataFolder.exists()) dataFolder.mkdir();
        if(!file.exists())  file.createNewFile();
       this.yamlConfiguration.save(file);
    }

    public void setData(String dataPath, Object value) {yamlConfiguration.set(dataPath, value);}
    public Object getData(String dataPath) {return (yamlConfiguration == null) ? null : yamlConfiguration.get(dataPath);}

    public String getString(String dataPath) {return (yamlConfiguration == null) ? "" : (String) yamlConfiguration.get(dataPath);}
    public boolean getBoolean(String dataPath) {return (yamlConfiguration == null) ? false : (boolean) yamlConfiguration.get(dataPath);}
    public List<String> getList(String dataPath) {return (yamlConfiguration == null) ? new ArrayList<>() : (List<String>) yamlConfiguration.get(dataPath);}
    public int getNumber(String dataPath) {return (yamlConfiguration == null) ? 0 : Integer.parseInt((String) yamlConfiguration.get(dataPath));}
    public double getDouble(String dataPath) { return (yamlConfiguration == null) ? 0 : Double.parseDouble((String) yamlConfiguration.get(dataPath));}
    public double getFloat(String dataPath) { return (yamlConfiguration == null) ? 0 : Float.parseFloat((String) yamlConfiguration.get(dataPath));}
    public ConfigurationSection getSection(String dataPath) {return (yamlConfiguration == null) ? null : yamlConfiguration.getConfigurationSection(dataPath);}
    public List<String> getSectionChildren(String dataPath) {return (yamlConfiguration == null) ? new ArrayList<>() : new ArrayList<>(yamlConfiguration.getConfigurationSection(dataPath).getKeys(false));}

}
