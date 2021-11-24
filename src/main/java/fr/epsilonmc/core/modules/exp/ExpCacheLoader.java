package fr.epsilonmc.core.modules.exp;

import com.google.common.cache.CacheLoader;
import fr.epsilonmc.core.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.Set;
import java.util.UUID;

public class ExpCacheLoader extends CacheLoader<String, Integer> {

    @Override
    public Integer load(String uuid) throws Exception {
        Player player = Bukkit.getPlayer(UUID.fromString(uuid));
        Set<PermissionAttachmentInfo> effectivePermissions = player.getEffectivePermissions();


        int higherMultiplier = -1;
        for (PermissionAttachmentInfo effectivePermission : effectivePermissions) {
            if (effectivePermission.getPermission().startsWith(Permissions.EXP_MULTIPLIER_PATTERN)) {
                int multiplier = Integer.parseInt(effectivePermission.getPermission().substring(0, Permissions.EXP_MULTIPLIER_PATTERN.length()));
                higherMultiplier = Math.max(higherMultiplier, multiplier);
            }
        }

        return 100 + (higherMultiplier == -1 ? 0 : higherMultiplier);
    }

}
