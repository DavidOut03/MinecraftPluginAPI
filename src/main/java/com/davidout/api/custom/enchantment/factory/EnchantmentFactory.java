package com.davidout.api.custom.enchantment.factory;

import com.davidout.api.custom.enchantment.CustomEnchantment;
import com.davidout.api.custom.enchantment.EnchantmentDetails;
import com.davidout.api.enums.EnchantmentTarget;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;
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
