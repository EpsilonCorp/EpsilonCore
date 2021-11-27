package fr.epsilonmc.core.modules.helper.repair;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.core.modules.helper.HelperModule;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRepairExpiryPolicy {

    private Core core;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @Test
    @DisplayName("Test if expiry policy works fine")
    public void testExpiryPolicy() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.addAttachment(core, Permissions.HELPER_COMMAND_REPAIR_PATTERN + "1", true);
        HelperModule helperModule = ModuleRegistry.getInstance().getModule(HelperModule.class).getModule();
        helperModule.getPlayerRepairCache().put(epsilonPlayerMock, System.currentTimeMillis());

        assertTrue(helperModule.getPlayerRepairCache().containsKey(epsilonPlayerMock));
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
