package com.davidout.api.nms;

import org.bukkit.Bukkit;

public class NMSHelper {

    public static Class<?> getMinecraftClass(String className) {
            try {
                if(Version.serverVersionEqualsOrIsAbove("1.17")) {
                    return Class.forName("net.minecraft." + className);
                }
                return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]  + "." + className);
            } catch (Exception ignored) {
                return null;
            }
    }

    public static Class<?> getBukkitClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
        } catch (Exception e) {
            return null;
        }
    }

    public Class<?> getOBSClass(String className, String packageName) {
        try {
            return Class.forName("org.bukkit.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]  + "." + packageName + "." + className);
        } catch (Exception ex) {
            return null;
        }
    }


}
