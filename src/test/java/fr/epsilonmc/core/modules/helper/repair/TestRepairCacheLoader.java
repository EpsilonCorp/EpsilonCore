package fr.epsilonmc.core.modules.helper.repair;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.core.modules.helper.HelperModule;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import fr.epsilonmc.mock.core.CoreMock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRepairCacheLoader {

    private CoreMock coreMock;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        coreMock = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if loading cache works fine")
    public void testCacheLoader() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.addAttachment(coreMock, Permissions.HELPER_COMMAND_REPAIR_PATTERN + "1", true);
        HelperModule helperModule = ModuleRegistry.getInstance().getModule(HelperModule.class).getModule();
        helperModule.getPlayerRepairCache().getUnchecked(epsilonPlayerMock);

        assertNotNull(helperModule.getPlayerRepairCache().getIfPresent(epsilonPlayerMock));
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
