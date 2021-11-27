package fr.epsilonmc.api.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class EpsilonCommandExecutor implements CommandExecutor {

    private final IEpsilonCommand command;
    private final EpsilonCommand epsilonCommand;

    @Override
    public boolean onCommand(CommandSender bukkitSender, Command bukkitCommand, String label, String[] args) {
        Sender sender = new Sender(bukkitSender);
        Arguments arguments = new Arguments(args);
        // TODO: CommandInfo from plugin.yml
        CommandInfo commandInfo = new CommandInfo(
                epsilonCommand.name(),
                null,
                null,
                epsilonCommand.playerOnly()
        );

        return command.execute(sender, arguments, commandInfo);
    }
}
