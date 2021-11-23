package fr.epsilonmc.api.module;

import fr.epsilonmc.api.command.IEpsilonCommand;
import lombok.Getter;
import org.bukkit.event.Listener;

public abstract class AbstractModule {

    @Getter private String name;
    @Getter private Listener[] listeners;
    @Getter private IEpsilonCommand[] commands;

}
