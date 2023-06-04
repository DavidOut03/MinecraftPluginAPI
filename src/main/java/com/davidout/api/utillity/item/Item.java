package com.davidout.api.utillity.item;

import org.bukkit.inventory.ItemStack;

public class Item {

    /**
     *  Armour
     */

    public static String helmet = "_helmet";
    public static String chestplate = "_chestplate";
    public static String leggings = "_leggings";
    public static String boots = "_boots";

    /**
     *  Tools
     */

    public static String pickaxe = "_pickaxe";
    public static String axe = "_axe";
    public static String shovel = "_shovel";
    public static String spade = "_spade";
    public static String hoe = "_hoe";
    public static String fishing_rod = "fishing_rod";
    public static String shears = "shears";

    /**
     *  Weapons
     */
    public static String sword = "_sword";
    public static String bow = "bow";
    public static String crossbow = "crossbow";
    public static String trident = "trident";

    public static boolean itemIsArmour(ItemStack itemStack) {
        String mat = itemStack.getType().toString().toLowerCase();
        return  mat.toLowerCase().endsWith(helmet) || mat.endsWith(chestplate) || mat.endsWith(leggings) || mat.endsWith(boots);
    }

    public static boolean itemIsTool(ItemStack itemStack) {
        String mat = itemStack.getType().toString().toLowerCase();
        return  mat.toLowerCase().endsWith(pickaxe) || mat.endsWith(axe) || mat.endsWith(shovel) || mat.endsWith(spade)|| mat.endsWith(hoe) || mat.endsWith(fishing_rod) || mat.endsWith(shears);
    }

    public static boolean itemIsWeapon(ItemStack itemStack) {
        String mat = itemStack.getType().toString().toLowerCase();
        return  mat.toLowerCase().endsWith(sword) || mat.endsWith(bow) || mat.endsWith(crossbow) || mat.endsWith(trident);
    }

    public static boolean itemIsBlock(ItemStack itemStack) {
      return itemStack.getType().isBlock();
    }


}
