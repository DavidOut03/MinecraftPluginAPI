package com.davidout.minecraftPluginAPI;

import com.davidout.minecraftPluginAPI.command.CustomCommand;
import org.bukkit.event.Listener;

import java.util.List;

public class Main extends MinecraftPlugin {


    @Override
    public void onStartup() {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public List<Listener> registerEvents() {
        return null;
    }

    @Override
    public List<CustomCommand> registerCommands() {
        return null;
    }
}