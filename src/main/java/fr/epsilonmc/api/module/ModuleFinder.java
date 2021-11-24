package fr.epsilonmc.api.module;

import java.util.Optional;

public class ModuleFinder {

    public static Optional<EpsilonModule> findModuleOnClass(Object module) {
        Class<?> moduleClass = module.getClass();
        EpsilonModule annotation = moduleClass.getAnnotation(EpsilonModule.class);
        return Optional.of(annotation);
    }

}
