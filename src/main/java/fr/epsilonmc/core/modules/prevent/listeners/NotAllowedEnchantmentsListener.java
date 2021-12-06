package fr.epsilonmc.core.modules.prevent.listeners;

import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.modules.prevent.PreventModule;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NotAllowedEnchantmentsListener implements Listener {

    private PreventModule preventModule;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }

        for (Enchantment notAllowedEnchantment : getModule().getNotAllowedEnchantments()) {
            itemStack.removeEnchantment(notAllowedEnchantment);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack current = event.getCurrentItem();
        if (current != null && current.getType() != Material.AIR) {
            for (Enchantment notAllowedEnchantment : getModule().getNotAllowedEnchantments()) {
                current.removeEnchantment(notAllowedEnchantment);
            }
        }
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        for (Enchantment notAllowedEnchantment : getModule().getNotAllowedEnchantments()) {
            event.getEnchantsToAdd().remove(notAllowedEnchantment);
        }
    }

    public PreventModule getModule() {
        if (preventModule == null) {
            preventModule = ModuleRegistry.getInstance().getModule(PreventModule.class).getModule();
        }

        return preventModule;
    }

}
