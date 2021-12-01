package fr.epsilonmc.api.module;

import fr.epsilonmc.api.configuration.ConfigurationLoader;
import fr.epsilonmc.api.configuration.EpsilonConfiguration;
import fr.epsilonmc.api.exception.EpsilonRuntimeException;
import fr.epsilonmc.core.Core;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModuleRegistry {

    private ModuleRegistry() {}
    @Getter private final static ModuleRegistry instance = new ModuleRegistry();

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

    @SneakyThrows
    public void unregisterAll() {
        for (ModuleInfo<Object> moduleInfo : moduleInfoMap.values()) {
            final Class<?> aClass = moduleInfo.getModule().getClass();
            for (Field declaredField : aClass.getDeclaredFields()) {
                if (declaredField.getDeclaredAnnotation(EpsilonConfiguration.class) != null) {
                    declaredField.setAccessible(true);
                    ConfigurationLoader.save(Core.getInstance(), moduleInfo.getModule(), declaredField.get(moduleInfo.getModule()));
                }
            }


            HandlerList.unregisterAll(moduleInfo.getPlugin()); // listeners
        }
        
        moduleInfoMap.clear();
    }
}
