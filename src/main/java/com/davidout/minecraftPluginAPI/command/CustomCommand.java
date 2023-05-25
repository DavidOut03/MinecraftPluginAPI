package com.davidout.minecraftPluginAPI.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface CustomCommand {

    public String getCommandName();
    public String getUsage();
    public List<String> getAliases();

    public List<CustomCommand> getSubCommands();

    public void sendHelp(String[] arguments);

    public boolean executeCommand(CommandSender sender, String[] arguments);
    public List<String> autoCompleteCommand(CommandSender sender, String[] arguments);


}
