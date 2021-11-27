package fr.epsilonmc.api.io;

import fr.epsilonmc.core.Core;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ProxyOperations {

    @SneakyThrows
    public static void switchPlayerServer(Player player, String server, boolean bypass) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        dataOutputStream.writeUTF(player.getName());
        dataOutputStream.writeUTF(server);
        dataOutputStream.writeBoolean(bypass);

        player.sendPluginMessage(Core.getInstance(), "queue_channel", byteArrayOutputStream.toByteArray());
    }

}
