package fr.epsilonmc.core.modules.death;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import fr.epsilonmc.mock.core.CoreMock;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestHeadDrops {

    private CoreMock coreMock;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        coreMock = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if head drops works fine")
    public void testHeadDrops() {
        EpsilonPlayerMock epsilonPlayerMockKiller = new EpsilonPlayerMock("Lucas__Lks");
        EpsilonPlayerMock epsilonPlayerMockKilled = new EpsilonPlayerMock("zordix");

        epsilonPlayerMockKiller.addAttachment(coreMock, Permissions.DEATH_HEAD_DROPS, true);
        epsilonPlayerMockKilled.setKiller(epsilonPlayerMockKiller);

        PlayerDeathEvent playerDeathEvent = new PlayerDeathEvent(
                epsilonPlayerMockKilled,
                Collections.emptyList(),
                0,
                ""
        );
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> coreMock.getServer().getPluginManager().callEvent(playerDeathEvent));
        assertEquals(InvocationTargetException.class.getName(), runtimeException.getMessage());
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
