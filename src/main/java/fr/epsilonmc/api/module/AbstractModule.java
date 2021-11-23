package fr.epsilonmc.api.module;

import lombok.Getter;
import org.bukkit.event.Listener;

public abstract class AbstractModule {

    @Getter private String name;
    @Getter private Listener[] listeners;
}
