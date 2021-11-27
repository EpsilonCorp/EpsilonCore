package fr.epsilonmc.core.modules.helper.commands;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.api.io.ProxyOperations;
import fr.epsilonmc.core.Permissions;

public class InvestCommand implements IEpsilonCommand {
    @Override
    @EpsilonCommand(name = "invest", playerOnly = true)
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        ProxyOperations.switchPlayerServer(
                sender.getPlayer(),
                "invest",
                sender.getPlayer().hasPermission(Permissions.VIP_QUEUE_BYPASS)
        );
        ChatOperations.sendPrefixedAndTranslated(sender.getPlayer(), "&cVous ajouté à la file d'attente de l'Invest !");
        return true;
    }
}
