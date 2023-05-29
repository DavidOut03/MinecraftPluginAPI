package com.davidout.api.file;

import com.davidout.api.MinecraftPlugin;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
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
    public String getAbsoluteFolderPath() {return dataFolder.getAbsolutePath() + "/" + folderPath + "/" + fileName + ".yml";}


    public PluginFile(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.fileName = fileName;
    }

    public void generateFile() throws IOException {
        this.dataFolder = MinecraftPlugin.getPlugin().getDataFolder();
        this.file = new File(dataFolder, folderPath + "/" + fileName + ".yml");


        if(!dataFolder.exists()) dataFolder.mkdirs();
        if(file.exists()) return;
        file.createNewFile();

        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void reloadFile() {this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);}

    public void saveFile() throws IOException {
        if(!dataFolder.exists()) dataFolder.mkdirs();
        if(!file.exists())  file.createNewFile();
       this.yamlConfiguration.save(file);
    }

    public void setData(String dataPath, Object value) {yamlConfiguration.set(dataPath, value);}
    public Object getData(String dataPath) {return yamlConfiguration.get(dataPath);}

    public String getString(String dataPath) {return (String) yamlConfiguration.get(dataPath);}
    public boolean getBoolean(String dataPath) {return (boolean) yamlConfiguration.get(dataPath);}
    public List<String> getList(String dataPath) {return (List) yamlConfiguration.get(dataPath);}
    public int getNumber(String dataPath) {return Integer.parseInt((String) yamlConfiguration.get(dataPath));}
    public double getDouble(String dataPath) { return Double.parseDouble( (String) yamlConfiguration.get(dataPath));}
    public double getFloat(String dataPath) { return Float.parseFloat( (String) yamlConfiguration.get(dataPath));}
    public ConfigurationSection getSection(String dataPath) {return yamlConfiguration.getConfigurationSection(dataPath);}
    public Set<String> getSectionChildren(String dataPath) {return yamlConfiguration.getConfigurationSection(dataPath).getKeys(false);}

}
