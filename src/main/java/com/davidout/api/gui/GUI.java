package com.davidout.api.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class GUI {

   
    private int rows;


    public void draw() {
    }

    public void setItemAction(ItemAction action) {
        action.itemAction(new ItemStack(Material.AIR));
    }

}
