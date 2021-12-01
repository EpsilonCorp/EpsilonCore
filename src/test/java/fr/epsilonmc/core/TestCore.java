package fr.epsilonmc.core;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.mock.core.modules.test.TestModule;
import fr.epsilonmc.mock.core.CoreMock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCore {

    private CoreMock coreMock;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        coreMock = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test the module registration")
    public void testModuleRegistered() {
        ModuleRegistry moduleRegistry = ModuleRegistry.getInstance();
        moduleRegistry.registerModule(coreMock, new TestModule());

        assertEquals(TestModule.class, moduleRegistry.getModule(TestModule.class).getModule().getClass());
        assertEquals(42, moduleRegistry.getModule(TestModule.class).getModule().validateRegistration());
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
