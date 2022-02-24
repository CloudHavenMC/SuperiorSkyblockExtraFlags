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
    @EventHandler(ignoreCancelled = true)
    public void onEntityShootBow(EntityShootBowEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;

        Player p = (Player) entity;

        String permission = getPermission(e.getBow());
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(entity.getLocation(), p, permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    private String getPermission(ItemStack item) {
        Material material = item.getType();
        if (material.equals(Material.BOW)) {
            return "SHOOT_BOW";
        }

        if (material.equals(Material.CROSSBOW)) {
            return "SHOOT_CROSSBOW";
        }

        return null;
    }
}
