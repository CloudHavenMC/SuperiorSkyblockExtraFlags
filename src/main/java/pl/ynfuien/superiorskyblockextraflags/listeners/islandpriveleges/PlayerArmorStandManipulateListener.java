package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerArmorStandManipulateListener implements Listener {
    // Island privileges that this event handles:
    // - USE_ARMOR_STANDS

    @EventHandler(ignoreCancelled = true)
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
        // Get armor stand
        ArmorStand armorStand = e.getRightClicked();
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(armorStand.getLocation(), e.getPlayer(), "USE_ARMOR_STANDS");

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }
}
