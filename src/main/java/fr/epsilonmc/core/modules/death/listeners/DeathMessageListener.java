package fr.epsilonmc.core.modules.death.listeners;

import fr.epsilonmc.api.io.ChatOperations;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathMessageListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        if (killer == null) {
            event.setDeathMessage(ChatOperations.translateColorCode(
                    "&7[&c\u2717&7] &6%s &eest mort tout seul...",
                    player.getName()
            ));
            return;
        }

        ItemStack weapon = killer.getItemInHand();
        if (weapon != null && weapon.hasItemMeta() && weapon.getItemMeta().hasDisplayName()) {
            event.setDeathMessage(ChatOperations.translateColorCode(
                    "&7[&c\u2717&7] &6%s &ea été tué par &6%s &eavec %s.",
                    player.getName(),
                    killer.getName(),
                    weapon.getItemMeta().getDisplayName()
            ));
        } else {
            event.setDeathMessage(ChatOperations.translateColorCode(
                    "&7[&c\u2717&7] &6%s &ea été tué par &6%s.",
                    player.getName(),
                    killer.getName()
            ));
        }
    }

}
