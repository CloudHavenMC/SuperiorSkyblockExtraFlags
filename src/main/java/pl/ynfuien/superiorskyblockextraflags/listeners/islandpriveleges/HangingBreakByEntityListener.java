package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class HangingBreakByEntityListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
        Entity entity = e.getEntity();

        if (!(e.getRemover() instanceof Player)) return;
        if (!(entity instanceof ItemFrame)) return;

        Player p = (Player) e.getRemover();

        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), p, "USE_ITEM_FRAMES");

        if (!cancelEvent) return;
        e.setCancelled(true);
    }
}
