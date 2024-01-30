package mcapi.davidout.manager.enchantment;

import mcapi.davidout.model.ItemType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnchantmentTarget {


    ALL,

    ARMOUR, HELMET , CHESTPLATE, LEGGINGS, BOOTS,

    TOOL, PICKAXE, AXE, HOE, SHOVEL, SHEAR, FISHING_ROD,

    WEAPON, SWORD, BOW, CROSSBOW, TRIDENT,

    ELYTRA;


    public static List<EnchantmentTarget> getCategories(ItemStack itemStack) {
        List<EnchantmentTarget> categories = new ArrayList<>();
        Material mat = itemStack.getType();
        String matName = mat.name().toUpperCase();

        categories.add(ALL);
        if(ItemType.isTool(mat)) {
            categories.add(TOOL);
        }

        if(ItemType.isArmor(mat)) {
            categories.add(ARMOUR);
        }

        if(ItemType.isWeapon(mat)) {
            categories.add(WEAPON);
        }

        Arrays.stream(values()).filter(enchantmentTarget ->
            matName.contains(enchantmentTarget.name().toUpperCase())
        ).forEach(categories::add);

        return categories;
    }

    public static boolean isEnchantable(List<EnchantmentTarget> targets, ItemStack itemStack) {
        return getCategories(itemStack).stream().anyMatch(targets::contains);
    }

    public static boolean isEnchantable(EnchantmentTarget target, ItemStack itemStack) {
        return getCategories(itemStack).contains(target);
    }


}
