package pl.ynfuien.superiorskyblockextraflags.listeners;

import com.bgsoftware.superiorskyblock.api.events.PluginInitializeEvent;
import com.bgsoftware.superiorskyblock.api.island.IslandFlag;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.ynfuien.superiorskyblockextraflags.SuperiorSkyblockExtraFlags;
import pl.ynfuien.superiorskyblockextraflags.listeners.islandflags.CreatureSpawnListener;
import pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges.*;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;


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
            "USE_SHULKER_BOXES",
            "USE_BEDS",
            "USE_CANDLES",
            "COLLECT_SWEET_BERRIES",
            "COLLECT_GLOW_BERRIES",
            "USE_ANVILS",
            "USE_BELLS",
            "USE_BEACONS",
            "USE_BARRELS",
            "USE_BREWING_STANDS",
            "USE_LEVERS",
            "USE_REPEATERS",
            "USE_COMPARATORS",
            "USE_DAYLIGHT_DETECTORS",
            "USE_NOTE_BLOCKS",
            "USE_JUKEBOXES",
            "USE_TRIPWIRES",
            "USE_COMPOSTERS",
            "USE_FLOWER_POTS",
            "USE_CAULDRONS",
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
            "SHOOT_BOW",
            "SHOOT_CROSSBOW",
            "THROW_EGGS",
            "THROW_SNOWBALLS",
            "THROW_POTIONS",
            "USE_LECTERNS",
            "EAT_CAKE",
            "PAT_DRAGON_EGG",
            "CARVE_PUMPKINS",
    };

    @EventHandler
    public void onPluginInit(PluginInitializeEvent e){
        Util.log("&bSuperiorInitializeEvent...");

        for (String islandFlag : islandFlags) {
            IslandFlag.register(islandFlag);
        }
        Util.log(MessageFormat.format("&3Registered &e{0} &3island flags!", islandFlags.length));

        for (String islandPrivilege : islandPrivileges) {
            IslandPrivilege.register(islandPrivilege);
        }
        Util.log(MessageFormat.format("&3Registered &e{0} &3island privileges!", islandPrivileges.length));


        // Registering listeners for island flags and privileges
        List<Listener> listeners = Arrays.asList(
                // Listener for island flags
                new CreatureSpawnListener(),
                // Listeners for island privileges
                new BlockBreakListener(),
                new PlayerInteractListener(),
                new PlayerInteractEntityListener(),
                new EntityDamageByEntityListener(),
                new PlayerArmorStandManipulateListener(),
                new HangingBreakByEntityListener(),
                new ProjectileHitListener(),
                new ProjectileLaunchListener(),
                new EntityShootBowListener(),
                new PlayerTakeLecternBookListener()
        );

        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, instance);
        }

        Util.log(MessageFormat.format("&3Registered &e{0} &3listeners!", listeners.size()));


        Util.log("&aSuccessfully &bregistered all necessary things!");
    }
}
