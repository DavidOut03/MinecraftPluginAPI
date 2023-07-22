package com.davidout.api.minecraft.custom.file;

import com.davidout.api.minecraft.MinecraftPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PluginFolder extends File {
    private final File dataFolder;

    public PluginFolder(String path) {
        super(MinecraftPlugin.getPlugin().getDataFolder(), path);
        this.dataFolder = MinecraftPlugin.getPlugin().getDataFolder();
    }

    public PluginFolder(PluginFolder folder, String name) {
        super(folder.getPath(), name);
        this.dataFolder = MinecraftPlugin.getPlugin().getDataFolder();
    }

    public void createPath() {
        if(!dataFolder.exists()) dataFolder.mkdir();
        if(this.exists()) return;
        this.mkdir();
    }

    public boolean pathExists() {return dataFolder.exists() && this.exists();
    }

    public String getFolderName() {return getPath().split("/")[getPath().split("/").length - 1];}


    public List<File> getFilesInFolder() {
        if(!this.exists()) return Collections.emptyList();
        return Arrays.asList(Objects.requireNonNull(this.listFiles()));
    }

}
