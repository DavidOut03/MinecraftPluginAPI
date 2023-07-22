package com.davidout.api.minecraft.custom.enchantment;

import com.davidout.api.minecraft.enums.EnchantmentTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchantmentDetails {

    private final String name;
    private final int maxLevel;
    private final int minLevel;
    private final String description;
    private final List<EnchantmentTarget> targetList;

    public EnchantmentDetails(String name, int maxLevel) {
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.description = "";
        this.targetList = new ArrayList<>(Arrays.asList(EnchantmentTarget.ALL));
    }

    public EnchantmentDetails(String name, int maxLevel, String description) {
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.description = description;
        this.targetList = new ArrayList<>(Arrays.asList(EnchantmentTarget.ALL));
    }

    public EnchantmentDetails(String name, int maxLevel, EnchantmentTarget target) {
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.description = "";
        this.targetList = new ArrayList<>(Arrays.asList(target));
    }

    public EnchantmentDetails(String name, int maxLevel, String description, EnchantmentTarget target) {
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.description = description;
        this.targetList = new ArrayList<>(Arrays.asList(target));
    }

    public EnchantmentDetails(String name, int maxLevel, String description, List<EnchantmentTarget> targets) {
        this.name = name;
        this.minLevel = 1;
        this.maxLevel = maxLevel;
        this.description = description;
        this.targetList = targets;
    }

    public String getName() {return name;}
    public int getMinLevel() {return minLevel;}
    public int getMaxLevel() {return maxLevel;}
    public String getDescription() {return description;}
    public List<EnchantmentTarget> getTargets() {return targetList;}




}
