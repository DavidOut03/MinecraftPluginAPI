package com.davidout.api.custom.file;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginFile {

    private YamlConfiguration yamlConfiguration;
    private File file;

    private final PluginFolder folder;
    private final String fileName;


    public String getFileName() {return fileName;}


    public PluginFile(PluginFolder folder, String fileName) {
        this.folder = folder;
        this.fileName = fileName;
        this.file = new File(folder.getFolder(), fileName + ".yml");
    }

    public void generateFile() throws IOException {
        if(!folder.pathExists()) folder.createPath();
        if(!file.exists()) file.createNewFile();
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void reloadFile() {this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);}

    public void saveFile() throws IOException {
        if(!folder.pathExists()) folder.createPath();
        if(!file.exists()) generateFile();
            this.yamlConfiguration.save(file);
    }

    public void setData(String dataPath, Object value) throws IOException {
        if(yamlConfiguration == null) this.generateFile();
            yamlConfiguration.set(dataPath, value);
    }


    public String getAbsoluteFolderPath() {return this.file.getAbsolutePath();}

    public Object getData(String dataPath) {return (yamlConfiguration == null) ? null : yamlConfiguration.get(dataPath);}

    public String getString(String dataPath) {
        return (yamlConfiguration == null || yamlConfiguration.get(dataPath) == null) ? "" : yamlConfiguration.get(dataPath).toString();
    }
    public boolean getBoolean(String dataPath) {return (yamlConfiguration == null) ? false : (boolean) yamlConfiguration.get(dataPath);}
    public List<String> getList(String dataPath) {return (yamlConfiguration == null) ? new ArrayList<>() : (List<String>) yamlConfiguration.get(dataPath);}
    public int getNumber(String dataPath) {
        if (yamlConfiguration == null || getString(dataPath) == null) return 0;

        try {
            return  Integer.parseInt(getString(dataPath));
        } catch (Exception ex) {
            return 0;
        }
    }

    public double getDouble(String dataPath) {
        if (yamlConfiguration == null || getString(dataPath) == null) return 0;

        try {
            return  Double.parseDouble(getString(dataPath));
        } catch (Exception ex) {
            return 0;
        }
    }

    public float getFloat(String dataPath) {
        if (yamlConfiguration == null || getString(dataPath) == null) return 0;

        try {
            return  Float.parseFloat(getString(dataPath));
        } catch (Exception ex) {
            return 0;
        }
    }

    public ConfigurationSection getSection(String dataPath) {return (yamlConfiguration == null) ? null : yamlConfiguration.getConfigurationSection(dataPath);}
    public List<String> getSectionChildren(String dataPath) {return (yamlConfiguration == null) ? new ArrayList<>() : new ArrayList<>(yamlConfiguration.getConfigurationSection(dataPath).getKeys(false));}


}
