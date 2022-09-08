package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class SheepDyeWoolListener implements Listener {
    // Island privileges that this event handles:
    // - DYE_SHEEP

    @EventHandler(ignoreCancelled = true)
    public void onSheepDyeWool(SheepDyeWoolEvent e) {
        // Get player
        Player p = e.getPlayer();

        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(e.getEntity().getLocation(), p, "DYE_SHEEP");

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }
}
