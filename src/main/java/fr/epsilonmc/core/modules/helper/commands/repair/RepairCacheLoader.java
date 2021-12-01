package fr.epsilonmc.core.modules.helper.commands.repair;

import com.google.common.cache.CacheLoader;
import fr.epsilonmc.api.permission.PermissionOperations;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class RepairCacheLoader extends CacheLoader<Player, Long> {

    private final String pattern;

    @Override
    public Long load(Player player) throws Exception {
        int expiryTimeInMinute = PermissionOperations.getHighestValueOfPattern(player, pattern);

        return System.currentTimeMillis() + (long) expiryTimeInMinute * 60 * 1000;
    }
}
