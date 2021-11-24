package fr.epsilonmc.core.modules.secret.listeners;

import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.core.modules.secret.SecretModule;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SecretSecurityListener implements Listener {

    private SecretModule secretModule;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        validatePlayerCache(event, event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onMove(PlayerMoveEvent event) {
        validatePlayerCache(event, event.getPlayer());
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        validatePlayerCache(event, event.getPlayer());
    }

    private void validatePlayerCache(Cancellable event, Player player) {
        String uuid = player.getUniqueId().toString();

        if (secretModule.getPlayerCache().contains(uuid))
            event.setCancelled(true);
    }

    private SecretModule getModule() {
        if (secretModule == null) {
            secretModule = ModuleFactory.getInstance().getModule(SecretModule.class).getModule();
        }

        return secretModule;
    }

}
