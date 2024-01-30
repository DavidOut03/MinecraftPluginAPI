package mcapi.davidout.model;

import mcapi.davidout.manager.enchantment.EnchantmentTarget;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestEnchantmentTarget {

    private List<ItemStack> armour;
    private List<ItemStack> tools;
    private List<ItemStack> weapons;

    @Before
    public void setUp() {
        armour = Arrays.asList(
                new ItemStack(Material.DIAMOND_HELMET),
                new ItemStack(Material.GOLDEN_CHESTPLATE),
                new ItemStack(Material.CHAINMAIL_BOOTS),
                new ItemStack(Material.IRON_LEGGINGS)
        );

        weapons = Arrays.asList(
                new ItemStack(Material.DIAMOND_SWORD),
                new ItemStack(Material.BOW)
        );

        tools = Arrays.asList(
                new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.IRON_AXE),
                new ItemStack(Material.GOLDEN_HOE),
                new ItemStack(Material.DIAMOND_SHOVEL),
                new ItemStack(Material.SHEARS),
                new ItemStack(Material.FISHING_ROD)
        );
    }

    @Test
    public void checkCategories() {
        Assert.assertTrue(EnchantmentTarget.getCategories(new ItemStack(Material.BOOK)).contains(EnchantmentTarget.ALL));
        checkCategoryTest(armour, Arrays.asList(EnchantmentTarget.ARMOUR, EnchantmentTarget.ALL, EnchantmentTarget.HELMET, EnchantmentTarget.CHESTPLATE, EnchantmentTarget.LEGGINGS, EnchantmentTarget.BOOTS));
        checkCategoryTest(tools, Arrays.asList(EnchantmentTarget.TOOL, EnchantmentTarget.ALL, EnchantmentTarget.PICKAXE, EnchantmentTarget.AXE, EnchantmentTarget.HOE, EnchantmentTarget.SHOVEL, EnchantmentTarget.SHEAR, EnchantmentTarget.FISHING_ROD));
        checkCategoryTest(weapons, Arrays.asList(EnchantmentTarget.ALL, EnchantmentTarget.TOOL, EnchantmentTarget.WEAPON, EnchantmentTarget.SWORD, EnchantmentTarget.BOW));
    }

    private void checkCategoryTest(List<ItemStack> items, List<EnchantmentTarget> expectedCategories) {
        List<EnchantmentTarget> actualCategories = new ArrayList<>();

        items.forEach(itemStack -> {
            List<EnchantmentTarget> types = EnchantmentTarget.getCategories(itemStack);
            List<EnchantmentTarget> typesToAdd = types.stream().filter(type -> !actualCategories.contains(type)).collect(Collectors.toList());
            actualCategories.addAll(typesToAdd);
        });

        Assert.assertEquals(expectedCategories.size(), actualCategories.size());
        Assert.assertTrue(actualCategories.containsAll(expectedCategories));
    }

}
