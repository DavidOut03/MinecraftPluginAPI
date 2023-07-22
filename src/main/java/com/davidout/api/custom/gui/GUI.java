package com.davidout.api.custom.gui;

import com.davidout.api.utillity.text.TextUtils;
import com.davidout.api.utillity.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class GUI {
    private HashMap<Integer, ItemStack> items;

    public GUI() {
        this.items = new HashMap<>();

        for (int i = 0; i < (getRows() * 9); i++) {
            items.put(i, null);
        }
    }

    private Inventory getDefaultGUI(Object ... arguments) {
        if(this.getRows() == 0) return Bukkit.createInventory(null, 9, TextUtils.formatColorCodes(getTitle()));
        Inventory inventory = Bukkit.createInventory(null, this.getSlots(), TextUtils.formatColorCodes(getTitle()));

        this.createInventory(arguments);

        for (int i = 0; i < inventory.getSize(); i++) {
            if(this.items.size() < i || this.items.get(i) == null) continue;
            inventory.setItem(i, this.items.get(i));
        }

        return inventory;
    }

    public void openInventory(Player player, Object ... arguments) {
        Inventory inventory = getDefaultGUI(arguments);
        player.openInventory(inventory);
    }


    /**
     *
     *  Abstract methods
     *
     */


    public abstract String getTitle();
    public abstract int getRows();
    public abstract void createInventory(Object ... arguments);
    public abstract void onClick(InventoryClickEvent event, Player p);


    /**
     *
     *  inventory management
     *
     */

    public int getSlots() {return getRows() * 9;}


    /**
     *
     * ItemStack management
     *
     */

    public ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    public List<ItemStack> getItems() {
        return new ArrayList<>(this.items.values());
    }

    public void setItem(int slot, ItemStack itemStack) {
        if(slot >= (getRows() * 9)) return;
        this.items.put(slot, itemStack);
    }

    public void addItem(ItemStack itemStack) {
        for (Map.Entry<Integer, ItemStack> integerItemStackEntry : this.items.entrySet()) {
            if(!Item.itemIsNull(integerItemStackEntry.getValue())) continue;
            this.items.put(integerItemStackEntry.getKey(), itemStack);
            break;
        }
    }

    public void removeItem(int slot) {
        this.items.put(slot, null);
    }

    public void removeAllItems(ItemStack itemStack) {
        this.items.forEach((integer, cs) -> {
            if(!itemStack.equals(cs)) return;
            removeItem(integer);
        });
    }

}
