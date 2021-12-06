package fr.epsilonmc.core.modules.prevent.listeners;

import fr.epsilonmc.api.io.ChatOperations;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.modules.prevent.PreventModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AntiCleanUpListener implements Listener {

    private PreventModule preventModule;

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer != null) {
            getModule().getNoCleanUpCache().put(killer, event.getDrops());
        }
    }

    @EventHandler
    private void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem().getItemStack();
        if (isItemInCleanUpCache(itemStack)) {
            event.setCancelled(true);
            if (!getModule().getAlertedPlayerCache().asMap().containsKey(player)) {
                getModule().getAlertedPlayerCache().put(player, (short) 0);
                ChatOperations.sendPrefixedAndTranslated(player, "&cCe stuff est protégé par l'anti clean-up !");
            }
        }
    }

    private boolean isItemInCleanUpCache(ItemStack itemStack) {
        for (List<ItemStack> value : getModule().getNoCleanUpCache().asMap().values()) {
            if (value.contains(itemStack)) {
                return true;
            }
        }
        return false;
    }

    private PreventModule getModule() {
        if (preventModule == null) {
            preventModule = ModuleRegistry.getInstance().getModule(PreventModule.class).getModule();
        }

        return preventModule;
    }

}
