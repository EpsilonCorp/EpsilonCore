package fr.epsilonmc.api.module;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.exception.UnknownEpsilonCommandException;
import fr.epsilonmc.api.type.TypeConverter;
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
public class ModuleInfo<T> {

    private final JavaPlugin plugin;
    private final EpsilonModule epsilonModule;
    private final T module;

    public void handleRegistration() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();


        for (Listener listener : TypeConverter.instantiateArray(epsilonModule.listeners())) {
            pluginManager.registerEvents(listener, plugin);
        }

        for (IEpsilonCommand command : TypeConverter.instantiateArray(epsilonModule.commands())) {
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
