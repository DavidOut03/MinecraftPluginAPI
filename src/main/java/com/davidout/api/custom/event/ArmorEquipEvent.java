package com.davidout.api.custom.event;

import com.davidout.api.enums.ArmorType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final ItemStack oldItem;
    private final ItemStack newItem;
    private final ArmorType type;

    public ArmorEquipEvent(Player player, ItemStack oldItem, ItemStack newItem, ArmorType type) {
        this.player = player;
        this.oldItem = oldItem;
        this.newItem = newItem;
        this.type = type;
    }

    public Player getPlayer() {return this.player;}
    public ItemStack getEquipedArmor() {return this.newItem;}
    public ItemStack getDequipedArmor() {return this.oldItem;}
    public ArmorType getArmorType() {return this.type;}


    @Override
    public HandlerList getHandlers() {return handlers;}
    public static HandlerList getHandlerList() {return handlers;}
}
