package com.davidout.api.custom.file;

import com.davidout.api.MinecraftPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PluginFolder {

    private final File dataFolder;
    private final String path;


    public PluginFolder(String path) {
        this.path = path;
        this.dataFolder = MinecraftPlugin.getPlugin().getDataFolder();
    }

    public boolean createPath() {
        if(dataFolder.exists()) return true;
        boolean worked = dataFolder.mkdir();

        if(!worked) return false;
        if(new File(dataFolder, path).exists()) return true;
        return new File(dataFolder, path).mkdir();
    }

    public boolean pathExists() {
        return dataFolder.exists() && new File(dataFolder, path).exists();
    }


    public String getAbsoluteFolderPath() {return this.getFolder().getAbsolutePath();}
    public String getPath() {return this.path;}
    public String getFolderName() {return this.path.split("/")[this.path.split("/").length - 1];}

    public File getFolder() {
       return new File(dataFolder, path);
    }

    public List<File> getFilesInFolder() {
        if(getFolder() == null) return Collections.emptyList();
        return Arrays.asList(Objects.requireNonNull(this.getFolder().listFiles()));
    }

}
