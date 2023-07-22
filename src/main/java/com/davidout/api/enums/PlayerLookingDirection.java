package com.davidout.api.enums;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public enum PlayerLookingDirection {

    NORTH, EAST, SOUTH, WEST, UP, DOWN;

    public static PlayerLookingDirection getDirection(Player player) {
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();

        if (yaw < 0) {
            yaw += 360;
        }
        yaw %= 360;

        PlayerLookingDirection direction = null;

        if (yaw >= 45 && yaw < 135) direction = SOUTH;
        if (yaw >= 135 && yaw < 225) direction = WEST;
        if (yaw >= 225 && yaw < 315) direction = NORTH;
        if (yaw >= 315 || yaw < 45) direction = EAST;
        if(pitch <= -45) direction = UP;
        if(pitch >= 45) direction = DOWN;
        return direction;
    }

    public static BlockFace getFacingDirection(Player player) {
        PlayerLookingDirection direction = getDirection(player);

        switch (direction) {
            case UP: return BlockFace.UP;
            case DOWN: return BlockFace.DOWN;
            case NORTH: return BlockFace.NORTH;
            case EAST: return BlockFace.EAST;
            case SOUTH: return BlockFace.SOUTH;
            case WEST: return BlockFace.WEST;
        }

        return BlockFace.SELF;
    }

    public static BlockFace getOppositeFace(Location placedBlockLoc, Location againstBlockLoc) {
        int dx = placedBlockLoc.getBlockX() - againstBlockLoc.getBlockX();
        int dy = placedBlockLoc.getBlockY() - againstBlockLoc.getBlockY();
        int dz = placedBlockLoc.getBlockZ() - againstBlockLoc.getBlockZ();

        if (dx == 1) {
            return BlockFace.WEST;
        } else if (dx == -1) {
            return BlockFace.EAST;
        } else if (dy == 1) {
            return BlockFace.DOWN;
        } else if (dy == -1) {
            return BlockFace.UP;
        } else if (dz == 1) {
            return BlockFace.NORTH;
        } else if (dz == -1) {
            return BlockFace.SOUTH;
        }

        return null;
    }


}
