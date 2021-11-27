package fr.epsilonmc.core.modules.secret;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.api.thread.ThreadSafe;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSecretCache {

    private ServerMock server;
    private Core core;

    @BeforeAll
    public void setUp() {
        server = MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @Test
    @DisplayName("Test if Secret Cache works fine by using player quit")
    public void testSecretCacheByQuit() {
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

        PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(epsilonPlayerMock, "");
        core.getServer().getPluginManager().callEvent(playerQuitEvent);
        assertFalse(secretModule.getPlayerCache().contains("253a3fe5-4bce-3192-bf99-6d0f5a0478d1"));
    }

    @Test
    @DisplayName("Test if Secret Cache works fine by using chat secret key")
    public void testSecretCacheByChatSecretKey() {
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

        epsilonPlayerMock.chat("/" + secretModule.getSecretConfiguration().getSecretKey());
        ThreadSafe.sleep(10); // Because chat messages are sent via async threads
        assertFalse(secretModule.getPlayerCache().contains("253a3fe5-4bce-3192-bf99-6d0f5a0478d1"));
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unmock();
    }
}
