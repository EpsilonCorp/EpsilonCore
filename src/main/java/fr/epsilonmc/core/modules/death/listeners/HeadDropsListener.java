package fr.epsilonmc.core.modules.death.listeners;

import fr.epsilonmc.api.external.ItemBuilder;
import fr.epsilonmc.core.Permissions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HeadDropsListener implements Listener {

    private static final ItemBuilder skullBuilder = (new ItemBuilder(Material.SKULL_ITEM)).setDurability((short) 3);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        if (killer != null && killer.hasPermission(Permissions.DEATH_HEAD_DROPS)) {
            event.getDrops().add(skullBuilder.setSkullOwner(player.getName()).toItemStack());
        }
    }

}
