package fr.epsilonmc.api.module;

import fr.epsilonmc.api.exception.EpsilonRuntimeException;
import fr.epsilonmc.api.exception.ModuleRegisterException;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModuleFactory {

    private ModuleFactory() {}
    @Getter private final static ModuleFactory instance = new ModuleFactory();

    private final Map<Class<?>, ModuleInfo> moduleInfoMap = new HashMap<>();

    public void registerModule(JavaPlugin plugin, Object module) {
        Optional<EpsilonModule> optionalEpsilonModule = ModuleFinder.findModuleOnClass(module);
        EpsilonModule epsilonModule;

        if (!optionalEpsilonModule.isPresent()) {
            throw new ModuleRegisterException("Module not found on class %s!", module.getClass().getName());
        }
        epsilonModule = optionalEpsilonModule.get();

        if (moduleInfoMap.containsKey(module.getClass())) {
            throw new EpsilonRuntimeException("Module %s already registered!", epsilonModule.name());
        }

        ModuleInfo moduleInfo = new ModuleInfo(plugin, epsilonModule);
        moduleInfo.handleRegistration();
        moduleInfoMap.put(module.getClass(), moduleInfo);
    }
}
