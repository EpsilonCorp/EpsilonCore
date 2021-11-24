package fr.epsilonmc.core;

import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.core.modules.exp.ExpModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        ModuleFactory moduleFactory = ModuleFactory.getInstance();
        moduleFactory.registerModule(this, new ExpModule());
    }

}
