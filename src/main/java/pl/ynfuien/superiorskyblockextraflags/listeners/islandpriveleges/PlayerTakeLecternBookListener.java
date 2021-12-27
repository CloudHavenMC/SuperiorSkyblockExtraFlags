package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerTakeLecternBookListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerTakeLecternBook(PlayerTakeLecternBookEvent e) {
        Lectern lectern = e.getLectern();
        Player p = e.getPlayer();

        boolean cancelEvent = Util.checkIslandPrivilege(lectern.getLocation(), p, "USE_LECTERNS");

        if (!cancelEvent) return;
        e.setCancelled(true);
    }
}
