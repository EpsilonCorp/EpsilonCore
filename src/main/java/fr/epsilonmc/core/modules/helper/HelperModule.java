package fr.epsilonmc.core.modules.helper;

import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairCommand;
import fr.epsilonmc.core.modules.helper.commands.repair.RepairExpiryPolicy;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;

@EpsilonModule(name = "Helper", commands = {RepairCommand.class})
public class HelperModule {

    @Getter
    private final Cache<Player, Long> playerRepairCache = new Cache2kBuilder<Player, Long>() {}
            .expiryPolicy(new RepairExpiryPolicy())
            .keepDataAfterExpired(false)
            .build();

}
