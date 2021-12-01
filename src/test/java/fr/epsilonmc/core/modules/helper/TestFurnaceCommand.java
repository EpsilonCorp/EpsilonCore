package fr.epsilonmc.core.modules.helper;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.mock.core.CoreMock;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFurnaceCommand {

    private CoreMock coreMock;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        coreMock = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if furnace command works fine")
    public void testFurnaceCommand() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.addAttachment(coreMock, "core.helper.furnace", true);

        epsilonPlayerMock.setItemInHand(new ItemStack(Material.REDSTONE_ORE));
        epsilonPlayerMock.performCommand("furnace");
        assertEquals(Material.REDSTONE, epsilonPlayerMock.getItemInHand().getType());

        epsilonPlayerMock.setItemInHand(new ItemStack(Material.GOLD_ORE));
        epsilonPlayerMock.performCommand("furnace");
        assertEquals(Material.GOLD_INGOT, epsilonPlayerMock.getItemInHand().getType());

        epsilonPlayerMock.setItemInHand(new ItemStack(Material.IRON_ORE));
        epsilonPlayerMock.performCommand("furnace");
        assertEquals(Material.IRON_INGOT, epsilonPlayerMock.getItemInHand().getType());

        epsilonPlayerMock.setItemInHand(new ItemStack(Material.COAL_ORE));
        epsilonPlayerMock.performCommand("furnace");
        assertEquals(Material.COAL, epsilonPlayerMock.getItemInHand().getType());

        epsilonPlayerMock.setItemInHand(new ItemStack(Material.DIAMOND_ORE));
        epsilonPlayerMock.performCommand("furnace");
        assertEquals(Material.DIAMOND, epsilonPlayerMock.getItemInHand().getType());

        epsilonPlayerMock.setItemInHand(new ItemStack(Material.EMERALD_ORE));
        epsilonPlayerMock.performCommand("furnace");
        assertEquals(Material.EMERALD, epsilonPlayerMock.getItemInHand().getType());
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}

