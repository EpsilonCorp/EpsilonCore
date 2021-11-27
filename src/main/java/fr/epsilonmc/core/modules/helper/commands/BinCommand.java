package fr.epsilonmc.core.modules.helper.commands;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.io.ChatOperations;
import org.bukkit.Bukkit;

public class BinCommand implements IEpsilonCommand {
    @Override
    @EpsilonCommand(name = "poubelle", playerOnly = true)
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        sender.getPlayer().openInventory(Bukkit.createInventory(
                null,
                54,
                ChatOperations.translateColorCode("&8» Poubelle")
        ));
        return false;
    }
}
