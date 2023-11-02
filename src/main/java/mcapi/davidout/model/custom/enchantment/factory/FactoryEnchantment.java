package mcapi.davidout.model.custom.enchantment.factory;

import mcapi.davidout.model.custom.enchantment.CustomEnchantment;
import mcapi.davidout.model.custom.enchantment.EnchantmentDetails;
import org.bukkit.event.Event;

import java.util.List;

public class FactoryEnchantment extends CustomEnchantment {

    private final EnchantmentFunction function;
    private final List<Class<? extends Event>> events;

    public FactoryEnchantment(EnchantmentDetails details, List<Class<? extends Event>> events, EnchantmentFunction function) {
        super(details);
        this.events = events;
        this.function = function;
    }

    @Override
    public List<Class<? extends Event>> getEvents() {
        return events;
    }

    @Override
    public void onAction(Event event) {
        function.executeFunction(event);
    }
}
