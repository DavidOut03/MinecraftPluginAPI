package mcapi.davidout.manager.enchantment.event;


import mcapi.davidout.manager.enchantment.details.ICustomEnchantment;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class IEnchantmentEvent<T extends Event, C extends ICustomEnchantment> implements Listener {

    private final C enchantment;

    public IEnchantmentEvent(C enchantment) {
        this.enchantment = enchantment;
    }

    @EventHandler
    public void onEvent(T event) {
        this.handleEvent(event, enchantment);
    }

    public abstract void handleEvent(T event, C enchantment);

}
