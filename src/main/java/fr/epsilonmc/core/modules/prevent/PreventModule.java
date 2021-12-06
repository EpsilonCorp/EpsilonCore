package fr.epsilonmc.core.modules.prevent;

import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.modules.prevent.listeners.ExploitFixerListener;
import fr.epsilonmc.core.modules.prevent.listeners.ReserveFixListener;
import fr.epsilonmc.core.modules.prevent.listeners.WitherSpawnPreventListener;

@EpsilonModule(
        name = "prevent",
        listeners = {
                ExploitFixerListener.class,
                ReserveFixListener.class,
                WitherSpawnPreventListener.class
        })
public class PreventModule {
}
