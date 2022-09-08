package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

import java.util.Arrays;

public class PlayerInteractEntityListener implements Listener {
    // Island privileges that this event handles:
    // - USE_ITEM_FRAMES
    // - ROTATE_ITEM_FRAME_ITEMS
    // - USE_ARMOR_STANDS
    // - DYE_COLLARS

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        // Get entity
        Entity entity = e.getRightClicked();

        // Get player
        Player p = e.getPlayer();

        // Get island privilege
        String permission = getPermission(entity, p, e.getHand());
        // Return if no privilege was returned
        if (permission == null) return;
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), p, permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    private String getPermission(Entity e, Player p, EquipmentSlot hand) {
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

        if (e instanceof Wolf || e instanceof Cat) {
            Material item = p.getInventory().getItem(hand).getType();
            if (!item.isItem() || !item.toString().endsWith("_DYE")) return null;

            Tameable tameable = ((Tameable) e);
            if (!tameable.isTamed()) return null;
            if (!tameable.isTamed() || tameable.getOwner().equals(p)) return null;

            return "DYE_COLLARS";
        }

        return null;
    }
}
