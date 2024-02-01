package mcapi.davidout.manager;

import mcapi.davidout.manager.enchantment.EnchantmentManager;
import mcapi.davidout.manager.enchantment.EnchantmentTarget;
import mcapi.davidout.manager.enchantment.IEnchantmentManager;
import mcapi.davidout.manager.enchantment.details.ICustomEnchantment;
import mcapi.davidout.manager.enchantment.details.IEnchantmentDetails;
import mcapi.davidout.manager.enchantment.enchanter.IEnchanter;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class EnchantmentManagerTest {

    private IEnchantmentManager enchantmentManager;
    private ICustomEnchantment enchantment;

    @Before
    public void setUp() {
        Plugin plugin = Mockito.mock(Plugin.class);
        IEnchanter enchanter = Mockito.mock(IEnchanter.class);

        this.enchantmentManager = new EnchantmentManager(plugin, enchanter);
        this.enchantment = new MockEnchantment(new MockEnchantmentDetails());
    }

    @Test
    public void test1_registerEnchantment() {
        enchantmentManager.addEnchantment(enchantment);
        Assert.assertEquals(1, enchantmentManager.getCustomEnchantments().size());
    }

    @Test
    public void test2_unRegisterEnchantment() {
        enchantmentManager.removeEnchantment(enchantment);
        Assert.assertEquals(0, enchantmentManager.getCustomEnchantments().size());
    }
}

class MockEnchantment extends ICustomEnchantment {

    public MockEnchantment(IEnchantmentDetails iEnchantmentDetails) {
        super(iEnchantmentDetails);
    }

    @Override
    public List<Class<? extends Event>> triggerOnEvents() {
        return List.of();
    }

    @Override
    public void handleEvent(Event event) {
        // Mock the handling logic if needed
    }
}

class MockEnchantmentDetails extends IEnchantmentDetails {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public List<EnchantmentTarget> getTargets() {
        return List.of();
    }

    @Override
    public List<EnchantmentTarget> getTargetsEnchantmentConflictsWidth() {
        return List.of();
    }
}


