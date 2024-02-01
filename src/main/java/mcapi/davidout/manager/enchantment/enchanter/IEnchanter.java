package mcapi.davidout.manager.enchantment.enchanter;

import mcapi.davidout.manager.enchantment.details.ICustomEnchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface IEnchanter {

    Map<ICustomEnchantment, Integer> getCustomEnchantments(ItemStack itemStack);

}
