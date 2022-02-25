package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class HangingBreakByEntityListener implements Listener {
    // Island privileges that this event handles:
    // - USE_ITEM_FRAMES

    @EventHandler(ignoreCancelled = true)
    public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
        // Get entity
        Entity entity = e.getEntity();

        // Return if remover isn't player
        if (!(e.getRemover() instanceof Player)) return;
        if (!(entity instanceof ItemFrame)) return;

        // Get player
        Player p = (Player) e.getRemover();
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), p, "USE_ITEM_FRAMES");

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }
}
