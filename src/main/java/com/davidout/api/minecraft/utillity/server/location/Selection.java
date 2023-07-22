package com.davidout.api.minecraft.utillity.server.location;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Selection {

    public static List<Block> getSelectedBlocks(Location pos1, Location pos2) {
        return getSelection(pos1, pos2).stream().map(Location::getBlock).collect(Collectors.toList());
    }

    public static List<Block> getSelectedBlocks(List<Location> selection) {
        return selection.stream().map(Location::getBlock).collect(Collectors.toList());
    }

    public static List<Location> getSelection(Location pos1, Location pos2) {
        if(!isSameWorld(pos1, pos2)) return new ArrayList<>();
        int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());

        int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        List<Location> selection = new ArrayList<>();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    selection.add(new Location(pos1.getWorld(), x, y, z));
                }
            }
        }

        return selection;
    }

    public static List<Location> getOutlineSelection(Location pos1, Location pos2) {
        int minX,minY,minZ;
        int maxX,maxY,maxZ;

         minX = getMinLocation(pos1.getBlockX(), pos2.getBlockX());
         minY = getMinLocation(pos1.getBlockY(), pos2.getBlockY());
         minZ = getMinLocation(pos1.getBlockZ(), pos2.getBlockZ());

         maxX = getMaxLocation(pos1.getBlockX(), pos2.getBlockX());
         maxY = getMaxLocation(pos1.getBlockY(), pos2.getBlockY());
         maxZ = getMaxLocation(pos1.getBlockZ(), pos2.getBlockZ());

        List<Location> returned = new ArrayList<>();

        getSelection(pos1, pos2).forEach(location -> {
            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            if(x != minX && x !=maxX && y != minY && y != maxY && z != minZ && z != maxZ) return;
            returned.add(location);
        });

        return returned;
    }

    public static Location getCenter(Location pos1, Location pos2) {
        if(!isSameWorld(pos1, pos2)) return null;
        double centerX = (pos1.getX() + pos2.getX()) / 2.0;
        double centerY = (pos1.getY() + pos2.getY()) / 2.0;
        double centerZ = (pos1.getZ() + pos2.getZ()) / 2.0;
        return new Location(pos1.getWorld(), centerX, centerY, centerZ);
    }

    private static int getMinLocation(int pos1, int pos2) {
        return Math.min(pos1, pos2);
    }

    private static int getMaxLocation(int pos1, int pos2) {
        return Math.max(pos1, pos2);
    }

    private static boolean isSameWorld(Location pos1, Location pos2) {
        return Objects.requireNonNull(pos1.getWorld()).getName().equalsIgnoreCase(Objects.requireNonNull(pos2.getWorld()).getName());
    }

}
