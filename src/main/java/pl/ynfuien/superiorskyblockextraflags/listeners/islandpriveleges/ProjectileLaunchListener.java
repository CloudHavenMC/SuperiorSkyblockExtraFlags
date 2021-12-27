package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class ProjectileLaunchListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        Projectile projectile = e.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player p = (Player) shooter;

        String permission = getPermission(projectile.getType());
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(projectile.getLocation(), p, permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    private String getPermission(EntityType type) {

        if (type.equals(EntityType.EGG)) {
            return "THROW_EGGS";
        }

        if (type.equals(EntityType.SNOWBALL)) {
            return "THROW_SNOWBALLS";
        }

        if (type.equals(EntityType.SPLASH_POTION)) {
            return "THROW_POTIONS";
        }

        return null;
    }
}
