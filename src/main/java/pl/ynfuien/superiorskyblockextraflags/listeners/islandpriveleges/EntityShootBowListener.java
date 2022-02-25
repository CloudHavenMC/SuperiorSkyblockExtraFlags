package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class EntityShootBowListener implements Listener {
    // Island privileges that this event handles:
    // - SHOOT_BOW
    // - SHOOT_CROSSBOW

    @EventHandler(ignoreCancelled = true)
    public void onEntityShootBow(EntityShootBowEvent e) {
        // Get entity
        Entity entity = e.getEntity();
        // Return if entity isn't player
        if (!(entity instanceof Player)) return;

        // Get player
        Player p = (Player) entity;

        // Get island privilege
        String permission = getPermission(e.getBow());
        // Return if no privilege was returned
        if (permission == null) return;
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), p, permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    private String getPermission(ItemStack item) {
        // Get material of item
        Material material = item.getType();

        // Return privilege for bow
        if (material.equals(Material.BOW)) {
            return "SHOOT_BOW";
        }

        // Return privilege for crossbow
        if (material.equals(Material.CROSSBOW)) {
            return "SHOOT_CROSSBOW";
        }

        return null;
    }
}
