package mcapi.davidout.manager.language;

import mcapi.davidout.MinecraftPlugin;
import mcapi.davidout.MinecraftPluginManager;
import mcapi.davidout.manager.file.IFileManager;
import mcapi.davidout.manager.language.bundle.IMessageBundle;
import mcapi.davidout.manager.language.bundle.MessageBundle;
import mcapi.davidout.manager.language.message.MessageKey;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MessageManager implements IMessageManager {

    private final List<IMessageBundle> messageBundles;
    private final IFileManager fileManager;
    private IMessageBundle currentBundle;

    public MessageManager(IFileManager fileManager) {
        this.fileManager = fileManager;
        this.messageBundles = new ArrayList<>();
        this.loadMessageBundles();
    }

    @Override
    public IMessageBundle getCurrentBundle() {
        return currentBundle;
    }

    @Override
    public List<IMessageBundle> getBundles() {
        return messageBundles;
    }

    @Override
    public void setCurrentBundle(String bundleName) {
        Optional<IMessageBundle> bundle = messageBundles.stream().filter(iMessageBundle -> iMessageBundle.getName().equalsIgnoreCase(bundleName)).findFirst();
        if(bundle.isEmpty()) return;
        this.currentBundle = bundle.get();
    }


    @Override
    public String getMessage(MessageKey key) {
        return getMessageFromBundle(currentBundle, key);
    }

    @Override
    public String getMessage(String key) {
        return getMessageFromBundle(currentBundle, key);
    }

    @Override
    public String getMessageFromBundle(IMessageBundle bundle, MessageKey key) {
        return getMessageFromBundle(bundle, key.getKey());
    }

    @Override
    public String getMessageFromBundle(IMessageBundle bundle, String key) {
        return bundle.getMessages().entrySet().stream().
                filter(entry -> entry.getKey().equalsIgnoreCase(key)).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    @Override
    public void addMessageBundle(IMessageBundle bundle) {
        messageBundles.add(bundle);
    }

    @Override
    public boolean loadMessageBundles() {
        File baseFolder = fileManager.getBaseFolder();

        if (baseFolder == null || !baseFolder.exists()) {
            return false;
        }

        File[] yamlFiles = baseFolder.listFiles((dir, name) -> name.endsWith(".yaml"));

        if (yamlFiles == null) {
            return false;
        }

        messageBundles.clear();

        for (File file : yamlFiles) {
            try {
                String bundleName = file.getName().replace(".yaml", "");
                Map<String, String> messages = fileManager.loadFile(SavedBundle.class, file.getName()).messages;

                Optional<IMessageBundle> existingBundle = messageBundles.stream()
                        .filter(bundle -> bundle.getName().equalsIgnoreCase(bundleName))
                        .findFirst();

                existingBundle.ifPresent(messageBundles::remove);
                messageBundles.add(new MessageBundle(bundleName, messages));
            } catch (IOException e) {
                System.err.println("Could not load message bundle: " + file.getName());
                e.printStackTrace();
            }
        }

        currentBundle = messageBundles.isEmpty() ? null : messageBundles.get(0);
        return true;
    }

    @Override
    public boolean saveMessageBundles()  {
        for (IMessageBundle bundle : this.messageBundles) {
            try {
                fileManager.saveFile(new SavedBundle(bundle.getMessages()), bundle.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
}

class SavedBundle {
    public Map<String, String> messages;

    public SavedBundle(Map<String, String> messages) {
        this.messages = messages;
    }

    public SavedBundle() {
        this.messages = new HashMap<>();
    }
}
