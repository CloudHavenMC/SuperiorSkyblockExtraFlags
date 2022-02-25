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
import pl.ynfuien.superiorskyblockextraflags.utils.Logger;

import java.text.MessageFormat;


public class SuperiorInitializeListener implements Listener {
    private final SuperiorSkyblockExtraFlags instance;
    public SuperiorInitializeListener(SuperiorSkyblockExtraFlags instance) {
        this.instance = instance;
    }

    // Island flags array
    private String[] islandFlags = new String[] {
            "NATURAL_MONSTER_SPAWN_NORMAL",
            "NATURAL_MONSTER_SPAWN_NETHER",
            "NATURAL_MONSTER_SPAWN_THE_END"
    };
    // Island privileges array
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
            "EAT_CHORUS_FRUIT",
            "PAT_DRAGON_EGG",
            "CARVE_PUMPKINS",
            "USE_ELYTRA"
    };

    @EventHandler
    public void onPluginInit(PluginInitializeEvent e){
        Logger.log("&bSuperiorInitializeEvent...");

        // Register all island flags
        for (String islandFlag : islandFlags) {
            IslandFlag.register(islandFlag);
        }
        Logger.log(MessageFormat.format("&3Registered &e{0} &3island flags!", islandFlags.length));

        // Register all island privileges
        for (String islandPrivilege : islandPrivileges) {
            IslandPrivilege.register(islandPrivilege);
        }
        Logger.log(MessageFormat.format("&3Registered &e{0} &3island privileges!", islandPrivileges.length));


        // Listeners for island flags and privileges
        Listener[] listeners = new Listener[] {
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
            new PlayerTakeLecternBookListener(),
            new PlayerItemConsumeListener(),
            new EntityToggleGlideListener()
        };

        // Register listeners
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, instance);
        }

        Logger.log(MessageFormat.format("&3Registered &e{0} &3listeners!", listeners.length));


        Logger.log("&aSuccessfully &bregistered all necessary things!");
    }
}
