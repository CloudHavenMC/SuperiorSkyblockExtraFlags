package pl.ynfuien.superiorskyblockextraflags.listeners;

import com.bgsoftware.superiorskyblock.api.events.PluginInitializeEvent;
import com.bgsoftware.superiorskyblock.api.island.IslandFlag;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import pl.ynfuien.superiorskyblockextraflags.SuperiorSkyblockExtraFlags;
import pl.ynfuien.superiorskyblockextraflags.listeners.islandflags.CreatureSpawnListener;
import pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges.*;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;


public class SuperiorInitializeListener implements Listener {
    private final SuperiorSkyblockExtraFlags instance;
    public SuperiorInitializeListener(SuperiorSkyblockExtraFlags instance) {
        this.instance = instance;
    }

    private String[] islandFlags = new String[] {
            "NATURAL_MONSTER_SPAWN_NORMAL",
            "NATURAL_MONSTER_SPAWN_NETHER",
            "NATURAL_MONSTER_SPAWN_THE_END"
    };
    private String[] islandPrivileges = new String[] {
            "USE_BUTTONS",
            "USE_DOORS",
            "USE_TRAPDOORS",
            "USE_FENCE_GATES",
            "USE_FURNACES",
            "USE_CHESTS",
            "USE_BEDS",
            "COLLECT_SWEET_BERRIES",
            "COLLECT_GLOW_BERRIES",
            "USE_ANVILS",
            "USE_BELLS",
            "USE_BARRELS",
            "USE_COMPOSTERS",
            "USE_ENDER_CHESTS",
            "USE_ENCHANTING_TABLES",
            "USE_HOPPERS",
            "USE_DISPENSERS",
            "USE_DROPPERS",
            "USE_PRESSURE_PLATES",
            "USE_ARMOR_STANDS",
            "USE_ITEM_FRAMES",
            "ROTATE_ITEM_FRAME_ITEMS",
            "USE_DRIPLEAF",
    };

    @EventHandler
    public void onPluginInit(PluginInitializeEvent e){

        for (String islandFlag : islandFlags) {
            IslandFlag.register(islandFlag);

            Util.log("Registered island flag: " + IslandFlag.getByName(islandFlag).getName());
        }

        for (String islandPrivilege : islandPrivileges) {
            IslandPrivilege.register(islandPrivilege);

            Util.log("Registered island permission: " + IslandPrivilege.getByName(islandPrivilege).getName());
        }


        PluginManager pm = Bukkit.getServer().getPluginManager();

        // Register events for island flags
        pm.registerEvents(new CreatureSpawnListener(), instance);

        // Register events for island privileges
        pm.registerEvents(new BlockBreakListener(), instance);
        pm.registerEvents(new PlayerInteractListener(), instance);
        pm.registerEvents(new PlayerInteractEntityListener(), instance);
        pm.registerEvents(new EntityDamageByEntityListener(), instance);
        pm.registerEvents(new PlayerArmorStandManipulateListener(), instance);
        pm.registerEvents(new HangingBreakByEntityListener(), instance);
    }
}
