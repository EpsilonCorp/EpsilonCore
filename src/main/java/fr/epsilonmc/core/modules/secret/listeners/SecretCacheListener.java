package fr.epsilonmc.core.modules.secret.listeners;

import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.api.permission.PermissionOperations;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.core.modules.secret.SecretModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SecretCacheListener implements Listener {

    private SecretModule secretModule;

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        if (PermissionOperations.validate(player, Permissions.OP_PATTERN)
                && event.getResult() == PlayerLoginEvent.Result.ALLOWED
                && getModule().getSecretConfiguration().getWhoNeedsSecretLogin().contains(uuid)
                && !getModule().getPlayerCache().contains(uuid)){

            getModule().getPlayerCache().add(uuid);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        getModule().getPlayerCache().remove(event.getPlayer().getUniqueId().toString());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();

        if (getModule().getPlayerCache().contains(uuid)
                && event.getMessage().equals("/" + getModule().getSecretConfiguration().getSecretKey())) {
            getModule().getPlayerCache().remove(uuid);
        }
    }

    private SecretModule getModule() {
        if (secretModule == null) {
            secretModule = ModuleRegistry.getInstance().getModule(SecretModule.class).getModule();
        }

        return secretModule;
    }

}
