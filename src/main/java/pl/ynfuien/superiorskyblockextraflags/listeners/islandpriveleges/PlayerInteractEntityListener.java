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
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();

        String permission = getPermission(entity);
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), e.getPlayer(), permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    private String getPermission(Entity e) {
        if (e instanceof ItemFrame) {
            if (((ItemFrame) e).getItem().getType().equals(Material.AIR)) {
                return "USE_ITEM_FRAMES";
            }

            return "ROTATE_ITEM_FRAME_ITEMS";
        }

        if (e instanceof ArmorStand) {
            return "USE_ARMOR_STANDS";
        }

        return null;
    }
}
