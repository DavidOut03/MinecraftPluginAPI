package mcapi.davidout.model;

import com.sun.source.tree.AssertTree;
import mcapi.davidout.manager.file.YamlFileManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TestItemType {

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
        checkCategoryTest(armour, Arrays.asList(ItemType.ARMOUR, ItemType.OTHER, ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS));
        checkCategoryTest(tools, Arrays.asList(ItemType.TOOL, ItemType.OTHER, ItemType.PICKAXE, ItemType.AXE, ItemType.HOE, ItemType.SHOVEL, ItemType.SHEAR, ItemType.FISHING_ROD));
        checkCategoryTest(weapons, Arrays.asList(ItemType.OTHER, ItemType.TOOL, ItemType.WEAPON, ItemType.SWORD, ItemType.BOW));
    }

    private void checkCategoryTest(List<ItemStack> items, List<ItemType> expectedCategories) {
        List<ItemType> actualCategories = new ArrayList<>();

        items.forEach(itemStack -> {
            List<ItemType> types = ItemType.getCategories(itemStack);
            List<ItemType> typesToAdd = types.stream().filter(type -> !actualCategories.contains(type)).collect(Collectors.toList());
            actualCategories.addAll(typesToAdd);
        });

        Assert.assertEquals(expectedCategories.size(), actualCategories.size());
        Assert.assertTrue(actualCategories.containsAll(expectedCategories));
    }
}
