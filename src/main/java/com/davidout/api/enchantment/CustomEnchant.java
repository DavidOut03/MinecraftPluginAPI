package com.davidout.api.enchantment;

import com.davidout.api.utillity.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public abstract class CustomEnchant extends Enchantment implements Listener {

    private String name;
    private int minLevel;
    private int maxLevel;
    private EnchantmentTarget target;


    public CustomEnchant(String name, int maxLevel) {
        super((NamespacedKey) ((ServerUtils.getServerVersionNumber() >= 1.14) ? (Object) NamespacedKey.minecraft(name) : (Object) Enchantment.values().length));

        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.target = EnchantmentTarget.ALL;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStartLevel() {
        return this.minLevel;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;

    }
    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }


    @Override
    public EnchantmentTarget getItemTarget() {
        return getTarget();
    }


    /**
     *
     *  Custom getters
     *
     */

    public EnchantmentTarget getTarget() {
        return this.target;
    }
    public void setTarget(EnchantmentTarget target) {
        this.target = target;
    }

    public abstract List<Class<? extends Event>> getEvents();
    public abstract void onAction(Event event);

    public void registerEnchantment(Plugin plugin) {


        try {
            Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
            acceptingNew.setAccessible(true);
            acceptingNew.set(null, true);

            Enchantment.registerEnchantment(this);

            this.getEvents().forEach(currentEvent -> {
                Bukkit.getServer().getPluginManager().registerEvent(currentEvent, this, EventPriority.MONITOR, (listener, event) -> onAction(event), plugin);
            });



        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not register custom enchantment: '" + getName() + "' due to an error: ");
            ex.printStackTrace();
        }
    }

    public void unRegisterEnchantment() {

        try {
            Field byIdField = Enchantment.class.getDeclaredField("byKey");
            Field byNameField = Enchantment.class.getDeclaredField("byName");
            byIdField.setAccessible(true);
            byNameField.setAccessible(true);
            Map<Integer, Enchantment> byId = (Map<Integer, Enchantment>) byIdField.get(null);
            Map<String, Enchantment> byName = (Map<String, Enchantment>) byNameField.get(null);
            byId.remove( getKey());
            byName.remove( getName());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not unregister custom enchantment: '" + getName() + "' due to an error: ");
            ex.printStackTrace();
        }
    }

}
