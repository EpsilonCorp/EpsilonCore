package fr.epsilonmc.core.modules.exp;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.plugin.PluginManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestExp {

    private ServerMock server;
    private Core core;

    @BeforeAll
    public void setUp() {
        server = MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @Test
    @DisplayName("Test if exp multiplier works fine")
    public void testExpMultiplier() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.addAttachment(core, Permissions.EXP_MULTIPLIER_PATTERN + "79", true);
        PluginManager pluginManager = core.getServer().getPluginManager();

        // Only 1 event is necessary because all the others are the same
        PlayerExpChangeEvent playerExpChangeEvent = new PlayerExpChangeEvent(epsilonPlayerMock, 23);
        pluginManager.callEvent(playerExpChangeEvent);
        assertEquals(Math.floor(23 * 1.79), playerExpChangeEvent.getAmount());
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
