package fr.epsilonmc.core;

import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.modules.death.DeathModule;
import fr.epsilonmc.core.modules.exp.ExpModule;
import fr.epsilonmc.core.modules.helper.HelperModule;
import fr.epsilonmc.core.modules.prevent.PreventModule;
import fr.epsilonmc.core.modules.secret.SecretModule;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class Core extends JavaPlugin {

    @Getter @Setter
    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        ModuleRegistry moduleRegistry = ModuleRegistry.getInstance();
        moduleRegistry.registerModule(this, new ExpModule());
        moduleRegistry.registerModule(this, new SecretModule());
        moduleRegistry.registerModule(this, new HelperModule());
        moduleRegistry.registerModule(this, new DeathModule());
        moduleRegistry.registerModule(this, new PreventModule());
    }

    @Override
    public void onDisable() {
        ModuleRegistry.getInstance().unregisterAll();
    }
}
