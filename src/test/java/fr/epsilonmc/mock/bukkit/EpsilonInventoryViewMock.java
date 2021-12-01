package fr.epsilonmc.mock.bukkit;

import be.seeseemelk.mockbukkit.inventory.InventoryViewMock;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class EpsilonInventoryViewMock extends InventoryViewMock {
    public EpsilonInventoryViewMock(HumanEntity player, Inventory top, Inventory bottom, InventoryType type) {
        super(player, top, bottom, type);
    }
}
