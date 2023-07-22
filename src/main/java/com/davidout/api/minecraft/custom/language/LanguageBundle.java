package com.davidout.api.minecraft.custom.language;

import java.util.HashMap;

public class LanguageBundle {
    private final HashMap<String, String> messages;
    private final String language;

    public LanguageBundle(String language) {
        this.language = language;
        this.messages = new HashMap<>();
    }

    public String getLanguage() {return this.language;}
    public String getMessage(String key) {return messages.get(key);}
    public void setMessage(String key, String value) {this.messages.put(key, value);}

    public HashMap<String, String> getMessages() {return messages;}
}
