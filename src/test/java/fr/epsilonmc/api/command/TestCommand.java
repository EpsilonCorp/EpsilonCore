package fr.epsilonmc.api.command;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import fr.epsilonmc.mock.core.modules.test.TestModule;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCommand {

    private ServerMock server;
    private Core core;

    @BeforeAll
    public void setUp() {
        server = MockBukkit.mock();
        core = MockBukkit.load(Core.class);
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
