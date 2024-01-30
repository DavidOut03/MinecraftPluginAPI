package mcapi.davidout.manager;

import mcapi.davidout.manager.file.YamlFileManager;
import mcapi.davidout.manager.language.IMessageManager;
import mcapi.davidout.manager.language.MessageManager;
import mcapi.davidout.manager.language.bundle.IMessageBundle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMessageManager {

    private IMessageManager messageManager;
    private MockBundle mockBundle;


    @Before
    public void setUp() {
        File folder = new File("src/test/dump/messages");
        folder.mkdir();
        messageManager = new MessageManager(new YamlFileManager(folder));
        mockBundle = new MockBundle();
    }

    @Test
    public void test1_saveMessageBundles() {
        messageManager.addMessageBundle(mockBundle);

        boolean saved = messageManager.saveMessageBundles();

        Assert.assertTrue(saved);
    }

    @Test
    public void test2_loadMessageBundles() {
        boolean loaded = messageManager.loadMessageBundles();

        List<IMessageBundle> bundles = messageManager.getBundles();
        IMessageBundle bundle = bundles.get(0);

        Assert.assertTrue("Could not load message bundles.", loaded);
        Assert.assertNotNull("List of bundles is empty.", bundles);
        Assert.assertNotNull("Expected bundle cannot be found.", bundle);
        Assert.assertEquals("Loaded bundle is not the same as expected bundle.", bundle.getName(), mockBundle.getName());
    }



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
