package fr.epsilonmc.api.permission;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class PermissionOperations {

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
