package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerPortalListener implements Listener {
    // Island privileges that this event handles:
    // - USE_NETHER_PORTAL
    // - USE_END_PORTAL

    @EventHandler(ignoreCancelled = true)
    public void onPlayerPortal(PlayerPortalEvent e) {
        // Get player
        Player p = e.getPlayer();

        // Get island privilege
        String permission = getPermission(e.getCause());
        // Return if no privilege was returned
        if (permission == null) return;
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(e.getFrom(), p, permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    private String getPermission(TeleportCause cause) {
        // Return privilege for nether portal
        if (cause.equals(TeleportCause.NETHER_PORTAL)) {
            return "USE_NETHER_PORTAL";
        }

        // Return privilege for end portal
        if (cause.equals(TeleportCause.END_PORTAL)) {
            return "USE_END_PORTAL";
        }

        return null;
    }
}
