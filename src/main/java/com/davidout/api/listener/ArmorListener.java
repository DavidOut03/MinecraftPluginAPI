package com.davidout.api.listener;


import com.davidout.api.custom.event.ArmorDequipEvent;
import com.davidout.api.custom.event.ArmorEquipEvent;
import com.davidout.api.enums.ArmorType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorListener implements Listener {
    private static int[] armorslots = {39, 38, 37, 36};

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) {
        if(e.getClickedInventory() == null || !(e.getWhoClicked() instanceof Player)) return;
        if(e.getClickedInventory() != e.getWhoClicked().getInventory()) return;
        if(e.isCancelled()) return;
        Player p = (Player) e.getWhoClicked();


        if(e.getClick().isShiftClick()) {
            if(e.getSlotType() == InventoryType.SlotType.ARMOR && p.getInventory().firstEmpty() != -1) {
                Bukkit.getServer().getPluginManager().callEvent(new ArmorDequipEvent(p, e.getCurrentItem(), new ItemStack(Material.AIR), getArmorType(e.getCurrentItem())));
                return;
            }

            if(!canShiftEquip(e.getCurrentItem(), (Player) e.getWhoClicked())) return;
            Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(p, new ItemStack(Material.AIR), e.getCurrentItem(), getArmorType(e.getCursor())));
            return;
        }

        if(e.getSlotType() != InventoryType.SlotType.ARMOR) return;

        if(e.getClick().isLeftClick()) {
            if(isOpenSlot(e.getCurrentItem()) && isOpenSlot(e.getCursor())) return;


            // when a player equips an armor piece
            if(isOpenSlot(e.getCurrentItem()) && !isOpenSlot(e.getCursor()) && canBePlacedInSlot(e.getSlot(), e.getCursor())) {
                Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(p, e.getCurrentItem(), e.getCursor(), getArmorType(e.getCursor())));
                return;
            }

            if(!isOpenSlot(e.getCurrentItem()) && !isOpenSlot(e.getCursor()) && isSameArmourType(e.getCurrentItem(), e.getCursor()) && canBePlacedInSlot(e.getSlot(), e.getCursor())) {
                Bukkit.getServer().getPluginManager().callEvent(new ArmorDequipEvent(p, e.getCurrentItem(), e.getCursor(), getArmorType(e.getCurrentItem())));
                Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(p, e.getCurrentItem(), e.getCursor(), getArmorType(e.getCursor())));
                return;
            }

            Bukkit.getServer().getPluginManager().callEvent(new ArmorDequipEvent(p, e.getCurrentItem(), new ItemStack(Material.AIR), getArmorType(e.getCurrentItem())));
            return;
        }

        // keyboard click
//        e.getClick().isKeyboardClick();

        if(e.getClick().isKeyboardClick()) {
            if(e.getClick().equals(ClickType.DROP) || e.getClick().equals(ClickType.CONTROL_DROP)) {
                Bukkit.getServer().getPluginManager().callEvent(new ArmorDequipEvent(p, e.getCurrentItem(), new ItemStack(Material.AIR), getArmorType(e.getCurrentItem())));
                return;
            }

            ItemStack hotbarItem = e.getClickedInventory().getItem(e.getHotbarButton());
            ItemStack switchedItem = getSwitchedItem(p, hotbarItem);

            if(isOpenSlot(hotbarItem) && isOpenSlot(e.getCurrentItem())) return;

            if(isOpenSlot(hotbarItem) && !isOpenSlot(e.getCurrentItem() )) {
                Bukkit.getServer().getPluginManager().callEvent(new ArmorDequipEvent(p, switchedItem, new ItemStack(Material.AIR), getArmorType(switchedItem)));
                return;
            }

            if(!isOpenSlot(hotbarItem) && isOpenSlot(e.getCurrentItem()) && canBePlacedInSlot(e.getSlot(), hotbarItem)) {
                Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(p, new ItemStack(Material.AIR), hotbarItem, getArmorType(hotbarItem)));
                return;
            }

            if(isArmor(hotbarItem) && !isOpenSlot(hotbarItem) && !isOpenSlot(e.getCurrentItem()) && isSameArmourType(hotbarItem, e.getCurrentItem()) && canBePlacedInSlot(e.getSlot(), hotbarItem)) {
                Bukkit.getServer().getPluginManager().callEvent(new ArmorDequipEvent(p, switchedItem, hotbarItem, getArmorType(hotbarItem)));
                Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(p, switchedItem, hotbarItem, getArmorType(hotbarItem)));
                return;
            }



        }
    }

    @EventHandler
    public void interackt(PlayerInteractEvent e) {
        if(isOpenSlot(e.getItem())) return;

        if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(getArmorType(e.getItem()) == null) return;

        ArmorType type = getArmorType(e.getItem());
        boolean can = type.equals(ArmorType.HELMET) && isOpenSlot(e.getPlayer().getInventory().getHelmet()) ||
                type.equals(ArmorType.CHESTPLATE) && isOpenSlot(e.getPlayer().getInventory().getChestplate()) ||
                type.equals(ArmorType.LEGGINGS) && isOpenSlot(e.getPlayer().getInventory().getLeggings()) ||
                type.equals(ArmorType.BOOTS) && isOpenSlot(e.getPlayer().getInventory().getBoots());

        if(!can) return;
        Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(e.getPlayer(), getSwitchedItem(e.getPlayer(), e.getItem()), e.getItem(), type));
    }

    private boolean isOpenSlot(ItemStack itemStack) {
        return itemStack == null || itemStack.getType() == null || itemStack.getType().equals(Material.AIR);
    }

    private boolean isSameArmourType(ItemStack itemStack1, ItemStack itemStack2) {
        if(getArmorType(itemStack1) == null || getArmorType(itemStack2) == null) return false;
        return getArmorType(itemStack1).equals(getArmorType(itemStack2));
    }


    private boolean canShiftEquip(ItemStack itemStack, Player player) {
        if(isOpenSlot(itemStack) || getArmorType(itemStack) == null) return false;
        return getArmorType(itemStack) == ArmorType.HELMET && isOpenSlot(player.getInventory().getHelmet()) ||
                getArmorType(itemStack) == ArmorType.CHESTPLATE && isOpenSlot(player.getInventory().getChestplate()) ||
                getArmorType(itemStack) == ArmorType.LEGGINGS && isOpenSlot(player.getInventory().getLeggings()) ||
                getArmorType(itemStack) == ArmorType.BOOTS && isOpenSlot(player.getInventory().getBoots());
    }

    private boolean canBePlacedInSlot(int slot, ItemStack itemStack) {
        ArmorType type = getArmorType(itemStack);
        if(type == null) return false;
        return slot == armorslots[0] && type.equals(ArmorType.HELMET) || slot == armorslots[1] && type.equals(ArmorType.CHESTPLATE) || slot == armorslots[2] && type.equals(ArmorType.LEGGINGS) || slot == armorslots[3] && type.equals(ArmorType.BOOTS);
    }

    private boolean isArmor(ItemStack itemStack) {
        return getArmorType(itemStack) == null;
    }

    public ArmorType getArmorType(ItemStack itemStack) {
        if(itemStack == null) return null;
        String mat = itemStack.getType().toString().toLowerCase();
        if(mat.contains("helmet")) return ArmorType.HELMET;
        if(mat.contains("chestplate")) return ArmorType.CHESTPLATE;
        if(mat.contains("leggings")) return ArmorType.LEGGINGS;
        if(mat.contains("boots")) return ArmorType.BOOTS;
        return null;
    }

    public ItemStack getSwitchedItem(Player p, ItemStack itemStack) {
        if(isOpenSlot(itemStack) || getArmorType(itemStack) == null) return null;
        ArmorType type = getArmorType(itemStack);

        switch (type) {
            case HELMET: return p.getInventory().getHelmet();
            case CHESTPLATE: return p.getInventory().getChestplate();
            case LEGGINGS: return p.getInventory().getLeggings();
            case BOOTS: return p.getInventory().getBoots();
            default: return null;
        }
    }

}

