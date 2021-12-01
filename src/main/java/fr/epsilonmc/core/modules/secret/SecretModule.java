package fr.epsilonmc.core.modules.secret;

import fr.epsilonmc.api.configuration.ConfigurationLoader;
import fr.epsilonmc.api.configuration.EpsilonConfiguration;
import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.core.modules.secret.listeners.SecretCacheListener;
import fr.epsilonmc.core.modules.secret.listeners.SecretSecurityListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EpsilonModule(name = "Secret", listeners = {SecretCacheListener.class, SecretSecurityListener.class})
public class SecretModule {

    @Getter
    private final List<String> playerCache = new ArrayList<>();

    @Getter
    @EpsilonConfiguration
    private final SecretConfiguration secretConfiguration = ConfigurationLoader.loadOrGenerate(
            Core.getInstance(),
            this,
            SecretConfiguration.class
    );
}
