package fr.epsilonmc.core.modules.helper;

import fr.epsilonmc.api.configuration.ConfigurationLoader;
import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.core.modules.helper.commands.*;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairAllCommand;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairInHandCommand;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairExpiryPolicy;
import fr.epsilonmc.core.modules.helper.config.HelperConfiguration;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;

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
    private final Cache<Player, Long> playerRepairCache = new Cache2kBuilder<Player, Long>() {}
            .expiryPolicy(new RepairExpiryPolicy(Permissions.HELPER_COMMAND_REPAIR_PATTERN))
            .keepDataAfterExpired(false)
            .build();

    @Getter
    private final Cache<Player, Long> playerRepairAllCache = new Cache2kBuilder<Player, Long>() {}
            .expiryPolicy(new RepairExpiryPolicy(Permissions.HELPER_COMMAND_REPAIR_ALL_PATTERN))
            .keepDataAfterExpired(false)
            .build();

}
