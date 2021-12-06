package fr.epsilonmc.core.modules.prevent.listeners;

import fr.epsilonmc.api.io.ChatOperations;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class ReserveFixListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void inventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("Reserve")) {
            if (event.getAction().name().startsWith("PLACE")) {
                ChatOperations.sendPrefixedAndTranslated(player, "&cVous ne pouvez pas placer d'item depuis le serveur faction dans votre réserve.");
                event.setCancelled(true);
            }
        } else if (player.getOpenInventory() != null
                && player.getOpenInventory().getTitle().equals("Reserve")
                && event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            ChatOperations.sendPrefixedAndTranslated(player, "&cVous ne pouvez pas placer d'item depuis le serveur faction dans votre réserve.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void inventoryDrag(InventoryDragEvent event) {
        if (event.getInventory().getTitle().equals("Reserve")) {
            ChatOperations.sendPrefixedAndTranslated(
                    event.getWhoClicked(),
                    "&cVous ne pouvez pas placer d'item depuis le serveur faction dans votre réserve."
            );
            event.setCancelled(true);
        }
    }

}
