package fr.epsilonmc.core.modules.death;

import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.modules.death.listeners.DeathMessageListener;
import fr.epsilonmc.core.modules.death.listeners.HeadDropsListener;
import fr.epsilonmc.core.modules.death.listeners.KillTrackerListener;

@EpsilonModule(
        name = "death",
        listeners = {
                HeadDropsListener.class,
                DeathMessageListener.class,
                KillTrackerListener.class
        })
public class DeathModule {

}
