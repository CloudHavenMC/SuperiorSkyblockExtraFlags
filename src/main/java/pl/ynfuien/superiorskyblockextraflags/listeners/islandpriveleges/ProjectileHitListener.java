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
    @EventHandler(ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent e) {
        Block b = e.getHitBlock();

        Projectile projectile = e.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player p = (Player) shooter;

        String permission = getPermission(b, projectile);
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(b.getLocation(), p, permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    private String getPermission(Block b, Projectile p) {
        if (b != null) {
            if (b.getType().equals(Material.BELL)) {
                return "USE_BELLS";
            }
        }

        return null;
    }
}
