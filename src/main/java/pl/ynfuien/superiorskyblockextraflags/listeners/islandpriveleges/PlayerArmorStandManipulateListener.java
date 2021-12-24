package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerArmorStandManipulateListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
        ArmorStand armorStand = e.getRightClicked();

        boolean cancelEvent = Util.checkIslandPrivilege(armorStand.getLocation(), e.getPlayer(), "USE_ARMOR_STANDS");

        if (!cancelEvent) return;
        e.setCancelled(true);
    }
}
