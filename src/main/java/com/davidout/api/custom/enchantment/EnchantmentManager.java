package com.davidout.api.custom.enchantment;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.custom.enchantment.CustomEnchantment;
import com.davidout.api.utillity.RomanNumber;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EnchantmentManager {

    private static final List<CustomEnchantment> customEnchantList = new ArrayList<>();
    private final MinecraftPlugin plugin;

    public EnchantmentManager(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }

    public void addEnchantment(CustomEnchantment enchant) {
        customEnchantList.add(enchant);
        enchant.registerEnchantment(plugin);
    }
    public void removeEnchantment(CustomEnchantment enchant) {
        customEnchantList.remove(enchant);
        enchant.unRegisterEnchantment();
    }


    /**
     *
     * Register and unregister enchantments
     *
     */

    public void registerEnchantments() {customEnchantList.forEach(customEnchant -> customEnchant.registerEnchantment(plugin));}
    public void unRegisterEnchantments() {
        customEnchantList.forEach(CustomEnchantment::unRegisterEnchantment);
    }


    /**
     *
     *
     * Static methods.
     *
     */

    public static boolean containsEnchantment(Enchantment enchantment, ItemStack itemStack) {
        if(itemStack == null || itemStack.getItemMeta() == null) return false;
        return Objects.requireNonNull(itemStack.getItemMeta()).hasEnchant(enchantment) || itemStack.getItemMeta().hasEnchant(Objects.requireNonNull(Enchantment.getById(enchantment.getId())));
    }


    public static List<CustomEnchantment> getCustomEnchants() {
        return customEnchantList;
    }


    public static boolean addCustomEnchantment(ItemStack item, Enchantment enchantment, int level) {
        CustomEnchantment customEnchantment = getEnchantByName(enchantment.getName());
        return addCustomEnchantment(item, level, customEnchantment);
    }

    public static boolean addCustomEnchantment(ItemStack item, String name, int level) {
        CustomEnchantment enchantment = getEnchantByName(name);
        return addCustomEnchantment(item, level, enchantment);
    }

    private static boolean addCustomEnchantment(ItemStack item, int level, CustomEnchantment enchantment) {
        if(!canEnchantItem(enchantment, item)) return false;
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemMeta.setLore(updateLore(itemMeta.getLore(), enchantment, level));
        item.setItemMeta(itemMeta);
        return true;
    }

    public static boolean addCustomEnchantment(ItemStack item, CustomEnchantment enchantment, int level) {
        return addCustomEnchantment(item, level, enchantment);
    }

    private static List<String> updateLore(List<String> currentLore, CustomEnchantment enchantment, int level) {
        ArrayList<String> returned = new ArrayList<>();
        if(currentLore == null) currentLore = new ArrayList<>();

        String name = enchantment.getName().substring(0, 1).toUpperCase() + enchantment.getName().substring(1);
        String enchantmentLine = (enchantment.getMaxLevel() == 1)? ChatColor.GRAY + name : ChatColor.GRAY + name + " " + RomanNumber.toRoman(level);
        String addedLore = enchantmentLine.replace("-", " ").replace("_", " ");
        if(!currentLore.contains(addedLore)) returned.add(addedLore);


        if(!currentLore.isEmpty()) returned.addAll(currentLore);
        return returned;
    }

    private static boolean canEnchantItem(CustomEnchantment enchantment, ItemStack itemStack) {
        return enchantment != null && itemStack != null && itemStack.getItemMeta() != null && enchantment.canEnchantItem(itemStack);
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
