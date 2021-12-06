package fr.epsilonmc.core.modules.prevent.listeners;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import fr.epsilonmc.api.io.ChatOperations;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class BowPreventListener implements Listener {

    @EventHandler
    private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Arrow) || event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE)
            return;

        Arrow arrow = (Arrow) event.getDamager();
        RegionManager container = WorldGuardPlugin.inst().getRegionContainer().get(arrow.getWorld());
        if (container != null) {
            for (ProtectedRegion applicableRegion : container.getApplicableRegions(arrow.getLocation())) {
                if (applicableRegion.getId().toLowerCase().startsWith("event")) {
                    if (arrow.getShooter() instanceof Player) {
                        ChatOperations.sendPrefixedAndTranslated(
                                (CommandSender) arrow.getShooter(),
                                "&cLes arcs sont interdits dans les zones événements."
                        );
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

}
