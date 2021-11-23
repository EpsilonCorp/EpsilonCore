package fr.epsilonmc.api.command;

@FunctionalInterface
public interface IEpsilonCommand {

    boolean execute(Sender sender, Arguments arguments, CommandInfo info);

}
