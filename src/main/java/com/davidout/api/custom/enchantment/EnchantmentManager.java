package com.davidout.api.custom.enchantment;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.custom.enchantment.CustomEnchantment;
import com.davidout.api.utillity.RomanNumber;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentManager {

    private static List<CustomEnchantment> customEnchantList = new ArrayList<>();
    private MinecraftPlugin plugin;

    public EnchantmentManager(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }

    public void addEnchantment(CustomEnchantment enchant) {
        this.customEnchantList.add(enchant);
        enchant.registerEnchantment(plugin);
    }
    public void removeEnchantment(CustomEnchantment enchant) {
        this.customEnchantList.remove(enchant);
        enchant.unRegisterEnchantment();
    }


    /**
     *
     * Register and unregister enchantments
     *
     */

    public void registerEnchantments() {this.customEnchantList.forEach(customEnchant -> customEnchant.registerEnchantment(plugin));}
    public void unRegisterEnchantments() {
        this.customEnchantList.forEach(enchant -> enchant.unRegisterEnchantment());
    }


    /**
     *
     *
     * Static methods.
     *
     */


    public static List<CustomEnchantment> getCustomEnchants() {
        return customEnchantList;
    }


    public static boolean addCustomEnchantment(ItemStack item, Enchantment enchantment, int level) {
        CustomEnchantment customEnchantment = getEnchantByName(enchantment.getName());
        if(canNotEnchant(customEnchantment, item)) return false;
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(customEnchantment, level, true);
        itemMeta.setLore(updateLore(itemMeta.getLore(), customEnchantment, level));
        item.setItemMeta(itemMeta);
        return true;
    }

    public static boolean addCustomEnchantment(ItemStack item, String name, int level) {
        CustomEnchantment enchantment = getEnchantByName(name);
        if(canNotEnchant(enchantment, item)) return false;
        ItemMeta itemMeta = item.getItemMeta();
            itemMeta.addEnchant(enchantment, level, true);
            itemMeta.setLore(updateLore(itemMeta.getLore(), enchantment, level));
            item.setItemMeta(itemMeta);
        return true;
    }

    public static boolean addCustomEnchantment(ItemStack item, CustomEnchantment enchantment, int level) {
        if(canNotEnchant(enchantment, item)) return false;
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemMeta.setLore(updateLore(itemMeta.getLore(), enchantment, level));
        item.setItemMeta(itemMeta);
        return true;
    }

    private static List<String> updateLore(List<String> currentLore, CustomEnchantment enchantment, int level) {
        ArrayList<String> returned = new ArrayList<>();
        if(currentLore == null) currentLore = new ArrayList<>();

        String name = enchantment.getName().substring(0, 1).toUpperCase() + enchantment.getName().substring(1);
        String enchantmentLine = (enchantment.getMaxLevel() == 1)? ChatColor.GRAY + name : ChatColor.GRAY + name + " " + RomanNumber.toRoman(level);
        String addedLore = enchantmentLine.replace("-", " ").replace("_", " ");
        if(!currentLore.contains(addedLore)) returned.add(addedLore);


        if(currentLore != null && !currentLore.isEmpty()) returned.addAll(currentLore);
        return returned;
    }

    private static boolean canNotEnchant(CustomEnchantment enchantment, ItemStack itemStack) {
        return enchantment == null || itemStack == null || itemStack.getItemMeta() == null || !enchantment.canEnchantItem(itemStack);
    }



    public static boolean enchantmentExists(String name) {
        return getEnchantByName(name) == null;
    }

    public static CustomEnchantment getEnchantByName(String name) {
        for (CustomEnchantment customEnchant : customEnchantList) {
            if(!customEnchant.getName().equalsIgnoreCase(name)) continue;
            return customEnchant;
        }

        return null;
    }

}
