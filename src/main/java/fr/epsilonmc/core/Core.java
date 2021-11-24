package fr.epsilonmc.core;

import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.core.modules.exp.ExpModule;
import fr.epsilonmc.core.modules.secret.SecretModule;
import lombok.Getter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class Core extends JavaPlugin {

    @Getter private static Core instance;

    // tests purposes only
    public Core(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        instance = this;

        ModuleFactory moduleFactory = ModuleFactory.getInstance();
        moduleFactory.registerModule(this, new ExpModule());
        moduleFactory.registerModule(this, new SecretModule());
    }

}
