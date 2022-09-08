package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class ProjectileLaunchListener implements Listener {
    // Island privileges that this event handles:
    // - THROW_EGGS
    // - THROW_SNOWBALLS
    // - THROW_POTIONS
    // - THROW_EXPERIENCE_BOTTLES

    @EventHandler(ignoreCancelled = true)
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        // Get projectile
        Projectile projectile = e.getEntity();
        // Get shooter
        ProjectileSource shooter = projectile.getShooter();
        // Return if shooter isn't player
        if (!(shooter instanceof Player)) return;

        // Get player
        Player p = (Player) shooter;

        // Get island privilege
        String permission = getPermission(projectile.getType());
        // Return if no privilege was returned
        if (permission == null) return;
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(projectile.getLocation(), p, permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    private String getPermission(EntityType type) {
        // Return privilege for eggs
        if (type.equals(EntityType.EGG)) {
            return "THROW_EGGS";
        }

        // Return privilege for snowballs
        if (type.equals(EntityType.SNOWBALL)) {
            return "THROW_SNOWBALLS";
        }

        // Return privilege for potions
        if (type.equals(EntityType.SPLASH_POTION)) {
            return "THROW_POTIONS";
        }

        // Return privilege for experience bottle
        if (type.equals(EntityType.THROWN_EXP_BOTTLE)) {
            return "THROW_EXPERIENCE_BOTTLES";
        }

        return null;
    }
}
