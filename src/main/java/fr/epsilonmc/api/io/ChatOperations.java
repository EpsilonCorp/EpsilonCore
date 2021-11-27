package fr.epsilonmc.api.io;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatOperations {

    public static void sendPrefixedAndTranslated(CommandSender commandSender, String txt, Object... objects) {
        commandSender.sendMessage(String.format(
                ChatColor.translateAlternateColorCodes('&', txt),
                objects
        ));
    }

}
