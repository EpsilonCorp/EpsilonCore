package fr.epsilonmc.api.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.epsilonmc.api.io.FileOperations;
import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.api.module.ModuleFinder;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;

public class ConfigurationLoader {

    public static <T> T loadOrGenerate(JavaPlugin plugin, Object module, Class<? extends T> configurationClass) {
        EpsilonModule epsilonModule = ModuleFinder.findModuleOnClass(module);
        File dataFolder = plugin.getDataFolder();
        File moduleFileConfiguration = new File(dataFolder, epsilonModule.name() + ".json");

        generateFiles(dataFolder, moduleFileConfiguration);

        BufferedReader configurationBufferedReader = FileOperations.getReader(moduleFileConfiguration);
        return new Gson().fromJson(configurationBufferedReader, configurationClass);
    }

    public static <T> void save(JavaPlugin plugin, Object module, T configuration) {
        EpsilonModule epsilonModule = ModuleFinder.findModuleOnClass(module);
        File dataFolder = plugin.getDataFolder();
        File moduleFileConfiguration = new File(dataFolder, epsilonModule.name() + ".json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileOperations.write(moduleFileConfiguration, gson.toJson(configuration));
    }

    @SneakyThrows
    private static void generateFiles(File dataFolder, File moduleFileConfiguration) {
        if ((!dataFolder.exists() || (dataFolder.isFile() && dataFolder.delete())) && dataFolder.mkdir()) {
            // TODO: put logger instead of sout
            System.out.printf("%s created!\n", dataFolder.getPath());
        }

        if (!moduleFileConfiguration.exists() && moduleFileConfiguration.createNewFile()) {
            // TODO: put logger instead of sout
            System.out.printf("%s created!\n", moduleFileConfiguration.getPath());
        }
    }

}

