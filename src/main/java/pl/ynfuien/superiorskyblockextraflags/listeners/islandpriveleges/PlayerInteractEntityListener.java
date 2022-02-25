package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class PlayerInteractEntityListener implements Listener {
    // Island privileges that this event handles:
    // - USE_ITEM_FRAMES
    // - ROTATE_ITEM_FRAME_ITEMS
    // - USE_ARMOR_STANDS

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        // Get entity
        Entity entity = e.getRightClicked();

        // Get island privilege
        String permission = getPermission(entity);
        // Return if no privilege was returned
        if (permission == null) return;
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), e.getPlayer(), permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    private String getPermission(Entity e) {
        // Privileges for item frames
        if (e instanceof ItemFrame) {
            // Return privilege for item frame use, if no item is in it
            if (((ItemFrame) e).getItem().getType().equals(Material.AIR)) {
                return "USE_ITEM_FRAMES";
            }

            // Return privilege for rotate item frame items
            return "ROTATE_ITEM_FRAME_ITEMS";
        }

        // Return privilege for armor stands
        if (e instanceof ArmorStand) {
            return "USE_ARMOR_STANDS";
        }

        return null;
    }
}
