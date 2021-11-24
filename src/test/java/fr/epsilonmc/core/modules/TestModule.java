package fr.epsilonmc.core.modules;

import fr.epsilonmc.api.module.EpsilonModule;

@EpsilonModule(name = "test")
public class TestModule {

    public int validateRegistration() {
        return 42;
    }

}
