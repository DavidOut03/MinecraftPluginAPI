package com.davidout.api.enchantment;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.utillity.RomanNumber;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentManager {

    private List<CustomEnchant> customEnchantList;
    private MinecraftPlugin plugin;

    public EnchantmentManager(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.customEnchantList = new ArrayList<>();
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


    public static void addCustomEnchantment(ItemStack item, CustomEnchant enchantment, int level) {
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
        returned.add(" ");
        returned.addAll(currentLore);
        return returned;
    }



    public boolean enchantmentExists(String name) {
        return getEnchantByName(name) == null;
    }

    public CustomEnchant getEnchantByName(String name) {
        for (CustomEnchant customEnchant : customEnchantList) {
            if(!customEnchant.getName().equalsIgnoreCase(name)) continue;
            return customEnchant;
        }

        return null;
    }

}
