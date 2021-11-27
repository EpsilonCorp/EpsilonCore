package fr.epsilonmc.core.modules.helper.commands;

import fr.epsilonmc.api.command.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class AnvilCommand implements IEpsilonCommand {
    @Override
    @EpsilonCommand(name = "anvil", playerOnly = true)
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        EntityPlayer entityPlayer = ((CraftPlayer) sender.getPlayer()).getHandle();
        AnvilContainer anvilContainer = new AnvilContainer(entityPlayer);
        int counter = entityPlayer.nextContainerCounter();
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutOpenWindow(
                counter,
                "minecraft:anvil",
                new ChatMessage("Repairing"),
                0
        ));

        entityPlayer.activeContainer = anvilContainer;
        entityPlayer.activeContainer.windowId = counter;
        entityPlayer.activeContainer.addSlotListener(entityPlayer);
        entityPlayer.activeContainer = anvilContainer;
        entityPlayer.activeContainer.windowId = counter;

        return true;
    }

    public static class AnvilContainer extends ContainerAnvil {
        public AnvilContainer(EntityHuman entity) {
            super(entity.inventory, entity.world, new BlockPosition(0,0,0), entity);
        }

        public boolean a(EntityHuman entityhuman) {
            return true;
        }
    }
}
