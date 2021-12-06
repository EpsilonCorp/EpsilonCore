package fr.epsilonmc.core.modules.prevent;

import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.modules.prevent.listeners.ExploitFixerListener;

@EpsilonModule(
        name = "prevent",
        listeners = {
                ExploitFixerListener.class,
                ReserveFixListener.class,
        })
public class PreventModule {
}
