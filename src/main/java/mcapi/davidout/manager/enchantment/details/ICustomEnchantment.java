package mcapi.davidout.manager.enchantment.details;

import mcapi.davidout.manager.enchantment.event.IEnchantmentEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


public abstract class ICustomEnchantment extends EnchantmentWrapper implements Listener {

    private final IEnchantmentDetails enchantmentDetails;

    public ICustomEnchantment(IEnchantmentDetails iEnchantmentDetails) {
        super(iEnchantmentDetails.getName());
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
        triggerOnEvents().forEach(eventClass ->
                plugin.getServer().getPluginManager().registerEvent(
                        eventClass,
                        this,
                        EventPriority.MONITOR,
                        (listener, event) -> handleEvent(event), plugin)
        );
    }






}
