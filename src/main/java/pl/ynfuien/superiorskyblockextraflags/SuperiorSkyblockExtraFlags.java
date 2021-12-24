package pl.ynfuien.superiorskyblockextraflags;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.ynfuien.superiorskyblockextraflags.listeners.SuperiorInitializeListener;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public final class SuperiorSkyblockExtraFlags extends JavaPlugin {

    @Override
    public void onEnable() {
        Util.setInstance(this);

        PluginManager pm = getServer().getPluginManager();

        if (pm.getPlugin("SuperiorSkyblock2") == null) {
            Util.log(Util.formatColors("&cThere is no SuperiorSkyblock2 on your server! Plugin can't work without it."));
            pm.disablePlugin(this);
            return;
        }

        saveDefaultConfig();
        Util.setIslandProtectedMessage(getConfig().getString("island-protected-message"));
        Util.setHexPrefix(getConfig().getString("hex-prefix"));

        // Register island flags and privileges
        pm.registerEvents(new SuperiorInitializeListener(this), this);

        Util.log("Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        Util.log("Plugin successfully disabled!");
    }
}
