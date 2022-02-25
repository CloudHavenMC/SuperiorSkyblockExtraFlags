package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class EntityToggleGlideListener implements Listener {
    // Island privileges that this event handles:
    // - USE_ELYTRA

    @EventHandler(ignoreCancelled = true)
    public void onEntityToggleGlide(EntityToggleGlideEvent e) {
        // Get entity
        Entity entity = e.getEntity();
        // Return if entity isn't player
        if (!(entity instanceof Player)) return;

        // Return if player isn't gliding
        if (!e.isGliding()) return;

        // Get player
        Player p = (Player) entity;

        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(p.getLocation(), p, "USE_ELYTRA");

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }
}
