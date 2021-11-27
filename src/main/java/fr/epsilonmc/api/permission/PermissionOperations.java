package fr.epsilonmc.api.permission;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.Set;

public class PermissionOperations {

    public static int getHighestValueOfPattern(Player player, String pattern) {
        Set<PermissionAttachmentInfo> effectivePermissions = player.getEffectivePermissions();

        int highest = -1;
        for (PermissionAttachmentInfo effectivePermission : effectivePermissions) {
            if (effectivePermission.getPermission().startsWith(pattern)) {
                int multiplier = Integer.parseInt(effectivePermission.getPermission().substring(
                        pattern.length()
                ));
                highest = Math.max(highest, multiplier);
            }
        }

        return highest;
    }

    public static boolean validate(Player player, String startWiths) {
        boolean isValid = player.isOp();

        for (PermissionAttachmentInfo effectivePermission : player.getEffectivePermissions()) {
            if (effectivePermission.getPermission().startsWith(startWiths)) {
                isValid = true;
            }
        }

        return isValid;
    }

}
