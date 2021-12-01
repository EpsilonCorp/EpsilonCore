package fr.epsilonmc.core.modules.secret;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.mock.core.CoreMock;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSecretSecurity {

    private ServerMock server;
    private CoreMock coreMock;

    @BeforeAll
    public void setUp() {
        server = MockBukkit.mock();
        coreMock = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if Secret Security works fine")
    public void testSecretSecurity() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        SecretModule secretModule = ModuleRegistry.getInstance().getModule(SecretModule.class).getModule();
        PluginManager pluginManager = coreMock.getServer().getPluginManager();

        secretModule.getPlayerCache().add(epsilonPlayerMock.getUniqueId().toString());

        // Only 1 event is necessary because all the others are the same
        PlayerMoveEvent playerMoveEvent = new PlayerMoveEvent(epsilonPlayerMock, null, null);
        pluginManager.callEvent(playerMoveEvent);
        assertTrue(playerMoveEvent.isCancelled());
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }
}