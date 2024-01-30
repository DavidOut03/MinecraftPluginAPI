package mcapi.davidout.model;

import mcapi.davidout.manager.enchantment.EnchantmentTarget;
import mcapi.davidout.utillity.TextUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ItemType {

    BLOCK,
    TOOL, PICKAXE, AXE, HOE, SHOVEL, SHEAR, FISHING_ROD, COMPASS, CLOCK, FLINT_AND_STEEL,
    ARMOUR, HELMET , CHESTPLATE, LEGGINGS, BOOTS,
    WEAPON, SWORD, BOW, CROSSBOW, TRIDENT,
    DEFENSE, SHIELD,
    FOOD,
    POTION,
    MATERIAL,
    OTHER;

    public static List<ItemType> getCategories(ItemStack itemStack) {
        List<ItemType> categories = new ArrayList<>();
        Material mat = itemStack.getType();
        String matName = mat.name().toUpperCase();

        categories.add(OTHER);

        if(isBlock(mat)) {
            categories.add(BLOCK);
        }

        if(ItemType.isTool(mat)) {
            categories.add(TOOL);
        }

        if(ItemType.isArmor(mat)) {
            categories.add(ARMOUR);
        }

        if(ItemType.isWeapon(mat)) {
            categories.add(WEAPON);
        }

        if(isMaterial(mat)) {
            categories.add(MATERIAL);
        }

        if(isFood(mat)) {
            categories.add(FOOD);
        }

        if(isPotion(mat)) {
            categories.add(POTION);
        }

        Arrays.stream(values()).filter(target ->
                matName.contains(target.name().toUpperCase())
        ).forEach(itemType -> {
            if(categories.contains(itemType)) return;
            categories.add(itemType);
        });

        return categories;
    }

    public static boolean isBlock(Material material) {
        return material.isBlock();
    }

    public static boolean isTool(Material mat) {
        String matName = mat.name().toUpperCase();
        return matName.endsWith("_PICKAXE") || matName.endsWith("_AXE") || matName.endsWith("_SHOVEL") || matName.endsWith("_SPADE") || mat.name().endsWith("_HOE")
                || mat == Material.SHEARS || mat == Material.FLINT_AND_STEEL || mat == Material.CLOCK || mat == Material.COMPASS || isWeapon(mat);
    }

    public static boolean isWeapon(Material mat) {
        return mat.name().endsWith("_SWORD") || mat == Material.BOW || mat == Material.CROSSBOW || mat.name().contains("TRIDENT");
    }

    public static boolean isArmor(Material mat) {
        String matName = mat.name().toUpperCase();
        return  matName.endsWith("HELMET") || mat.name().endsWith("_CHESTPLATE") || mat.name().endsWith("_LEGGINGS") || mat.name().endsWith("_BOOTS");
    }

    public static boolean isFood(Material mat) {
        return mat.isEdible();
    }

    public static boolean isPotion(Material mat) {
        return mat.isItem() && mat.name().contains("POTION");
    }

    public static boolean isMaterial(Material mat) {
        return mat.name().contains("INGOT") || mat.name().contains("NUGGET");
    }

}
