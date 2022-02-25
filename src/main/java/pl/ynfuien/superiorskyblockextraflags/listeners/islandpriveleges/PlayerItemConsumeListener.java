package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerItemConsumeListener implements Listener {
    // Island privileges that this event handles:
    // - EAT_CHORUS_FRUIT

    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
        // Get item
        ItemStack item = e.getItem();

        // Return if item isn't chorus fruit
        if (!item.getType().equals(Material.CHORUS_FRUIT)) return;

        // Get player
        Player p = e.getPlayer();

        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(p.getLocation(), p, "EAT_CHORUS_FRUIT");

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }
}
