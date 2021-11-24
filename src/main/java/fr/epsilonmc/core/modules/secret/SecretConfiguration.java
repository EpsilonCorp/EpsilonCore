package fr.epsilonmc.core.modules.secret;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SecretConfiguration {

    @Getter private final String secretKey = "";
    @Getter private final List<String> whoNeedsSecretLogin = new ArrayList<String>(){{
        add("253a3fe5-4bce-3192-bf99-6d0f5a0478d1"); // Tests purposes
    }};

}
