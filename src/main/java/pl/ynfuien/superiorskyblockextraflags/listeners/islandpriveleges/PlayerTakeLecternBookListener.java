package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerTakeLecternBookListener implements Listener {
    // Island privileges that this event handles:
    // - USE_LECTERNS

    @EventHandler(ignoreCancelled = true)
    public void onPlayerTakeLecternBook(PlayerTakeLecternBookEvent e) {
        // Get lectern
        Lectern lectern = e.getLectern();
        // Get player
        Player p = e.getPlayer();

        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(lectern.getLocation(), p, "USE_LECTERNS");

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }
}
