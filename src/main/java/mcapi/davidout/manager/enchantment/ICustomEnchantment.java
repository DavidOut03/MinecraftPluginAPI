package mcapi.davidout.manager.enchantment;

import org.bukkit.enchantments.EnchantmentTarget;

import java.util.List;

public interface ICustomEnchantment {

    String getName();
    int getMaxLevel();
    String getDescription();
    List<EnchantmentTarget> getTargets();


}
