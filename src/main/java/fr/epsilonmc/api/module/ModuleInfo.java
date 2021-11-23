package fr.epsilonmc.api.module;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.exception.UnknownEpsilonCommandException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class ModuleInfo {

    private final JavaPlugin plugin;
    private final AbstractModule module;

    public void handleRegistration() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        for (Listener listener : module.getListeners()) {
            pluginManager.registerEvents(listener, plugin);
        }

        for (IEpsilonCommand command : module.getCommands()) {
            Optional<EpsilonCommand> optionalCommand = CommandFinder.findCommandOnClass(command);

            if (optionalCommand.isPresent()) {
                EpsilonCommand epsilonCommand = optionalCommand.get();
                plugin.getCommand(epsilonCommand.name())
                        .setExecutor((CommandSender bukkitSender, Command bukkitCommand, String label, String[] args) -> {
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
                        });
            } else {
                throw new UnknownEpsilonCommandException("Unknown command on %s!", command.getClass().getName());
            }
        }
    }
}
