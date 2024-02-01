package mcapi.davidout.manager.enchantment.enchanter;

import mcapi.davidout.manager.enchantment.details.ICustomEnchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ItemEnchanter implements IEnchanter {
    @Override
    public Map<ICustomEnchantment, Integer> getCustomEnchantments(ItemStack itemStack) {
        return null;
    }
}
