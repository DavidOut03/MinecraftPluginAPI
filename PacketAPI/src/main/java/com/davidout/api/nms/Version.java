package com.davidout.api.nms;

import org.bukkit.Bukkit;

public class Version {

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

    public static boolean isServerVersion(String version) {
        String serverVersion = Bukkit.getBukkitVersion();
        String[] serverParts = serverVersion.split("\\.");
        String[] versionParts = version.split("\\.");

        return Integer.parseInt(serverParts[0]) == Integer.parseInt(versionParts[0]) && Integer.parseInt(serverParts[1]) == Integer.parseInt(versionParts[1]);
    }


    public static boolean serverVersionEqualsOrIsAbove(String version) {
        String serverVersion = Bukkit.getBukkitVersion();
        String[] serverParts = serverVersion.split("\\.");
        String[] versionParts = version.split("\\.");

        return Integer.parseInt(serverParts[0]) > Integer.parseInt(versionParts[0]) || Integer.parseInt(serverParts[1]) >= Integer.parseInt(versionParts[1]);
    }
}
