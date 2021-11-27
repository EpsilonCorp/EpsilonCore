package fr.epsilonmc.mock.core.modules.test;

import fr.epsilonmc.api.module.EpsilonModule;

@EpsilonModule(name = "test", commands = TestCommand.class)
public class TestModule {

    public int validateRegistration() {
        return 42;
    }

}
