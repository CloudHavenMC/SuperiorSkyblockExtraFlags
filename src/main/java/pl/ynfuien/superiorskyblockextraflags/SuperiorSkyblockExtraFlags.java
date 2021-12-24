package pl.ynfuien.superiorskyblockextraflags;

import org.bukkit.plugin.java.JavaPlugin;
import pl.ynfuien.superiorskyblockextraflags.listeners.SuperiorInitializeListener;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public final class SuperiorSkyblockExtraFlags extends JavaPlugin {

    @Override
    public void onEnable() {
        Util.setInstance(this);

        saveDefaultConfig();
        Util.setIslandProtectedMessage(getConfig().getString("island-protected-message"));
        Util.setHexPrefix(getConfig().getString("hex-prefix"));

        // Register island flags and privileges
        getServer().getPluginManager().registerEvents(new SuperiorInitializeListener(this), this);

        Util.log("Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        Util.log("Plugin successfully disabled!");
    }
}
