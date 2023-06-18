package com.davidout.api.utillity.server;

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

    public static boolean serverVersionIsAbove(String version) {

        String serverVersion = Bukkit.getBukkitVersion();
        String[] serverParts = serverVersion.split("\\.");

        String[] versionParts = version.split("\\.");

        // Check if the first part is greater than 1 or if the second part is greater than or equal to 7
        if (Integer.parseInt(serverParts[0]) > Integer.parseInt(versionParts[0]) || Integer.parseInt(serverParts[1]) >=  Integer.parseInt(versionParts[1])) {
            return true;
        }

        return false;
    }

}


