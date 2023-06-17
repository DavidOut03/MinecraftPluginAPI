# MinecraftPluginAPI
MinecraftPluginAPI is a Minecraft Spigot Plugin tool which simplifies and speeds up the process of creating your own minecraft plugin.

# Index
-  [How do i install this library?](#how-to-install)
-  [How can i create my first plugin?](#create-your-own-plugin)
-  [How can i create my own command?](#how-do-i-create-my-own-custom-command-and-sub-commands)
-  [How can i edit the language and their messages?](#how-do-i-manage-the-languages)
-  [How can i create my own custom enchantments?](#how-do-i-create-my-own-custom-enchantments)
-  [How do i create a custom GUI?](#how-do-i-create-my-own-guis)
-  [How do i create my own scoreboard?](#how-can-i-create-a-custom-scoreboard)
-  [How do i manage my files?](#how-do-i-manage-my-files)

## How to Install
You can use this maven repository.

Add tese lines to youre pom.xml file.

**Repository**:
My github packages repository.

```xml
        <repository>
            <id>MinecraftPluginAPI</id>
            <url>https://maven.pkg.github.com/DavidOut03/MinecraftPluginAPI</url>
        </repository>
```



**Dependency (type latest version in for `version`)**
```xml
        <dependency>
            <groupId>com.davidout.api.minecraft</groupId>
            <artifactId>minecraft-plugin-api</artifactId>
            <version>1.3.0</version>
        </dependency>
```

**Use `version-LEGACY` (if you want to use the tool for `1.13` and below)**
```xml
        <dependency>
            <groupId>com.davidout.api.minecraft</groupId>
            <artifactId>minecraft-plugin-api</artifactId>
            <version>1.3.0-LEGACY</version>
        </dependency>
```
**Add this to your shaded maven plugin configuration**
```xml
    <configuration>
       <createDependencyReducedPom>false</createDependencyReducedPom>
       <shadedArtifactAttached>true</shadedArtifactAttached>                
     </configuration>
                          
```


## Create your own plugin
#### This is how you could create an example plugin

```java
// YourPlugin should be the name of your Main class
public class YourPlugin extends MinecraftPlugin {

    @Override
    public void onStartup() {
        this.getGuiManager().addGUI(new ExampleGUI());
        this.getGuiManager().addGUI(new ExampleGUI2());

        getEnchantmentManager().addEnchantment(new Speed("speed", 2));
    }

    @Override
    public void onShutdown() {}

    @Override
    public List<Listener> registerEvents() {
        return Arrays.asList(new TestEvent());
    }

    @Override
    public List<CustomCommand> registerCommands() {
        return Arrays.asList(new ExampleCommand());
    }

}
```

## How do i manage the languages

```java
import com.davidout.api.custom.language.LanguageManager;

public class YourPlugin extends MinecraftPlugin {

    @Override
    public void onStartup() {
        // use this method to set the current language.
        LanguageManager.setLanguage("en");
    }
    
    // You can override the default language bundle with this method.
    @Override
    public TranslationBundle getDefaultTranslationBundle() {
        TranslationBundle bundle = new TranslationBundle("en");
        bundle.setMessage("example", "This is a test message.");
        bundle.setMessage("onEnable", "&aEnabled custom enchantments plugin.");
        bundle.setMessage("onDisable", "&cDisabled custom enchantments plugin.");

        return bundle;
    }
}
```

```yaml
/* You can edit all the messages in the /language/default.yml or /langauage/(language).yml \*
message:
  example: 'This is a test message.'
  onEnable: '&aEnabled custom enchantments plugin.'
  onDisable: '&cDisabled custom enchantments plugin.'
```


## How do i create my own custom command and sub commands?

```java
public class ExampleCommand implements CustomCommand {

    public String getCommandName() {
        return "example";
    }


    public String getUsage() {
        return "/example";
    }


    public List<String> getAliases() {
        return new ArrayList<>();
    }


    public List<CustomCommand> getSubCommands() {
        return Arrays.asList(new OpenCommand());
    }


    public boolean executeCommand(CommandSender commandSender, String[] strings) {
        if(!(commandSender instanceof  Player)) return false;
        Player p = (Player) commandSender;
        EnchantmentManager.addCustomEnchantment(p.getInventory().getItemInMainHand(), "speed", 1);
        return false;
    }

    public List<String> autoCompleteCommand(CommandSender commandSender, String[] strings) {
        if(strings.length == 1) {
            return Arrays.asList("open");
        }


        return Arrays.asList("");
    }
}

```

### Create your subcommand
```java
public class OpenCommand implements CustomCommand {
    @Override
    public String getCommandName() {
        return "open";
    }

    @Override
    public String getUsage() {
        return "/example open <inventory>";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }


    @Override
    public List<CustomCommand> getSubCommands() {
        return null;
    }


    @Override
    public boolean executeCommand(CommandSender commandSender, String[] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage(getUsage());
            return true;
        }

        if(strings[0].equalsIgnoreCase("gui1")) {
            ExampleGUI gui1 = new ExampleGUI();
            gui1.openInventory((Player) commandSender);
            return true;
        }

        if(strings[0].equalsIgnoreCase("gui2")) {
            ExampleGUI2 gui2 = new ExampleGUI2();
            gui2.openInventory((Player) commandSender);
            return true;
        }
        return false;
    }

    @Override
    public List<String> autoCompleteCommand(CommandSender commandSender, String[] strings) {
        return  Arrays.asList("gui1", "gui2");
    }
}
```
## How do i create my own custom enchantments?

### Option 1
```java
public class Speed extends CustomEnchantment {
    
    public Speed() {
        super(new EnchantmentDetails("speed", 2, "Receive a speed boost with these boots.", EnchantmentTarget.BOOTS));
    }

    @Override
    public List<Class<? extends Event>> getEvents() {
        return Arrays.asList(ArmorEquipEvent.class, ArmorDequipEvent.class);
    }

    @Override
    public void onAction(Event event) {
        if (event instanceof ArmorEquipEvent) {
            ArmorEquipEvent event1 = (ArmorEquipEvent) event;
            event1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2), true);
        }

        if (event instanceof ArmorDequipEvent) {
            ArmorDequipEvent event1 = (ArmorDequipEvent) event;
            event1.getPlayer().removePotionEffect(PotionEffectType.SPEED);
        }
    }
}
```

### Register the enchantment

```java

// register the enchantment

public class YourPlugin extends MinecraftPlugin {

    @Override
    public void onStartup() {
        getEnchantmentManager().addEnchantment(new Speed());
    }
}

```

### Option 2

```java

import com.davidout.api.custom.enchantment.CustomEnchantment;
import com.davidout.api.custom.enchantment.EnchantmentDetails;
import com.davidout.api.custom.enchantment.factory.EnchantmentFactory;
import com.davidout.api.custom.event.ArmorEquipEvent;

public class YourPlugin extends MinecraftPlugin {

    @Override
    public void onStartup() {
        EnchantmentDetails enchantmentDetails = new EnchantmentDetails("speed", 2);
        
        CustomEnchantment enchantment = EnchantmentFactory.createEnchantment(enchantmentDetails, ArmorEquipEvent.class, event -> {
        
            // your logic
        });
        
        getEnchantmentManager().addEnchantment(enchantment);
    }
}

```




### How do i add the enchantment to an item?
```java
  int level = 2; 
  CustomEnchantment enchantment = new Speed();
  EnchantmentManager.addCustomEnchantment(p.getInventory().getItemInMainHand(), /* Enchantment name or class */ enchantment, level);
```

## How do i create my own GUIS?
```java
public class ExampleGUI extends GUI {

    public ExampleGUI() {
        super(6, "&cTest");
    }

    @Override
    public void createInventory() {
        addItem(new ItemStack(Material.DIAMOND_BLOCK));
    }

    @Override
    public void onClick(InventoryClickEvent inventoryClickEvent, Player player) {
        inventoryClickEvent.setCancelled(true);

        if(inventoryClickEvent.getCurrentItem().getType().equals(Material.DIAMOND_BLOCK)) {
            player.sendMessage(TextUtils.formatColorCodes("&cTest"));
        }
    }


}

```

### How can i add it to the plugin?
```java
      this.getGuiManager().addGUI(new ExampleGUI());
```


### How can a player open the GUI?
```java
        ExampleGUI gui1 = new ExampleGUI();
        gui1.openInventory(player);
```
   
## How can i create a custom scoreboard?
```java

public class Board extends CustomScoreboard {
    @Override
    public String getName() {
        return "board";
    }

    @Override
    public List<String> update(Player player) {
        setTitle("   &9Once A Year   ");

        return Arrays.asList(
                "&7&m                              ",
                "   &b&lPlay time: &f" + PlayTimeCounter.getFormatedTime(player),
                " ",
                "   &b&lX: &f" + player.getLocation().getBlockX(),
                "   &b&lY: &f" + player.getLocation().getBlockY(),
                "   &b&lZ: &f" + player.getLocation().getBlockZ(),
                "&c&7&m                              "
        );
    }

    @Override
    public boolean useUpdate() {
        return true;
    }

    @Override
    public long updateTimeInSeconds() {
        return (long) 1;
    }

}
```
### Register the scoreboard

```java
// register the scoreboard

public class YourPlugin extends MinecraftPlugin {

    @Override
    public void onStartup() {
        getScoreboardManager().registerScoreboard(board);
    }
}

```
### How i a add a player to a scoreboard
```java
// Set a players scoreboard
 YourPlugin.getPlugin().getScoreboardManager().setScoreboard(e.getPlayer(), /* the scoreboard you want */  board );

```

## How do i manage my files?

### Create a file.

```java

    PluginFolder folder = new PluginFolder("settings");
    PluginFile file = new PluginFile(folder, "settings");
    
    // Create the path and the file.
    folder.createPath();
    file.generateFile();

```

### Edit a file

```java

    PluginFolder folder = new PluginFolder("settings");
    PluginFile file = new PluginFile(folder, "settings");
    
    file.setData("settings.keepInventory", false);
    file.setData("settings.sayHello", true);
    file.saveFile();

```

### Reload a file

```java

    PluginFolder folder = new PluginFolder("settings");
    PluginFile file = new PluginFile(folder, "settings");
    
    file.reloadFile();

```



See [LICENSE.md](LICENSE.md) for license information.
