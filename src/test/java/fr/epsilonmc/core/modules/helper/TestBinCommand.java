package fr.epsilonmc.core.modules.helper;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.mock.core.CoreMock;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBinCommand {

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if bin command works fine")
    public void testBinCommand() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.performCommand("poubelle");

        assertEquals(ChatOperations.translateColorCode("&8» Poubelle"), epsilonPlayerMock.getOpenInventory().getTitle());
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
