package mcapi.davidout.manager.enchantment;

import mcapi.davidout.manager.enchantment.details.ICustomEnchantment;
import mcapi.davidout.manager.enchantment.enchanter.IEnchanter;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public interface IEnchantmentManager {


    boolean addEnchantment(ICustomEnchantment enchantment);
    boolean removeEnchantment(ICustomEnchantment enchantment);

    List<ICustomEnchantment> getCustomEnchantments();

    IEnchanter getItemEnchanter();

}
