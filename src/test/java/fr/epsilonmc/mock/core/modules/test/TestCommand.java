package fr.epsilonmc.mock.core.modules.test;

import fr.epsilonmc.api.command.*;

public class TestCommand implements IEpsilonCommand {

    @Override
    @EpsilonCommand(name = "test")
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        sender.getCommandSender().sendMessage("test");
        return true;
    }
}
