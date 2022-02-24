package pl.ynfuien.superiorskyblockextraflags.listeners.islandflags;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandFlag;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {
    // Island flags that this event handles:
    // - NATURAL_MONSTER_SPAWN_NORMAL
    // - NATURAL_MONSTER_SPAWN_NETHER
    // - NATURAL_MONSTER_SPAWN_THE_END

    @EventHandler (ignoreCancelled = true)
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        // Get entity
        LivingEntity entity = e.getEntity();

        // Return if entity isn't monster
        if (!isMonster(entity)) return;

        // Return if spawn reason isn't natural spawn
        if (!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) return;

        // Get entity location
        Location location = entity.getLocation();
        // Get island at location
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        // Return if no island is at entity location
        if (island == null) return;

        // Get world environment
        World.Environment environment = location.getWorld().getEnvironment();

        // Set flag
        String flag = "NATURAL_MONSTER_SPAWN_"+environment.name();
        // Change flag if world's environment is CUSTOM
        if (environment.equals(World.Environment.CUSTOM)) flag = "NATURAL_MONSTER_SPAWN_NORMAL";

        // Cancel event if island has flag disabled
        if (!island.hasSettingsEnabled(IslandFlag.getByName(flag))) {
            e.setCancelled(true);
        }
    }

    // Gets whether entity is a monster or not
    public static boolean isMonster(Entity e) {
        if (e instanceof Monster) return true;
        if (e instanceof Slime) return true;
        if (e instanceof Flying) return true;

        return false;
    }
}
