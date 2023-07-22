package com.davidout.api.minecraft.plugin;

import com.davidout.api.minecraft.MinecraftPlugin;
import com.davidout.api.minecraft.custom.command.CommandManager;
import com.davidout.api.minecraft.custom.enchantment.EnchantmentManager;
import com.davidout.api.minecraft.custom.gui.GUIManager;
import com.davidout.api.minecraft.custom.scoreboard.ScoreboardManager;
import com.davidout.api.minecraft.database.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class PluginManagers {

    private final EnchantmentManager enchantmentManager;
    private final ScoreboardManager scoreboardManager;
    private final DatabaseManager databaseManager;
    private final CommandManager commandManager;
    private final GUIManager guiManager;
    private final PluginManager pm;



    public PluginManagers(MinecraftPlugin plugin) {
            this.enchantmentManager = new EnchantmentManager(plugin);
            this.commandManager = new CommandManager(plugin);
            this.scoreboardManager = new ScoreboardManager();
            this.guiManager = new GUIManager();
            this.pm = Bukkit.getPluginManager();
            this.databaseManager = new DatabaseManager();
    }

    public EnchantmentManager getEnchantmentManager() {return this.enchantmentManager;}
    public ScoreboardManager getScoreboardManager() {return this.scoreboardManager;}
    public DatabaseManager getDatabaseManager() {return this.databaseManager;}
    public CommandManager getCommandManager() {return this.commandManager;}
    public GUIManager getGuiManager() {return this.guiManager;}
    public PluginManager getPluginManager() {return this.pm;}

}
