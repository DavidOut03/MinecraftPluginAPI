package com.davidout.api.gui;

import com.davidout.api.utillity.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class GUI {
    private List<ItemStack> items;
    private String title;
    private int rows;

    public GUI(int rows, String title) {
        this.items = new ArrayList<>();
        this.rows = rows;
        this.title = TextUtils.formatColorCodes(title);

        for(int i = 0; i < (rows * 9); i++) {
            this.items.add(new ItemStack(Material.AIR));
        }

    }

    public Inventory getGUI() {
        if(this.getRows() == 0) return Bukkit.createInventory(null, 9, this.title);
        Inventory inventory = Bukkit.createInventory(null, this.getSlots(), this.title);

        this.createInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            if(this.items.get(i) == null) break;
            inventory.setItem(i, this.items.get(i));
        }

        return inventory;
    }

    public void openInventory(Player player) {
        player.openInventory(this.getGUI());
    }


    /**
     *
     *  Abstract methods
     *
     */


    public abstract void createInventory();
    public abstract void onClick(InventoryClickEvent event, Player p);


    /**
     *
     *  inventory management
     *
     */

    public void setTitle(String title) {this.title = TextUtils.formatColorCodes(title);}
    public String getTitle() {return this.title;}

    public int getRows() {return this.rows;}
    public int getSlots() {return this.rows * 9;}
    public void setRows(int rows) {this.rows = rows;}


    /**
     *
     * ItemStack management
     *
     */

    public ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    public void setItem(int slot, ItemStack itemStack) {
        this.items.set(slot, itemStack);
    }

    public void addItem(ItemStack itemStack) {
        this.items.add(itemStack);
    }

    public void removeItem(int slot) {
        this.items.remove(slot);
    }

    public void removeAllItems(ItemStack itemStack) {
        this.items.forEach(cs -> {
            if(!cs.equals(itemStack)) return;
            this.items.remove(cs);
        });
    }

}
