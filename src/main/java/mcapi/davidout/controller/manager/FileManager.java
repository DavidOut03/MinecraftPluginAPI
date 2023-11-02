package mcapi.davidout.controller.manager;

import mcapi.davidout.model.custom.file.PluginFile;
import mcapi.davidout.model.custom.file.PluginFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileManager {

    private static List<PluginFile> fileList = new ArrayList<>();
    private static List<PluginFolder> folderList = new ArrayList<>();

    public static PluginFile getFile(String fileName) {
        Optional<PluginFile> file = fileList.stream().filter(pluginFile -> pluginFile.getFileName().equalsIgnoreCase(fileName)).collect(Collectors.toList()).stream().findFirst();
        return file.orElse(null);
    }

    public static PluginFolder getFolder(String folderName) {
        Optional<PluginFolder> folder = folderList.stream().filter(pluginFolder -> pluginFolder.getFolderName().equalsIgnoreCase(folderName)).collect(Collectors.toList()).stream().findFirst();
        return folder.orElse(null);
    }

    public static PluginFile createFile(PluginFolder folder, String fileName) {
        PluginFile file = new PluginFile(folder, fileName);
        fileList.add(file);
        return file;
    }

    public static void addFile(PluginFile file) {
        List<PluginFile> newList = new ArrayList<>(fileList);
        newList.add(file);
        fileList = newList;
    }
    public static void removeFile(PluginFile file) {fileList.remove(file);}
    public static void setFiles(List<PluginFile> files) {fileList = (files == null) ? new ArrayList<>() : files;}

    public static void addFolder(PluginFolder folder) {
        List<PluginFolder> newList = new ArrayList<>(folderList);
        newList.add(folder);
        folderList = newList;
    }
    public static void removeFolder(PluginFolder folder) {folderList.remove(folder);}
    public static void setFolderList(List<PluginFolder> list) {folderList = list;}

    public static void createFolders() {folderList.forEach(PluginFolder::createPath);}

    public static void createFiles() {
        fileList.forEach(file -> {
            try {
                file.generateFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create the file: " + file.getFileName() + "\n" +
                        "folder path: " + file.getAbsolutePath()+ "\n" +
                        " because of an error:", e);

            }
        });
    }

    public static void saveFiles() throws IOException {
        for (PluginFile pluginFile : fileList) {
            pluginFile.saveFile();
        }
    }

    public static void reloadFiles() {
        for (PluginFile pluginFile : fileList) {
            pluginFile.reloadFile();
        }
    }
}
