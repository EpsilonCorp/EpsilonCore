package fr.epsilonmc.api.io;

import fr.epsilonmc.core.Variables;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatOperations {

    public static void sendPrefixedAndTranslated(CommandSender commandSender, String txt, Object... objects) {
        commandSender.sendMessage(translateColorCode(
                Variables.CHAT_EPSILON_PREFIX + txt,
                objects
        ));
    }

    public static String translateColorCode(String txt, Object... objects) {
        return ChatColor.translateAlternateColorCodes('&', String.format(
                txt, objects
        ));
    }

}
