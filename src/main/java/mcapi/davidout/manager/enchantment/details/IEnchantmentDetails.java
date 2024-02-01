package mcapi.davidout.manager.enchantment.details;

import mcapi.davidout.manager.enchantment.EnchantmentTarget;

import java.util.List;

public interface IEnchantmentDetails {

    String getName();
    int getMaxLevel();
    List<EnchantmentTarget> getTargets();
    List<EnchantmentTarget> getTargetsEnchantmentConflictsWidth();

}
