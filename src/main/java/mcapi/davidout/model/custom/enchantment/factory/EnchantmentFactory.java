package mcapi.davidout.model.custom.enchantment.factory;

import mcapi.davidout.model.custom.enchantment.CustomEnchantment;
import mcapi.davidout.model.custom.enchantment.EnchantmentDetails;
import org.bukkit.event.Event;

import java.util.Collections;
import java.util.List;

public class EnchantmentFactory {

    public static CustomEnchantment createEnchantment(EnchantmentDetails enchantmentDetails, List<Class<? extends Event>> events, EnchantmentFunction function) {
        return new FactoryEnchantment(enchantmentDetails, events, function);
    }

    public static CustomEnchantment createEnchantment(EnchantmentDetails enchantmentDetails, Class<? extends Event> event, EnchantmentFunction function) {
        return new FactoryEnchantment(enchantmentDetails, Collections.singletonList(event), function);
    }
}
