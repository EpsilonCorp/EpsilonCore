package fr.epsilonmc.api.module;

import fr.epsilonmc.api.exception.ModuleRegisterException;

import java.util.Optional;

public class ModuleFinder {

    public static Optional<EpsilonModule> findOptionalModuleOnClass(Object module) {
        Class<?> moduleClass = module.getClass();
        EpsilonModule annotation = moduleClass.getAnnotation(EpsilonModule.class);
        return Optional.of(annotation);
    }

    public static EpsilonModule findModuleOnClass(Object module) {
        Optional<EpsilonModule> optionalEpsilonModule = findOptionalModuleOnClass(module);

        if (!optionalEpsilonModule.isPresent()) {
            throw new ModuleRegisterException("Module not found on class %s!", module.getClass().getName());
        }

        return optionalEpsilonModule.get();
    }

}
