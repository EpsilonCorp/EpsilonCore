package fr.epsilonmc.core.modules.helper;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import fr.epsilonmc.api.configuration.ConfigurationLoader;
import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.core.modules.helper.commands.*;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairAllCommand;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairCacheLoader;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairInHandCommand;
import fr.epsilonmc.core.modules.helper.config.HelperConfiguration;
import lombok.Getter;
import org.bukkit.entity.Player;

@EpsilonModule(
        name = "Helper",
        commands = {
                RepairInHandCommand.class,
                RepairAllCommand.class,
                BinCommand.class,
                InvestCommand.class,
                FurnaceCommand.class,
                EnchantmentCommand.class,
                AnvilCommand.class
        })
public class HelperModule {

    @Getter
    private final HelperConfiguration helperConfiguration = ConfigurationLoader.loadOrGenerate(
            Core.getInstance(),
            this,
            HelperConfiguration.class
    );

    @Getter
    private final LoadingCache<Player, Long> playerRepairCache = CacheBuilder.newBuilder()
            .build(new RepairCacheLoader(Permissions.HELPER_COMMAND_REPAIR_PATTERN));

    @Getter
    private final LoadingCache<Player, Long> playerRepairAllCache = CacheBuilder.newBuilder()
            .build(new RepairCacheLoader(Permissions.HELPER_COMMAND_REPAIR_ALL_PATTERN));

}
