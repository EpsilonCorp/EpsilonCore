package fr.epsilonmc.core.modules.helper.commands;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.io.ChatOperations;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class FurnaceCommand implements IEpsilonCommand {

    private final Map<Material, Material> typeTransformMap = new HashMap<Material, Material>(){{
        put(Material.REDSTONE_ORE, Material.REDSTONE);
        put(Material.GOLD_ORE, Material.GOLD_INGOT);
        put(Material.IRON_ORE, Material.IRON_INGOT);
        put(Material.COAL_ORE, Material.COAL);
        put(Material.DIAMOND_ORE, Material.DIAMOND);
        put(Material.EMERALD_ORE, Material.EMERALD);
    }};

    @Override
    @EpsilonCommand(name = "furnace", playerOnly = true)
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        PlayerInventory inventory = sender.getPlayer().getInventory();

        for (ItemStack itemStack : inventory) {
            if (itemStack != null && typeTransformMap.containsKey(itemStack.getType())) {
                itemStack.setType(typeTransformMap.get(itemStack.getType()));
            }
        }
        ChatOperations.sendPrefixedAndTranslated(sender.getPlayer(), "&bVous avez cuit vos minerais !");
        return true;
    }
}
