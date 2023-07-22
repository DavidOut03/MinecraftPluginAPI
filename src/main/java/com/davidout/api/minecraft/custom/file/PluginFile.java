package com.davidout.api.minecraft.custom.file;

import com.davidout.api.minecraft.MinecraftPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginFile extends File {

    private YamlConfiguration yamlConfiguration;
    private final PluginFolder folder;
    private final String fileName;


    public String getFileName() {return fileName;}


    public PluginFile(PluginFolder folder, String fileName) {
        super(folder, fileName + ".yml");

        this.folder = folder;
        this.fileName = fileName;
    }

    public PluginFile(String fileName) {
        super(MinecraftPlugin.getPlugin().getDataFolder(), fileName + ".yml");
        this.folder = null;
        this.fileName = fileName;
    }

    public boolean fileExists() {return this.exists();}

    public void generateFile() throws IOException {
        if(folder != null && !folder.pathExists()) folder.createPath();
        if(!fileExists()) createNewFile();
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this);
    }

    public void reloadFile() {this.yamlConfiguration = YamlConfiguration.loadConfiguration(this);}

    public void saveFile() throws IOException {
        if(!folder.pathExists()) folder.createPath();
        if(!fileExists()) generateFile();
            this.yamlConfiguration.save(this);
    }

    public void setData(String dataPath, Object value) throws IOException {
        if(yamlConfiguration == null) this.generateFile();
            yamlConfiguration.set(dataPath, value);
    }

    public void setString(String dataPath, String value) throws IOException {
        if(yamlConfiguration == null) this.generateFile();
        yamlConfiguration.set(dataPath, value);
    }


    public Object getData(String dataPath) {return (yamlConfiguration == null) ? null : yamlConfiguration.get(dataPath);}

    public String getString(String dataPath) {
        return (yamlConfiguration == null || yamlConfiguration.get(dataPath) == null) ? "" : yamlConfiguration.get(dataPath).toString();
    }
    public boolean getBoolean(String dataPath) {return yamlConfiguration != null && (boolean) yamlConfiguration.get(dataPath);}
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
