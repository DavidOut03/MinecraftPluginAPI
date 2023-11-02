package mcapi.davidout.utillity.item;

import mcapi.davidout.utillity.text.TextUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class ItemCreator {


    /**
     *
     *  Create itemstacks normal way
     *
     *
     */

    public static ItemStack createItem(Material mat) {
        return new ItemStack(mat);
    }

    public static ItemStack createItem(Material mat, String displayName) {
        ItemStack itemStack = createItem(mat);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(TextUtils.formatColorCodes(displayName));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createItem(Material mat, String displayName, List<String> lore) {
        ItemStack itemStack = createItem(mat, displayName);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(TextUtils.formatLore(lore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createItem(Material mat, List<String> lore) {
        ItemStack itemStack = createItem(mat);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(TextUtils.formatLore(lore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createItem(Material mat, String name, List<String> lore, Map<Enchantment, Integer> enchantments, boolean unbreakable) {
        ItemStack itemStack = createItem(mat, name, lore);
        itemStack.addUnsafeEnchantments(enchantments);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setUnbreakable(unbreakable);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     *
     *  Create itemstacks with item amount
     *
     * @param mat
     * @param amount
     */

    public static ItemStack createItem(Material mat, int amount) {
        return new ItemStack(mat, amount);
    }

    public static ItemStack createItem(Material mat, int amount, String displayName) {
        ItemStack itemStack = createItem(mat, displayName);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public static ItemStack createItem(Material mat, int amount, String displayName, List<String> lore) {
        ItemStack itemStack = createItem(mat, displayName, lore);
        itemStack.setAmount(amount);
        return itemStack;
    }

    /**
     *
     *  Create unbreakable item
     *

     */

    public static ItemStack createUnbreakableItem(Material mat) {
        ItemStack itemStack = createItem(mat);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setUnbreakable(true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createUnbreakableItem(Material mat, String displayName) {
        ItemStack itemStack = createItem(mat, displayName);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setUnbreakable(true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createUnbreakableItem(Material mat, String displayName, List<String> lore) {
        ItemStack itemStack = createItem(mat, displayName, lore);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setUnbreakable(true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     *
     *  Create item with enchantments
     *
     * @param mat
     *
     */

    public static ItemStack createItemWithEnchantments(Material mat, Map<Enchantment, Integer> enchantments) {
        ItemStack itemStack = createItem(mat);
        itemStack.addUnsafeEnchantments(enchantments);
        return itemStack;
    }

    public static ItemStack createItemWithEnchantments(Material mat, String displayName, Map<Enchantment, Integer> enchantments) {
        ItemStack itemStack = createItem(mat, displayName);
        itemStack.addUnsafeEnchantments(enchantments);
        return itemStack;
    }

    public static ItemStack createItemWithEnchantments(Material mat, String displayName, List<String> lore, Map<Enchantment, Integer> enchantments) {
        ItemStack itemStack = createItem(mat, displayName, lore);
        itemStack.addUnsafeEnchantments(enchantments);
        return itemStack;
    }






}
