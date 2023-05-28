# MinecraftPluginAPI
MinecraftPluginAPI is a Minecraft Spigot Plugin tool which simplifies the process of creating your own minecrafg plugin.

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
            <version>1.0.1</version>
        </dependency>
```

**Use `version-LEGACY` (if you want to use the tool for `1.13` and below)**
```xml
        <dependency>
            <groupId>com.davidout.api.minecraft</groupId>
            <artifactId>minecraft-plugin-api</artifactId>
            <version>1.0.1-LEGACY</version>
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

### How do i create my own custom command and sub commands?

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

#### Create your subcommand
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
### How do i create my own custom enchantments?
```java
public class Speed extends CustomEnchantment {
    public Speed(String name, int maxLevel) {
        super(name, maxLevel);
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

#### How do i add the enchantment to an item?
```java
  EnchantmentManager.addCustomEnchantment(p.getInventory().getItemInMainHand(), "speed", 1);
```

### How do i create my own GUIS?
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

#### How can i add it to the plugin?
```java
      this.getGuiManager().addGUI(new ExampleGUI());
```


#### How can a player open the GUI?
```java
        ExampleGUI gui1 = new ExampleGUI();
        gui1.openInventory(player);
```
   




See [LICENSE.md](LICENSE.md) for license information.
