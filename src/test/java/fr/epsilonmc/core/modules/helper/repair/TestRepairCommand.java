package fr.epsilonmc.core.modules.helper.repair;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.core.Variables;
import fr.epsilonmc.core.modules.helper.HelperModule;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRepairCommand {

    private Core core;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @Test
    @DisplayName("Test if repair command works fine")
    public void testRepairCommand() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.addAttachment(core, "core.helper.repair", true);
        epsilonPlayerMock.addAttachment(core, Permissions.HELPER_COMMAND_REPAIR_PATTERN + "60", true);

        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        itemStack.setDurability((short) 10);

        epsilonPlayerMock.setItemInHand(itemStack);

        assertEquals(10, epsilonPlayerMock.getItemInHand().getDurability());
        epsilonPlayerMock.performCommand("repair");
        epsilonPlayerMock.assertSaid(ChatOperations.translateColorCode(Variables.CHAT_EPSILON_PREFIX+"&aL'item dans votre main vient d'être réparé !"));
        assertEquals(0, epsilonPlayerMock.getItemInHand().getDurability());
        epsilonPlayerMock.performCommand("repair");
        epsilonPlayerMock.assertSaid(ChatOperations.translateColorCode(Variables.CHAT_EPSILON_PREFIX+"&cAttendez encore avant de relancer un repair."));
    }

    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
