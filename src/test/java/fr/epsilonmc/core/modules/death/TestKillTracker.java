package fr.epsilonmc.core.modules.death;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.external.ItemBuilder;
import fr.epsilonmc.core.Variables;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import fr.epsilonmc.mock.core.CoreMock;
import org.bukkit.Material;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestKillTracker {

    private CoreMock coreMock;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        coreMock = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if kill tracker works fine")
    public void testKillTracker() {
        EpsilonPlayerMock epsilonPlayerMockKiller = new EpsilonPlayerMock("Lucas__Lks");
        EpsilonPlayerMock epsilonPlayerMockKilled = new EpsilonPlayerMock("zordix");

        epsilonPlayerMockKilled.setKiller(epsilonPlayerMockKiller);

        ItemStack weapon = new ItemBuilder(Material.DIAMOND_SWORD)
                .setLore(Variables.DEATH_KILLS_COUNT + 2)
                .toItemStack();
        epsilonPlayerMockKiller.setItemInHand(weapon);

        PlayerDeathEvent playerDeathEvent = new PlayerDeathEvent(
                epsilonPlayerMockKilled,
                Collections.emptyList(),
                0,
                ""
        );
        coreMock.getServer().getPluginManager().callEvent(playerDeathEvent);

        assertEquals(Variables.DEATH_KILLS_COUNT + 3, playerDeathEvent.getEntity().getKiller().getItemInHand().getItemMeta().getLore().get(0));
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
