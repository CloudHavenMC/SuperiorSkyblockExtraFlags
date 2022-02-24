package pl.ynfuien.superiorskyblockextraflags;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.ynfuien.superiorskyblockextraflags.listeners.SuperiorInitializeListener;
import pl.ynfuien.superiorskyblockextraflags.utils.Logger;
import pl.ynfuien.superiorskyblockextraflags.utils.Messages;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public final class SuperiorSkyblockExtraFlags extends JavaPlugin {
    private static SuperiorSkyblockExtraFlags instance;

    @Override
    public void onEnable() {
        // Set plugin instance
        instance = this;

        // Set logger prefix
        Logger.setPrefix("&3[&9SuperiorSkyblockExtraFlags&3] &f");

//        Util.setInstance(this);

        // Get plugin manager
        PluginManager pm = getServer().getPluginManager();

        // Check if SuperiorSkyblock2 isn't on server
        if (Util.isSS2Enabled()) {
            Logger.logError("There is no SuperiorSkyblock2 on your server! Plugin can't work without it.");
            pm.disablePlugin(this);
            return;
        }

        // Save config
        saveDefaultConfig();
        // Set message hex colors prefix
        Messages.setHexPrefix(getConfig().getString("hex-prefix"));
        // Set island protected message
        Messages.setIslandProtectedMessage(getConfig().getString("island-protected-message"));

        // Register island flags and privileges
        pm.registerEvents(new SuperiorInitializeListener(this), this);

        Logger.log("Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        Logger.log("Plugin successfully disabled!");
    }

    // Gets plugin instance
    public static SuperiorSkyblockExtraFlags getInstance() {
        return instance;
    }
}
