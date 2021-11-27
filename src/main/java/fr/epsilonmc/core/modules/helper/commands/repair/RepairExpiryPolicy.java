package fr.epsilonmc.core.modules.helper.commands.repair;

import fr.epsilonmc.api.permission.PermissionOperations;
import fr.epsilonmc.core.Permissions;
import org.bukkit.entity.Player;
import org.cache2k.CacheEntry;
import org.cache2k.annotation.Nullable;
import org.cache2k.expiry.ExpiryPolicy;

public class RepairExpiryPolicy implements ExpiryPolicy<Player, Long> {

    @Override
    public long calculateExpiryTime(Player player, Long repairedAt, long loadTime, @Nullable CacheEntry<Player, Long> currentEntry) {
        int expiryTimeInMinute = PermissionOperations.getHighestValueOfPattern(player, Permissions.HELPER_COMMAND_REPAIR_PATTERN);

        return repairedAt + (long) expiryTimeInMinute * 60 * 1000;
    }
}
