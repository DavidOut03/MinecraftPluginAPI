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

    private String name;
    private int minLevel;
    private int maxLevel;
    private List<EnchantmentTarget> targets;


    public CustomEnchantment(String name, int maxLevel) {
        super(EnchantmentManager.getCustomEnchants().size());
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.targets = new ArrayList<>();

        this.targets.add(EnchantmentTarget.ALL);
    }

    public CustomEnchantment(String name, int maxLevel, EnchantmentTarget target) {
        super(EnchantmentManager.getCustomEnchants().size());
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.targets = new ArrayList<>();

        this.targets.add(target);
    }

    public CustomEnchantment(String name, int maxLevel, List<EnchantmentTarget> targets) {
        super(EnchantmentManager.getCustomEnchants().size());
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.targets = new ArrayList<>();
        this.targets.addAll(targets);
    }



    @Override
    public String getName() {
        return this.name;
    }
    public String getDisplayName() {
        return getName().substring(0, 1).toUpperCase() + getName().substring(1).replace("-", " ").replace("_", " ");
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }


    @Override
    public boolean canEnchantItem(ItemStack item) {

        for (EnchantmentTarget currentTarget : this.targets) {
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

    public List<EnchantmentTarget> getTargets() {return targets;}
    public void addTarget(EnchantmentTarget target) {targets.add(target);}
    public void removeTarget(EnchantmentTarget target) {targets.remove(target);}

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
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");
            byIdField.setAccessible(true);
            byNameField.setAccessible(true);
            Map<Integer, Enchantment> byId = (Map<Integer, Enchantment>) byIdField.get(null);
            Map<String, Enchantment> byName = (Map<String, Enchantment>) byNameField.get(null);
            byId.remove( getId());
            byName.remove( getName());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not unregister custom enchantment: '" + getName() + "' due to an error: ");
            ex.printStackTrace();
        }
    }

}
