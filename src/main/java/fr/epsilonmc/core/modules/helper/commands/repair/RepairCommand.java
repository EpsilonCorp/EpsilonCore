package fr.epsilonmc.core.modules.helper.commands.repair;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.modules.helper.HelperModule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RepairCommand implements IEpsilonCommand {

    private HelperModule helperModule;

    @Override
    @EpsilonCommand(name = "repair", playerOnly = true)
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        if (helperModule == null) {
            helperModule = ModuleRegistry.getInstance().getModule(HelperModule.class).getModule();
        }

        Player player = sender.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material;

        if (helperModule.getPlayerRepairCache().containsKey(sender.getPlayer())) {
            return chatAndStop(sender, "&cAttendez encore avant de relancer un repair.");
        }

        if (itemStack == null
                || (material = itemStack.getType()) == Material.AIR
                || material.isBlock()
                || material.getMaxDurability() < 1) {
            return chatAndStop(sender, "&cCet item n'est pas réparable.");
        }

        itemStack.setDurability((short) 0);
        helperModule.getPlayerRepairCache().put(sender.getPlayer(), System.currentTimeMillis());
        return chatAndStop(sender, "&aL'item dans votre main vient d'être réparé !");
    }

    private boolean chatAndStop(Sender sender, String txt, Object... objects)  {
        ChatOperations.sendPrefixedAndTranslated(
                sender.getPlayer(),
                txt,
                objects
        );
        return true;
    }

}
