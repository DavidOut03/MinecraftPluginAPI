package com.davidout.api.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private List<PluginFile> fileList;

    public FileManager() {
        this.fileList = new ArrayList<>();
    }

    public PluginFile createFile(String folderpath, String fileName) {
        PluginFile file = new PluginFile(folderpath, fileName);
        this.fileList.add(file);
        return file;
    }

    public void addFile(PluginFile file) {this.fileList.add(file);}
    public void removeFile(PluginFile file) {this.fileList.remove(file);}
    public void setFiles(List<PluginFile> files) {this.fileList = (files == null) ? new ArrayList<>() : files;}

    public void createFiles() {
        this.fileList.forEach(file -> {
            try {
                file.generateFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create the file: " + file.getFileName() + "\n" +
                        "folder path: " + file.getFolderPath() + "\n" +
                        " because of an error:", e);

            }
        });
    }

    public void saveFiles() throws IOException {
        for (PluginFile pluginFile : this.fileList) {
            pluginFile.saveFile();
        }
    }

    public void reloadFiles() {
        for (PluginFile pluginFile : this.fileList) {
            pluginFile.reloadFile();
        }
    }
}
