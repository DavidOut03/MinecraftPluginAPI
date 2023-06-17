package com.davidout.api.custom.enchantment;

import com.davidout.api.enums.EnchantmentTarget;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CustomEnchantment extends EnchantmentWrapper implements Listener {

    private final EnchantmentDetails details;

    public CustomEnchantment(EnchantmentDetails details) {
        super(details.getName());
        this.details = details;
    }

    @Override
    public String getName() {
        return this.details.getName();
    }

    public String getDisplayName() {
        return getName().substring(0, 1).toUpperCase() + getName().substring(1).replace("-", " ").replace("_", " ");
    }

    @Override
    public int getStartLevel() {
        return this.details.getMinLevel();
    }
    @Override
    public int getMaxLevel() {return this.details.getMaxLevel();}

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }


    @Override
    public boolean canEnchantItem(ItemStack item) {

        for (EnchantmentTarget currentTarget : this.details.getTargets()) {
            if(!currentTarget.includes(item)) continue;
            return true;
        }
        return false;
    }




    /**
     *
     *  Custom getters
     *
     */

    /*

    Abstract methods

     */
    public abstract List<Class<? extends Event>> getEvents();
    public abstract void onAction(Event event);

    public String getDescription() {return details.getDescription();}
    public List<EnchantmentTarget> getTargets() {return details.getTargets();}
    public void addTarget(EnchantmentTarget target) {details.getTargets().add(target);}
    public void removeTarget(EnchantmentTarget target) {details.getTargets().remove(target);}



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
