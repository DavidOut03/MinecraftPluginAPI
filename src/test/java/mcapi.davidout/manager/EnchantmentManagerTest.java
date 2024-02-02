package mcapi.davidout.manager;

import mcapi.davidout.manager.enchantment.EnchantmentManager;
import mcapi.davidout.manager.enchantment.EnchantmentTarget;
import mcapi.davidout.manager.enchantment.IEnchantmentManager;
import mcapi.davidout.manager.enchantment.details.EnchantmentDetails;
import mcapi.davidout.manager.enchantment.details.ICustomEnchantment;
import mcapi.davidout.manager.enchantment.details.IEnchantmentDetails;
import mcapi.davidout.manager.enchantment.enchanter.IEnchanter;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.mockito.Mockito.when;

public class EnchantmentManagerTest {

    private IEnchantmentManager enchantmentManager;
    private ICustomEnchantment enchantment;

    @Before
    public void setUp() {
        Plugin plugin = Mockito.mock(Plugin.class);
        Server serverMock = Mockito.mock(Server.class);
        PluginManager pluginManagerMock = Mockito.mock(PluginManager.class);

        when(plugin.getServer()).thenReturn(serverMock);
        when(serverMock.getPluginManager()).thenReturn(pluginManagerMock);

        IEnchanter enchanter = Mockito.mock(IEnchanter.class);

        this.enchantmentManager = new EnchantmentManager(plugin, enchanter);
        this.enchantment = new MockEnchantment();
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

    public MockEnchantment() {
        super(new EnchantmentDetails("test", 0, List.of()));
    }

    @Override
    public List<Class<? extends Event>> triggerOnEvents() {
        return List.of(PlayerJoinEvent.class);
    }

    @Override
    public void handleEvent(Event event) {
        // Mock the handling logic if needed
    }
}


