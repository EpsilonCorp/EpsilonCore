package fr.epsilonmc.core.modules.exp.listener;

import fr.epsilonmc.api.module.ModuleFactory;
import fr.epsilonmc.core.modules.exp.ExpModule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerExpChangeListener implements Listener {

    private ExpModule expModule;

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {

        if (expModule == null) {
            expModule = ModuleFactory.getInstance().getModule(ExpModule.class).getModule();
        }

        String uuid = event.getPlayer().getUniqueId().toString();
        event.setAmount((int) (event.getAmount() * (expModule.getPlayerCache().getUnchecked(uuid) / 100D)));
    }

}
