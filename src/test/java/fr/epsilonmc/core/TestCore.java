package fr.epsilonmc.core;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.mock.core.modules.test.TestModule;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCore {

    private Core core;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @Test
    @DisplayName("Test the module registration")
    public void testModuleRegistered() {
        ModuleFactory moduleFactory = ModuleFactory.getInstance();
        moduleFactory.registerModule(core, new TestModule());

        assertEquals(TestModule.class, moduleFactory.getModule(TestModule.class).getModule().getClass());
        assertEquals(42, moduleFactory.getModule(TestModule.class).getModule().validateRegistration());
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unmock();
    }

}
