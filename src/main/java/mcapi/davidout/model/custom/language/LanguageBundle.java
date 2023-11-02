package mcapi.davidout.model.custom.language;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> getMessages() {return messages;}
}
