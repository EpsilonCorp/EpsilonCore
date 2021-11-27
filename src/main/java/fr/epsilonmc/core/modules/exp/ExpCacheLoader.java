package fr.epsilonmc.core.modules.exp;

import com.google.common.cache.CacheLoader;
import fr.epsilonmc.api.permission.PermissionOperations;
import fr.epsilonmc.core.Permissions;
import org.bukkit.entity.Player;

public class ExpCacheLoader extends CacheLoader<Player, Integer> {

    @Override
    public Integer load(Player player) {
        int higherMultiplier = PermissionOperations.getHighestValueOfPattern(player, Permissions.EXP_MULTIPLIER_PATTERN);

        return 100 + (higherMultiplier == -1 ? 0 : higherMultiplier);
    }

}
