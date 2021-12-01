package fr.epsilonmc.core.modules.death;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.external.ItemBuilder;
import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import fr.epsilonmc.mock.core.CoreMock;
import org.bukkit.Material;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDeathMessages {

    private CoreMock coreMock;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        coreMock = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if death message without killer works fine")
    public void testDeathMessageWithoutKiller() {
        EpsilonPlayerMock epsilonPlayerMockKilled = new EpsilonPlayerMock("Lucas__Lks");

        PlayerDeathEvent playerDeathEvent = new PlayerDeathEvent(
                epsilonPlayerMockKilled,
                Collections.emptyList(),
                0,
                ""
        );
        coreMock.getServer().getPluginManager().callEvent(playerDeathEvent);

        assertEquals(
                ChatOperations.translateColorCode(
                        "&7[&c\u2717&7] &6%s &eest mort tout seul...",
                        epsilonPlayerMockKilled.getName()
                ),
                playerDeathEvent.getDeathMessage()
        );
    }

    @Test
    @DisplayName("Test if death message with killer works fine")
    public void testDeathMessageWithKiller() {
        EpsilonPlayerMock epsilonPlayerMockKilled = new EpsilonPlayerMock("Lucas__Lks");
        EpsilonPlayerMock epsilonPlayerMockKiller = new EpsilonPlayerMock("zordix");
        epsilonPlayerMockKilled.setKiller(epsilonPlayerMockKiller);

        PlayerDeathEvent playerDeathEvent = new PlayerDeathEvent(
                epsilonPlayerMockKilled,
                Collections.emptyList(),
                0,
                ""
        );
        coreMock.getServer().getPluginManager().callEvent(playerDeathEvent);

        assertEquals(
                ChatOperations.translateColorCode(
                        "&7[&c\u2717&7] &6%s &ea été tué par &6%s.",
                        epsilonPlayerMockKilled.getName(),
                        epsilonPlayerMockKiller.getName()
                ),
                playerDeathEvent.getDeathMessage()
        );
    }

    @Test
    @DisplayName("Test if death message with killer and weapon works fine")
    public void testDeathMessageWithKillerAndWeapon() {
        EpsilonPlayerMock epsilonPlayerMockKilled = new EpsilonPlayerMock("Lucas__Lks");
        EpsilonPlayerMock epsilonPlayerMockKiller = new EpsilonPlayerMock("zordix");
        epsilonPlayerMockKilled.setKiller(epsilonPlayerMockKiller);

        ItemStack weapon = new ItemBuilder(Material.DIAMOND_SWORD).setName("WEAPON").toItemStack();
        epsilonPlayerMockKiller.setItemInHand(weapon);

        PlayerDeathEvent playerDeathEvent = new PlayerDeathEvent(
                epsilonPlayerMockKilled,
                Collections.emptyList(),
                0,
                ""
        );
        coreMock.getServer().getPluginManager().callEvent(playerDeathEvent);

        assertEquals(
                ChatOperations.translateColorCode(
                        "&7[&c\u2717&7] &6%s &ea été tué par &6%s &eavec %s.",
                        epsilonPlayerMockKilled.getName(),
                        epsilonPlayerMockKiller.getName(),
                        weapon.getItemMeta().getDisplayName()
                ),
                playerDeathEvent.getDeathMessage()
        );
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
