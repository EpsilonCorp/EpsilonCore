package fr.epsilonmc.core.modules.helper.commands;

import fr.epsilonmc.api.command.*;
import fr.epsilonmc.api.module.ModuleRegistry;
import fr.epsilonmc.core.modules.helper.HelperModule;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class EnchantmentCommand implements IEpsilonCommand {

    private HelperModule helperModule;
    private Location location;

    @Override
    @EpsilonCommand(name = "enchantment", playerOnly = true)
    public boolean execute(Sender sender, Arguments arguments, CommandInfo info) {
        if (helperModule == null) {
            helperModule = ModuleRegistry.getInstance().getModule(HelperModule.class).getModule();

            location = new Location(
                    Bukkit.getWorld(helperModule.getHelperConfiguration().getEnchantment().getWorld()),
                    helperModule.getHelperConfiguration().getEnchantment().getX(),
                    helperModule.getHelperConfiguration().getEnchantment().getY(),
                    helperModule.getHelperConfiguration().getEnchantment().getZ()
            );
        }

        if (location.getChunk().load()) {
            sender.getPlayer().openEnchanting(location, true);
        }

        return true;
    }
}
