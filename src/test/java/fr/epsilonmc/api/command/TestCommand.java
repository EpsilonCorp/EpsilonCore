package fr.epsilonmc.api.command;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import fr.epsilonmc.mock.core.CoreMock;
import fr.epsilonmc.mock.core.modules.test.TestModule;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCommand {

    private CoreMock core;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        core = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test the command api")
    public void testCommandApi() {
        ModuleRegistry moduleRegistry = ModuleRegistry.getInstance();
        moduleRegistry.registerModule(core, new TestModule());

        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        assertTrue(epsilonPlayerMock.performCommand("test"));
        epsilonPlayerMock.assertSaid("test");
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }


}
