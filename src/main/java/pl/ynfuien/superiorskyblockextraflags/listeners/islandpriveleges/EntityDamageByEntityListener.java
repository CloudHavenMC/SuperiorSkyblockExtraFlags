package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class EntityDamageByEntityListener implements Listener {
    // Island privileges that this event handles:
    // - USE_ITEM_FRAMES
    // - USE_ARMOR_STANDS

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        // Get entity
        Entity entity = e.getEntity();

        // Return if damager isn't player
        if (!(e.getDamager() instanceof Player)) return;

        // Get island privilege
        String permission = getPermission(entity);
        // Return if no privilege was returned
        if (permission == null) return;

        // Get player
        Player p = (Player) e.getDamager();
        // Check whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), p, permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    private String getPermission(Entity e) {
        // Get entity type
        EntityType type = e.getType();

        // Return privilege for item frames
        if (type.equals(EntityType.ITEM_FRAME) || type.equals(EntityType.GLOW_ITEM_FRAME)) {
            return "USE_ITEM_FRAMES";
        }

        // Return privilege for armor stands
        if (type.equals(EntityType.ARMOR_STAND)) {
            return "USE_ARMOR_STANDS";
        }

        return null;
    }
}
