package fr.epsilonmc.api.module;

import fr.epsilonmc.api.exception.EpsilonRuntimeException;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ModuleFactory {

    private ModuleFactory() {}
    @Getter private final static ModuleFactory instance = new ModuleFactory();

    private final Map<Class<? extends AbstractModule>, ModuleInfo> moduleInfoMap = new HashMap<>();

    public void registerModule(JavaPlugin plugin, AbstractModule module) {
        if (moduleInfoMap.containsKey(module.getClass())) {
            throw new EpsilonRuntimeException("Module %s already registered!", module.getName());
        }

        ModuleInfo moduleInfo = new ModuleInfo(plugin, module);
        moduleInfo.handleRegistration();
        moduleInfoMap.put(module.getClass(), moduleInfo);
    }
}
