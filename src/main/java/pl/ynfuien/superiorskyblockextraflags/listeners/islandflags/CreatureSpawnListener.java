package pl.ynfuien.superiorskyblockextraflags.listeners.islandflags;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandFlag;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {

    @EventHandler (ignoreCancelled = true)
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        LivingEntity entity = e.getEntity();

//        Util.log("Type: " + entity.getType() + ", monster: " + (entity instanceof Monster ? "true" : "false") + ", reason:" + e.getSpawnReason().name() + ", world: " + entity.getLocation().getWorld().getEnvironment().name());

        if (!(entity instanceof Monster)) return;

        if (!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) return;

        Location location = entity.getLocation();

        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) return;

        World.Environment environment = location.getWorld().getEnvironment();


        if (!environment.equals(World.Environment.NORMAL) && !environment.equals(World.Environment.CUSTOM)) {
            if (!island.hasSettingsEnabled(IslandFlag.getByName("NATURAL_MONSTER_SPAWN_"+environment.name()))) {
                e.setCancelled(true);
            }
            return;
        }

        if (!island.hasSettingsEnabled(IslandFlag.getByName("NATURAL_MONSTER_SPAWN_NORMAL"))) {
            e.setCancelled(true);
            return;
        }
    }
}
