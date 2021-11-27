package fr.epsilonmc.mock.bukkit;

import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EpsilonPlayerMock extends PlayerMock {

    public EpsilonPlayerMock(String name) {
        super(name);
    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        try {
            Field permissionAttachmentsField = getClass().getSuperclass().getSuperclass().getDeclaredField("permissionAttachments");
            permissionAttachmentsField.setAccessible(true);
            return ((Set<PermissionAttachment>) permissionAttachmentsField.get(this))
                    .stream()
                    .map(permissionAttachment -> {
                        List<String> permission = new ArrayList<>(permissionAttachment.getPermissions().keySet());

                        return new PermissionAttachmentInfo(
                                permissionAttachment.getPermissible(),
                                permission.get(0),
                                permissionAttachment,
                                permissionAttachment.getPermissions().get(permission.get(0))
                        );
                    }).collect(Collectors.toSet());
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return Collections.emptySet();
    }
}
