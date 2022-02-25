package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class ProjectileHitListener implements Listener {
    // Island privileges that this event handles:
    // - USE_BELLS

    @EventHandler(ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent e) {
        // Get git block
        Block b = e.getHitBlock();

        // Return if hit block is null
        if (b == null) return;
        // Return if hit block isn't bell
        if (!b.getType().equals(Material.BELL)) return;

        // Get shooter
        ProjectileSource shooter = e.getEntity().getShooter();
        // Return if shooter isn't player
        if (!(shooter instanceof Player)) return;

        // Get player
        Player p = (Player) shooter;

        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(b.getLocation(), p, "USE_BELLS");

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }
}
