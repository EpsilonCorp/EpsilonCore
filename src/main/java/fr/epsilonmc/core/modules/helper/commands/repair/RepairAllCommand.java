package fr.epsilonmc.core.modules.helper.commands.repair;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.modules.helper.HelperModule;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RepairAllCommand implements IEpsilonCommand {

    private HelperModule helperModule;

    @Override
    @EpsilonCommand(name = "repairall", playerOnly = true)
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        if (helperModule == null) {
            helperModule = ModuleRegistry.getInstance().getModule(HelperModule.class).getModule();
        }

        Player player = sender.getPlayer();

        if (helperModule.getPlayerRepairAllCache().containsKey(sender.getPlayer())) {
            return chatAndStop(sender, "&cAttendez encore avant de relancer un repairall.");
        }

        for (ItemStack itemStack : player.getInventory()) {
            RepairOperations.repair(itemStack);
        }

        helperModule.getPlayerRepairAllCache().put(player, System.currentTimeMillis());
        return chatAndStop(sender, "&aLes items de votre inventaire viennent d'être réparés !");
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
