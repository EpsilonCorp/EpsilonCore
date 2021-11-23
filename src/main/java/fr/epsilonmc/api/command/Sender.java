package fr.epsilonmc.api.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
public class Sender {

    private final CommandSender commandSender;

    public Player getPlayer() {
        return (Player) commandSender;
    }

    public boolean isPlayer() {
        return commandSender instanceof Player;
    }

}
