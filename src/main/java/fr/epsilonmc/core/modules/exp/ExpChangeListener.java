package fr.epsilonmc.core.modules.exp;

import fr.epsilonmc.api.module.ModuleRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExpChangeListener implements Listener {

    private ExpModule expModule;

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        if (expModule == null) {
            expModule = ModuleRegistry.getInstance().getModule(ExpModule.class).getModule();
        }

        event.setAmount((int) (event.getAmount() * (expModule.getPlayerCache().getUnchecked(event.getPlayer()) / 100D)));
    }

}
