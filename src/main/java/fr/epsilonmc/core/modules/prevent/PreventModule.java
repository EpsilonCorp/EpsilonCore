package fr.epsilonmc.core.modules.prevent;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.modules.prevent.listeners.*;
import lombok.Getter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.TimeUnit;

@EpsilonModule(
        name = "prevent",
        listeners = { // TODO: tests
                ExploitFixerListener.class,
                ReserveFixListener.class,
                WitherSpawnPreventListener.class,
                NotAllowedEnchantmentsListener.class,
                BowPreventListener.class
        })
public class PreventModule {

        @Getter private final Cache<Player, List<ItemStack>> noCleanUpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(20L, TimeUnit.SECONDS).build();

        @Getter private final Cache<Player, Short> alertedPlayerCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5L, TimeUnit.SECONDS).build();

        @Getter private final Enchantment[] notAllowedEnchantments = {
                Enchantment.KNOCKBACK,
                Enchantment.THORNS
        };

}
