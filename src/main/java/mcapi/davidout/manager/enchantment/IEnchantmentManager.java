package mcapi.davidout.manager.enchantment;

import java.util.List;

public interface IEnchantmentManager {

    List<ICustomEnchantment> getCustomEnchantments();
    boolean addEnchantment(ICustomEnchantment enchantment);
    boolean removeEnchantment(ICustomEnchantment enchantment);

}
