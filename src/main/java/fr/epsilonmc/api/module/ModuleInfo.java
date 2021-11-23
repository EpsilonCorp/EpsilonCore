package fr.epsilonmc.api.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
@Getter
public class ModuleInfo {

    private final JavaPlugin plugin;
    private final AbstractModule module;

    public void handleRegistration() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        for (Listener listener : module.getListeners()) {
            pluginManager.registerEvents(listener, plugin);
        }

        // TODO: commands
    }
}
