package fr.epsilonmc.core.modules.secret;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.bukkit.event.player.PlayerLoginEvent;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SecretTest {

    private ServerMock server;
    private Core core;

    @BeforeAll
    public void setUp() {
        server = MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @Test
    @DisplayName("Test if Secret Cache works fine")
    public void testSecretCache() {
        SecretModule secretModule = ModuleFactory.getInstance().getModule(SecretModule.class).getModule();
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock(server, "Lucas__Lks");
        epsilonPlayerMock.setOp(true);

        PlayerLoginEvent playerLoginEvent = new PlayerLoginEvent(
                epsilonPlayerMock,
                "",
                null,
                PlayerLoginEvent.Result.ALLOWED,
                ""
        );
        core.getServer().getPluginManager().callEvent(playerLoginEvent);

        assertTrue(secretModule.getPlayerCache().contains("253a3fe5-4bce-3192-bf99-6d0f5a0478d1"));
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unmock();
    }
}
