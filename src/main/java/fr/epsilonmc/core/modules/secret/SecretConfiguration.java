package fr.epsilonmc.core.modules.secret;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SecretConfiguration {

    @Getter private final String secretKey = "";
    @Getter private final List<String> whoNeedsSecretLogin = new ArrayList<>();

}
