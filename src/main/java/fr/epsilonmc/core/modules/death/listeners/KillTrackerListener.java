package fr.epsilonmc.core.modules.death.listeners;

import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.api.type.TypeParser;
import fr.epsilonmc.core.Variables;
import fr.epsilonmc.core.modules.death.DeathModule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class KillTrackerListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        ItemStack weapon;

        if (killer == null || (weapon = killer.getItemInHand()) == null || weapon.getType() == Material.AIR) {
            return;
        }

        int killsCount = getKillsCount(weapon) + 1;
        setKillsCount(weapon, killsCount);
    }

    private int getKillsCount(ItemStack itemStack) {
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
            List<String> lore = itemStack.getItemMeta().getLore();

            for (String line : lore) {
                if (line.startsWith(Variables.DEATH_KILLS_COUNT)) {
                    return TypeParser.intTryParse(line.substring(Variables.DEATH_KILLS_COUNT.length()), 0);
                }
            }
        }

        return 0;
    }

    private void setKillsCount(ItemStack itemStack, int killsCount) {
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
            List<String> lore = itemStack.getItemMeta().getLore();

            for (String line : lore) {
                if (line.startsWith(Variables.DEATH_KILLS_COUNT)) {
                    lore.set(lore.indexOf(line), Variables.DEATH_KILLS_COUNT + killsCount);
                }
            }

            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
    }

}
