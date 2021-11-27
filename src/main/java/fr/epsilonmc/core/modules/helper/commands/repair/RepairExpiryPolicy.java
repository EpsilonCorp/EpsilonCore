package fr.epsilonmc.core.modules.helper.commands.repair;

import fr.epsilonmc.api.permission.PermissionOperations;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.cache2k.CacheEntry;
import org.cache2k.annotation.Nullable;
import org.cache2k.expiry.ExpiryPolicy;

@RequiredArgsConstructor
public class RepairExpiryPolicy implements ExpiryPolicy<Player, Long> {

    private final String pattern;

    @Override
    public long calculateExpiryTime(Player player, Long repairedAt, long loadTime, @Nullable CacheEntry<Player, Long> currentEntry) {
        int expiryTimeInMinute = PermissionOperations.getHighestValueOfPattern(player, pattern);

        return repairedAt + (long) expiryTimeInMinute * 60 * 1000;
    }
}
