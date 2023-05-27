package com.davidout.api.utillity;

import org.bukkit.Bukkit;

public class ServerUtils {

    public static String getServerVersion() {
        String minecraftVersion = Bukkit.getVersion();
        String[] versionParts = minecraftVersion.split(" ")[2].split("\\.");
        return versionParts[0] + "." + versionParts[1];
    }

    public static double getServerVersionNumber() {
        String minecraftVersion = Bukkit.getVersion();
        String[] versionParts = minecraftVersion.split(" ")[2].split("\\.");
        return Double.parseDouble(versionParts[0] + "." + versionParts[1]);
    }

}


