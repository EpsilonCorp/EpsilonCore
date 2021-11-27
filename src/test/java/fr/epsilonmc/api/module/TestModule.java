package fr.epsilonmc.api.module;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestModule {

    @Test
    @DisplayName("Test if ModuleFinder works fine")
    public void testModuleFinder() {
        fr.epsilonmc.mock.core.modules.test.TestModule testModule = new fr.epsilonmc.mock.core.modules.test.TestModule();
        EpsilonModule epsilonModule = ModuleFinder.findModuleOnClass(testModule);

        assertNotNull(epsilonModule);
        assertEquals("test", epsilonModule.name());
        assertEquals(0, epsilonModule.listeners().length);
        assertEquals(1, epsilonModule.commands().length);
    }



}
