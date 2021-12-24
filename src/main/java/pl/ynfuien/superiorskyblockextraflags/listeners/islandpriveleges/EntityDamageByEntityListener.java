package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

public class EntityDamageByEntityListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();

        if (!(e.getDamager() instanceof Player)) return;

        String permission = getPermission(entity);
        if (permission == null) return;

        Player p = (Player) e.getDamager();
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), p, "USE_ITEM_FRAMES");

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    private String getPermission(Entity e) {
        EntityType type = e.getType();

        if (type.equals(EntityType.ITEM_FRAME) || type.equals(EntityType.GLOW_ITEM_FRAME)) {
            return "USE_ITEM_FRAMES";
        }

        if (type.equals(EntityType.ARMOR_STAND)) {
            return "USE_ARMOR_STANDS";
        }

        return null;
    }
}
