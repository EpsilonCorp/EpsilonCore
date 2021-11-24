package fr.epsilonmc.core;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.core.modules.TestModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoreTests {

    private ServerMock server;
    private Core core;

    @Before
    public void setUp() {
        server = MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void testModuleRegistered() {
        ModuleFactory moduleFactory = ModuleFactory.getInstance();
        moduleFactory.registerModule(core, new TestModule());

        assertEquals(TestModule.class, moduleFactory.getModule(TestModule.class).getModule().getClass());
        assertEquals(42, moduleFactory.getModule(TestModule.class).getModule().validateRegistration());
    }


}
