package mcapi.davidout.manager.enchantment.details;

import mcapi.davidout.manager.enchantment.EnchantmentTarget;

import java.util.List;

public abstract class IEnchantmentDetails {

    public abstract String getName();
    public abstract int getMaxLevel();
    public abstract List<EnchantmentTarget> getTargets();
    public abstract List<EnchantmentTarget> getTargetsEnchantmentConflictsWidth();
    public String getDisplayName() {
        return getName().substring(0, 1).toUpperCase() + getName().substring(1).replace("-", " ").replace("_", " ");
    }


}
