package mcapi.davidout.manager.enchantment.details;

import mcapi.davidout.manager.enchantment.EnchantmentTarget;
import mcapi.davidout.manager.enchantment.event.IEnchantmentEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


public abstract class ICustomEnchantment extends EnchantmentWrapper implements Listener {

    private final IEnchantmentDetails enchantmentDetails;

    public ICustomEnchantment(IEnchantmentDetails iEnchantmentDetails) {
        super(iEnchantmentDetails.getName().toLowerCase());
        this.enchantmentDetails = iEnchantmentDetails;
    }

    public IEnchantmentDetails getEnchantmentDetails() {
        return this.enchantmentDetails;
    }

    public boolean registerEnchantment(Plugin plugin) {
        try {
            Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
            acceptingNew.setAccessible(true);
            acceptingNew.set(null, true);

            Enchantment.registerEnchantment(this);
            registerEvents(plugin);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean unRegisterEnchantment() {
        try {
            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            Field byNameField = Enchantment.class.getDeclaredField("byName");
            byKeyField.setAccessible(true);
            byNameField.setAccessible(true);
            Map<Integer, Enchantment> byKey = (Map<Integer, Enchantment>) byKeyField.get(null);
            Map<String, Enchantment> byName = (Map<String, org.bukkit.enchantments.Enchantment>) byNameField.get(null);
            byKey.remove(getKey());
            byName.remove( getName());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public abstract List<Class<? extends Event>> triggerOnEvents();
    public abstract void handleEvent(Event event);

    private void registerEvents(Plugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        for (Class<? extends Event> eventClass : triggerOnEvents()) {
            pluginManager.registerEvent(
                    eventClass,
                    this,
                    EventPriority.MONITOR,
                    (listener, event) -> handleEvent(event),
                    plugin
            );
        }
    }

    @Override
    public int getStartLevel() {
        return 0;
    }
    @Override
    public int getMaxLevel() {return this.enchantmentDetails.getMaxLevel();}

    @NotNull
    @Override
    public String getName() {
        return this.enchantmentDetails.getName();
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
       return EnchantmentTarget.isEnchantable(this.enchantmentDetails.getTargets(), item);
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }




}
