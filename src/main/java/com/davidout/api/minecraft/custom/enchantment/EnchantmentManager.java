package com.davidout.api.minecraft.custom.enchantment;

import com.davidout.api.minecraft.MinecraftPlugin;
import com.davidout.api.minecraft.utillity.text.RomanNumber;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;

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
        if(enchantment == null || itemStack == null || itemStack.getItemMeta() == null) return false;
        return Objects.requireNonNull(itemStack.getItemMeta()).hasEnchant(enchantment) || itemStack.getItemMeta().hasEnchant(Objects.requireNonNull(Enchantment.getByKey(enchantment.getKey())));
    }

    public static boolean containsCustomEnchantment(ItemStack itemStack) {
        return itemStack.getItemMeta().getEnchants().keySet().stream().filter(enchantment -> enchantment instanceof CustomEnchantment).collect(Collectors.toList()).size() > 0;
    }


    public static List<CustomEnchantment> getCustomEnchants() {
        return customEnchantList;
    }

//    public static List<CustomEnchantment> getCustomEnchants(ItemStack itemStack) {
//        return itemStack.getEnchantments().keySet().stream().filter(enchantment -> enchantment instanceof CustomEnchantment).map(enchantment -> (CustomEnchantment) enchantment).collect(Collectors.toList());
//    }

    public static Map<CustomEnchantment, Integer> getCustomEnchantments(ItemStack itemStack) {
        if(itemStack == null) return new HashMap<>();
        return itemStack.getEnchantments().entrySet().stream().filter(enchantmentIntegerEntry -> enchantmentIntegerEntry.getKey() instanceof CustomEnchantment).collect(Collectors.toMap(enchantmentIntegerEntry -> (CustomEnchantment) enchantmentIntegerEntry.getKey(), Map.Entry::getValue));
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
        itemMeta.setLore(updateLore(item.getItemMeta().getLore(), enchantment, level));
        item.setItemMeta(itemMeta);
        return true;
    }

    public static boolean addCustomEnchantment(ItemStack item, CustomEnchantment enchantment, int level) {
        return addCustomEnchantment(item, level, enchantment);
    }

    private static List<String> updateLore(List<String> currentLore, CustomEnchantment enchantment, int level) {
        ArrayList<String> returned = new ArrayList<>();

        String name = enchantment.getName().substring(0, 1).toUpperCase() + enchantment.getName().substring(1);
        String enchantmentLine = (enchantment.getMaxLevel() == 1)? ChatColor.GRAY + name : ChatColor.GRAY + name + " " + RomanNumber.toRoman(level);
        String addedLore = enchantmentLine.replace("-", " ").replace("_", " ");
        returned.add(addedLore);

        if(currentLore != null && !currentLore.isEmpty()) {
            currentLore.removeIf(s -> {
                String[] split = s.split(" ");
                if(split.length <= 1) return false;
                String enchName = ChatColor.stripColor(split[0].toUpperCase());
                return enchName.equalsIgnoreCase(name.toUpperCase());
            });
            returned.addAll(currentLore);
        }

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
