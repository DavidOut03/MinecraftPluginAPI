package com.davidout.api.custom.command;

import com.davidout.api.MinecraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class CommandManager implements CommandExecutor, TabCompleter {

    private List<CustomCommand> commandList;
    private MinecraftPlugin plugin;

    public CommandManager(MinecraftPlugin plugin) {
        this.commandList = new ArrayList<>();
        this.plugin = plugin;
    }

    public boolean registerCommands(List<CustomCommand> commandList) {
        if(this.plugin == null || commandList == null) return false;

        commandList.forEach(customCommand -> {
            if(customCommand == null|| customCommand.getCommandName() == null) return;

            try {
                this.plugin.getCommand(customCommand.getCommandName()).setExecutor(this);
                this.plugin.getCommand(customCommand.getCommandName()).setTabCompleter(this);
                this.commandList.add(customCommand);
            } catch (Exception ex) {
                Bukkit.getLogger().log(Level.WARNING, "Could not register command: {c} because of an error: {e}".replace("{c}", customCommand.getCommandName()).replace("{e}", ex.toString()));
            }


        });

        return true;
    }


    private boolean equalsCommand(CustomCommand command, String alias) {
        if(command.getCommandName().equalsIgnoreCase(alias)) return true;

        if(command.getAliases() == null) return false;
        for (String commandAlias : command.getAliases()) {
            if(!commandAlias.equalsIgnoreCase(alias)) continue;
            return true;
        }

        return false;
    }

    private CustomCommand getSubCommand(CustomCommand cmd, String subCommandName) {
        if(cmd.getSubCommands() == null || cmd.getSubCommands().isEmpty()) return null;

        for (CustomCommand subCommand : cmd.getSubCommands()) {
            if(!this.equalsCommand(subCommand, subCommandName)) continue;
            return subCommand;
        }

        return null;
    }

    public boolean executeCustomCommand(CommandSender sender, CustomCommand customCommand, String[] arguments) {
        // if the command has no extra arguments execute the command.
        if(arguments.length == 0) {
            customCommand.executeCommand(sender, arguments);
            return true;
        }

        boolean canExecute = false;

        // Check for all arguments if its an subcommand
        for (int i = 0; i < arguments.length; i++) {
            CustomCommand subCommand = getSubCommand(customCommand, arguments[i]);
            if(subCommand == null)  return customCommand.executeCommand(sender, arguments);
            String[] newArgs = Arrays.copyOfRange(arguments, 1, arguments.length);
            canExecute = subCommand.executeCommand(sender, newArgs);
            break;
        }

        return canExecute;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandName, String[] arguments) {
        if(this.commandList.isEmpty()) return false;

        for (CustomCommand customCommand : this.commandList) {
            if (customCommand.getCommandName() == null) continue;
            if(!commandName.equalsIgnoreCase(customCommand.getCommandName()) && !equalsAlias(command, customCommand.getAliases())) continue;
            return this.executeCustomCommand(commandSender, customCommand, arguments);
        }

        return false;
    }

    private boolean equalsAlias(Command command, List<String> aliases) {
        boolean returned = false;

        for (String alias : aliases) {
            if(!alias.equals(command.getName()) && ! command.getAliases().contains(alias)) continue;
            returned = true;
            break;
        }

        return returned;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String commandName, String[] arguments) {
        List<String> returned = new ArrayList<>();
        if(this.commandList.isEmpty()) return returned;

        for (CustomCommand customCommand : this.commandList) {
            if(!customCommand.getCommandName().equalsIgnoreCase(commandName)) continue;


            for (int i = 0; i < arguments.length; i++) {
                String[] newArgs = Arrays.copyOfRange(arguments, 1, arguments.length);
                CustomCommand subCommand = getSubCommand(customCommand, arguments[i]);
                if(subCommand != null) {
                    returned.addAll(subCommand.autoCompleteCommand(commandSender, newArgs));
                    break;
                }

                returned.addAll(customCommand.autoCompleteCommand(commandSender, arguments));
            }

            Collections.sort(returned);
            return returned;
        }

        return returned;
    }
}
