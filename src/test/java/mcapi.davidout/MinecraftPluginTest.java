package mcapi.davidout;


import mcapi.davidout.manager.language.bundle.IMessageBundle;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JavaPlugin.class, MinecraftPlugin.class}) // Include MinecraftPlugin in the PrepareForTest annotation
public class MinecraftPluginTest {

    @Mock
    private Server serverMock;
    @Mock
    private PluginManager pluginManagerMock;
    @Mock
    private JavaPlugin javaPluginMock;

    private MinecraftPlugin plugin;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockStatic(JavaPlugin.class);
        when(JavaPlugin.getPlugin(MinecraftPlugin.class)).thenReturn((MinecraftPlugin) javaPluginMock);

        when(javaPluginMock.getServer()).thenReturn(serverMock);
        when(serverMock.getPluginManager()).thenReturn(pluginManagerMock);

        plugin = new MinecraftPlugin() {
            @Override
            public void onStartup() {

            }

            @Override
            public void onShutdown() {

            }

            @Override
            public List<IMessageBundle> getDefaultMessageBundles() {
                return List.of(new MockBundle());
            }
        };
    }

    @Test
    public void test1_setUp() {
        plugin.setUp();
    }

    class MockBundle implements IMessageBundle {

        @Override
        public String getName() {
            return "testBundle";
        }

        @Override
        public Map<String, String> getMessages() {
            Map<String, String> map = new HashMap<>();
            map.put("test1", "this is test message 1.");
            map.put("test2", "this is test message 2.");
            return map;
        }
    }
}
