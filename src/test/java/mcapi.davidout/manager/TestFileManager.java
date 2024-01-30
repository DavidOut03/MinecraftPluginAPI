package mcapi.davidout.manager;

import mcapi.davidout.manager.file.IFileManager;
import mcapi.davidout.manager.file.YamlFileManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFileManager {

    private IFileManager fileManager;
    private MockConfig config;
    private String testPath;

    @Before
    public void setUp() {
        File folder = new File("src/test/dump/");
        folder.mkdir();

        fileManager = new YamlFileManager();
        config = createMockConfig();
        testPath = "src/test/dump/test";
    }

    @Test
    public void test1_SaveFileSuccessfully() throws IOException {
        boolean result = fileManager.saveFile(config, testPath);
        Assert.assertTrue("File not saved successfully", result);
    }

    @Test
    public void test2_LoadFileAndVerifyContents() throws IOException {
        MockConfig loadedConfig = fileManager.loadFile(MockConfig.class, testPath);
        Assert.assertNotNull("Loaded configuration is null", loadedConfig);
        assertMockConfigsEqual(config, loadedConfig);
    }

    private void assertMockConfigsEqual(MockConfig expected, MockConfig actual) {
        Assert.assertEquals("Plugin names do not match", expected.pluginName, actual.pluginName);
        Assert.assertEquals("Versions do not match", expected.version, actual.version);
        Assert.assertEquals("Max players do not match", expected.maxPlayers, actual.maxPlayers);
        Assert.assertEquals("Object lists do not match", expected.objectList.size(), actual.objectList.size());
    }

    private MockConfig createMockConfig() {
        List<MockObject> objectList = new ArrayList<>();
        objectList.add(new MockObject("test", 1));
        objectList.add(new MockObject("test2", 2));
        objectList.add(new MockObject("test3", 3));
        return new MockConfig("test-plugin", "1.0.0", 10, objectList);
    }

}

class MockConfig {

    public String pluginName;
    public String version;
    public int maxPlayers;
    public List<MockObject> objectList;

    public MockConfig() {
        this.pluginName = "";
        this.version = "";
        this.maxPlayers = 0;
        this.objectList = new ArrayList<>();
    }

    public MockConfig(String pluginName, String version, int maxPlayers, List<MockObject> objectList) {
        this.pluginName = pluginName;
        this.version = version;
        this.maxPlayers = maxPlayers;
        this.objectList = objectList;
    }

}

class MockObject {

    public String name;
    public int age;

    public MockObject() {
        this.name = "";
        this.age = 0;
    }

    public MockObject(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
