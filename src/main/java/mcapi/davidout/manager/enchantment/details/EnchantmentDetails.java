package mcapi.davidout.manager.enchantment.details;

import mcapi.davidout.manager.enchantment.EnchantmentTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnchantmentDetails extends IEnchantmentDetails {

    private final String name;
    private final int maxLevel;
    private final List<EnchantmentTarget> targets;

    public EnchantmentDetails(String name, int maxLevel, List<EnchantmentTarget> enchantmentTargets) {
        this.name = (name == null) ? UUID.randomUUID().toString() : name;
        this.maxLevel = maxLevel;
        this.targets = (enchantmentTargets == null) ? new ArrayList<>() : enchantmentTargets;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public List<EnchantmentTarget> getTargets() {
        return targets;
    }

    @Override
    public List<EnchantmentTarget> getTargetsEnchantmentConflictsWidth() {
        return null;
    }
}
