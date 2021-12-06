package fr.epsilonmc.core.modules.prevent;

import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.modules.prevent.listeners.ExploitFixerListener;
import fr.epsilonmc.core.modules.prevent.listeners.NotAllowedEnchantmentsListener;
import fr.epsilonmc.core.modules.prevent.listeners.ReserveFixListener;
import fr.epsilonmc.core.modules.prevent.listeners.WitherSpawnPreventListener;
import lombok.Getter;
import org.bukkit.enchantments.Enchantment;

@EpsilonModule(
        name = "prevent",
        listeners = { // TODO: tests
                ExploitFixerListener.class,
                ReserveFixListener.class,
                WitherSpawnPreventListener.class,
                NotAllowedEnchantmentsListener.class
        })
public class PreventModule {

        @Getter private final Enchantment[] notAllowedEnchantments = {
                Enchantment.KNOCKBACK,
                Enchantment.THORNS
        };

}
