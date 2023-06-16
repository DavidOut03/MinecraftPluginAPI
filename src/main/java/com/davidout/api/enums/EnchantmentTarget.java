package com.davidout.api.enums;

import com.davidout.api.utillity.item.Item;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public enum EnchantmentTarget {



    // Categories
    ALL,
    BREAKABLE,
    ARMOUR,
    TOOLS,
    WEAPONS,

    // Armour
    HELMET, CHESTPLATE, LEGGINGS, BOOTS,

    // Tools
    PICKAXE, AXE, HOE, SHOVEL, SHEAR, FISHING_ROD,

    // Weapons
    SWORD,
    BOW,
    CROSSBOW,
    TRIDENT;

    public boolean includes(ItemStack itemStack) {
        if(this.equals(EnchantmentTarget.ALL) || this.equals(getEnchantmentTarget(itemStack)) || itemStack.getType().equals(Material.BOOK)) return true;
        EnchantmentTarget itemTarget = getEnchantmentTarget(itemStack);

        if(this.equals(EnchantmentTarget.ARMOUR)) {
            if(itemTarget.equals(EnchantmentTarget.HELMET) || itemTarget.equals(EnchantmentTarget.CHESTPLATE) || itemTarget.equals(EnchantmentTarget.LEGGINGS) || itemTarget.equals(EnchantmentTarget.BOOTS)) return true;
        }

        if(this.equals(EnchantmentTarget.TOOLS)) {
            if(itemTarget.equals(EnchantmentTarget.PICKAXE) ||
                    itemTarget.equals(EnchantmentTarget.AXE) || itemTarget.equals(EnchantmentTarget.HOE) ||
                    itemTarget.equals(EnchantmentTarget.SHOVEL) || itemTarget.equals(EnchantmentTarget.SHEAR) ||
                    itemTarget.equals(EnchantmentTarget.FISHING_ROD)) return true;
        }


        if(this.equals(EnchantmentTarget.WEAPONS)) {
            if(itemTarget.equals(EnchantmentTarget.SWORD) ||
                    itemTarget.equals(EnchantmentTarget.BOW) || itemTarget.equals(EnchantmentTarget.CROSSBOW) ||
                    itemTarget.equals(EnchantmentTarget.TRIDENT) || itemTarget.equals(EnchantmentTarget.AXE)) return true;
        }

        return this.name().equalsIgnoreCase(itemTarget.name());
    }

    public static EnchantmentTarget getEnchantmentTarget(ItemStack itemStack) {
        String mat = itemStack.getType().toString().toLowerCase();
        if(mat.equals(Material.BOOK)) return EnchantmentTarget.ALL;

        if(Item.itemIsArmour(itemStack)) {
            if(mat.endsWith(Item.helmet)) return EnchantmentTarget.HELMET;
            if(mat.endsWith(Item.chestplate)) return EnchantmentTarget.CHESTPLATE;
            if(mat.endsWith(Item.leggings)) return EnchantmentTarget.LEGGINGS;
            if(mat.endsWith(Item.boots)) return EnchantmentTarget.BOOTS;
            return EnchantmentTarget.ARMOUR;
        }

        if(Item.itemIsTool(itemStack)) {
            if(mat.endsWith(Item.pickaxe)) return EnchantmentTarget.PICKAXE;
            if(mat.endsWith(Item.axe)) return EnchantmentTarget.AXE;
            if(mat.endsWith(Item.hoe)) return EnchantmentTarget.HOE;
            if(mat.endsWith(Item.shovel) || mat.endsWith(Item.spade)) return EnchantmentTarget.SHOVEL;
            if(mat.endsWith(Item.shears)) return EnchantmentTarget.SHEAR;
            if(mat.endsWith(Item.fishing_rod)) return EnchantmentTarget.FISHING_ROD;
            return EnchantmentTarget.TOOLS;
        }

        if(Item.itemIsWeapon(itemStack)) {
            if(mat.endsWith(Item.sword)) return EnchantmentTarget.SWORD;
            if(mat.endsWith(Item.axe)) return EnchantmentTarget.AXE;
            if(mat.endsWith(Item.bow)) return EnchantmentTarget.BOW;
            if(mat.endsWith(Item.crossbow)) return EnchantmentTarget.CROSSBOW;
            if(mat.endsWith(Item.trident)) return EnchantmentTarget.TRIDENT;
            return EnchantmentTarget.WEAPONS;
        }

        return EnchantmentTarget.ALL;
    }

    public static boolean itemIsArmour(ItemStack itemStack) {
        String mat = itemStack.getType().toString().toLowerCase();
        return  mat.toLowerCase().endsWith("_helmet") || mat.endsWith("_chestplate") || mat.endsWith("_leggings") || mat.endsWith("_boots");
    }


}
