package fr.epsilonmc.api.command;

import java.lang.reflect.Method;
import java.util.Optional;

public class CommandFinder {

    public static Optional<EpsilonCommand> findCommandOnClass(IEpsilonCommand command) {
        Optional<EpsilonCommand> epsilonCommand = Optional.empty();
        Class<? extends IEpsilonCommand> commandClass = command.getClass();

        try {
            Method execute = commandClass.getDeclaredMethod("execute", Sender.class, Arguments.class, CommandInfo.class);
            EpsilonCommand epsilonCommandAnnotation = execute.getAnnotation(EpsilonCommand.class);
            epsilonCommand = Optional.of(epsilonCommandAnnotation);
        } catch (NoSuchMethodException ignored) {
            System.err.printf("Class %s does not contains any command!\n", command.getClass().getName());
        }

        return epsilonCommand;
    }

}
