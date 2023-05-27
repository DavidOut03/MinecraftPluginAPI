package com.davidout.api.enchantment;

import com.davidout.api.MinecraftPlugin;

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
