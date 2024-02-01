package mcapi.davidout.manager.enchantment;

import mcapi.davidout.manager.enchantment.details.ICustomEnchantment;
import mcapi.davidout.manager.enchantment.enchanter.IEnchanter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnchantmentManager implements IEnchantmentManager {

    private List<ICustomEnchantment> enchantments;
    private IEnchanter enchanter;
    private final Plugin plugin;

    public EnchantmentManager(Plugin plugin, IEnchanter enchanter) {
        this.enchantments = new ArrayList<>();
        this.enchanter = enchanter;
        this.plugin = plugin;
    }

    @Override
    public boolean addEnchantment(ICustomEnchantment enchantment) {
        boolean result = enchantment.registerEnchantment(plugin);
        if(!result) {
            return false;
        }
        enchantments.add(enchantment);
        return true;
    }

    @Override
    public boolean removeEnchantment(ICustomEnchantment enchantment) {
        boolean result = enchantment.unRegisterEnchantment();
        if(!result) {
            return false;
        }
        enchantments.remove(enchantment);
        return true;
    }


    @Override
    public List<ICustomEnchantment> getCustomEnchantments() {
        return this.enchantments;
    }

    @Override
    public IEnchanter getItemEnchanter() {
        return this.enchanter;
    }


}
