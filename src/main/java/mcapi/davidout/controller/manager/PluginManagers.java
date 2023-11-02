package mcapi.davidout.controller.manager;

import mcapi.davidout.MinecraftPlugin;
import mcapi.davidout.model.custom.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class PluginManagers {

    private final EnchantmentManager enchantmentManager;
    private final ScoreboardManager scoreboardManager;
    private final CommandManager commandManager;
    private final GUIManager guiManager;
    private final PluginManager pm;



    public PluginManagers(MinecraftPlugin plugin) {
            this.enchantmentManager = new EnchantmentManager(plugin);
            this.commandManager = new CommandManager(plugin);
            this.scoreboardManager = new ScoreboardManager();
            this.guiManager = new GUIManager();
            this.pm = Bukkit.getPluginManager();
    }

    public EnchantmentManager getEnchantmentManager() {return this.enchantmentManager;}
    public ScoreboardManager getScoreboardManager() {return this.scoreboardManager;}
    public CommandManager getCommandManager() {return this.commandManager;}
    public GUIManager getGuiManager() {return this.guiManager;}
    public PluginManager getPluginManager() {return this.pm;}

}
