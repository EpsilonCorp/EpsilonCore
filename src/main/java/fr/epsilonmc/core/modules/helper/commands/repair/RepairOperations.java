package fr.epsilonmc.core.modules.helper.commands.repair;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RepairOperations {

    public static boolean repair(ItemStack itemStack) {
        Material material;
        if (itemStack == null
                || (material = itemStack.getType()) == Material.AIR
                || material.isBlock()
                || material.getMaxDurability() < 1) {
            return false;
        }

        itemStack.setDurability((short) 0);
        return true;
    }

}
