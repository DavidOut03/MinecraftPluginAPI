package com.davidout.api.enchantment;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.utillity.RomanNumber;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentManager {

    private static List<CustomEnchant> customEnchantList = new ArrayList<>();
    private MinecraftPlugin plugin;

    public EnchantmentManager(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }

    public void addEnchantment(CustomEnchant enchant) {
        this.customEnchantList.add(enchant);
        enchant.registerEnchantment(plugin);
    }
    public void removeEnchantment(CustomEnchant enchant) {
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

    public static void addCustomEnchantment(ItemStack item, String name, int level) {
        CustomEnchant enchantment = getEnchantByName(name);
        if(enchantment == null) return;

        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null) {
            itemMeta.addEnchant(enchantment, level, true);
            itemMeta.setLore(updateLore(itemMeta.getLore(), enchantment, level));
            item.setItemMeta(itemMeta);
        }
    }

    private static List<String> updateLore(List<String> currentLore, CustomEnchant enchantment, int level) {
        ArrayList<String> returned = new ArrayList<>();

        String enchantmentLine = ChatColor.GRAY + enchantment.getName() + " " + RomanNumber.toRoman(level);
        returned.add(enchantmentLine);


        if(currentLore != null && !currentLore.isEmpty()) returned.addAll(currentLore);
        return returned;
    }



    public static boolean enchantmentExists(String name) {
        return getEnchantByName(name) == null;
    }

    public static CustomEnchant getEnchantByName(String name) {
        for (CustomEnchant customEnchant : customEnchantList) {
            if(!customEnchant.getName().equalsIgnoreCase(name)) continue;
            return customEnchant;
        }

        return null;
    }

}
