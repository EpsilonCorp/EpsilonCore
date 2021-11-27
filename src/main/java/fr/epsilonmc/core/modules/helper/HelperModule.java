package fr.epsilonmc.core.modules.helper;

import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairAllCommand;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairInHandCommand;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairExpiryPolicy;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;

@EpsilonModule(name = "Helper", commands = {RepairInHandCommand.class, RepairAllCommand.class})
public class HelperModule {

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
