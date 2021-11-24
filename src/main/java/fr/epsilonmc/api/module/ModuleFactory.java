package fr.epsilonmc.api.module;

import fr.epsilonmc.api.exception.EpsilonRuntimeException;
import fr.epsilonmc.api.exception.ModuleRegisterException;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModuleFactory {

    private ModuleFactory() {}
    @Getter private final static ModuleFactory instance = new ModuleFactory();

    private final Map<Class<?>, ModuleInfo<Object>> moduleInfoMap = new HashMap<>();

    public <T> ModuleInfo<? extends T> getModule(Class<? extends T> moduleClass) {
        return getOptionalModule(moduleClass).orElse(null);
    }

    public <T> Optional<ModuleInfo<T>> getOptionalModule(Class<? extends T> moduleClass) {
        ModuleInfo<T> moduleInfo = (ModuleInfo<T>) moduleInfoMap.getOrDefault(moduleClass, null);
        return Optional.ofNullable(moduleInfo);
    }

    public void registerModule(JavaPlugin plugin, Object module) {
        EpsilonModule epsilonModule = ModuleFinder.findModuleOnClass(module);

        if (moduleInfoMap.containsKey(module.getClass())) {
            throw new EpsilonRuntimeException("Module %s already registered!", epsilonModule.name());
        }


        ModuleInfo<Object> moduleInfo = new ModuleInfo<>(plugin, epsilonModule, module);
        moduleInfoMap.put(module.getClass(), moduleInfo);

        moduleInfo.handleRegistration();
    }
}
