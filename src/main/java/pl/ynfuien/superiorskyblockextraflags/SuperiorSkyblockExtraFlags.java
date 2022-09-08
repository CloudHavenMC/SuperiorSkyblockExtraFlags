package pl.ynfuien.superiorskyblockextraflags;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblock;
import com.bgsoftware.superiorskyblock.api.commands.SuperiorCommand;
import com.bgsoftware.superiorskyblock.api.modules.PluginModule;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import pl.ynfuien.superiorskyblockextraflags.listeners.Listeners;
import pl.ynfuien.superiorskyblockextraflags.utils.Logger;
import pl.ynfuien.superiorskyblockextraflags.utils.Messages;

import java.io.File;
import java.io.IOException;

public final class SuperiorSkyblockExtraFlags extends PluginModule {
    private static SuperiorSkyblockExtraFlags instance;
    private static SuperiorSkyblock plugin;


    public SuperiorSkyblockExtraFlags() {
        super("ExtraFlags", "Ynfuien");
        instance = this;
    }

    @Override
    public void onEnable(SuperiorSkyblock plugin) {
        // Plugin startup logic
        SuperiorSkyblockExtraFlags.plugin = plugin;

        // Set logger prefix
        Logger.setPrefix("&b[SuperiorSkyblock-ExtraFlags] ");

        // Save config
        File file = new File(instance.getModuleFolder(), "config.yml");
        if (!file.exists()) instance.saveResource("config.yml");
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        // Set message hex colors prefix
        Messages.setHexPrefix(config.getString("hex-prefix"));
        // Set island protected message
        Messages.setIslandProtectedMessage(config.getString("island-protected-message"));

        // Register island flags and privileges
        Listeners.registerFlags();
    }

    @Override
    public void onDisable(SuperiorSkyblock plugin) {
    }

    @Override
    public void onReload(SuperiorSkyblock plugin) {
    }

    @Override
    public void loadData(SuperiorSkyblock plugin) {
    }

    @Override
    public Listener[] getModuleListeners(SuperiorSkyblock superiorSkyblock) {
        return Listeners.getListeners();
    }

    @Override
    public SuperiorCommand[] getSuperiorCommands(SuperiorSkyblock superiorSkyblock) {
        return null;
    }

    @Override
    public SuperiorCommand[] getSuperiorAdminCommands(SuperiorSkyblock superiorSkyblock) {
        return null;
    }

    public static SuperiorSkyblockExtraFlags getInstance() {
        return instance;
    }
    public static SuperiorSkyblock getPlugin() {
        return plugin;
    }
}
