package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerItemConsumeListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
        ItemStack item = e.getItem();
        Player p = e.getPlayer();

        String permission = getPermission(item);
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(p.getLocation(), p, permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    private String getPermission(ItemStack item) {
        Material material = item.getType();

        if (material.equals(Material.CHORUS_FRUIT)) {
            return "EAT_CHORUS_FRUIT";
        }

        return null;
    }
}
