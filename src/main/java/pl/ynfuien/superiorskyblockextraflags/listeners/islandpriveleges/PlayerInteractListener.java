package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

import java.util.Arrays;
import java.util.Set;

public class PlayerInteractListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent e) {
        // Check for right click or physical action
        Action action = e.getAction();
        if (!action.equals(Action.RIGHT_CLICK_BLOCK) && !action.equals(Action.PHYSICAL)) {
            return;
        }

        Block b = e.getClickedBlock();

        String permission = getPermission(e.getItem(), b.getBlockData());
        Util.log(permission);
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(b.getLocation(), e.getPlayer(), permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    // Get appropriate permission to the appropriate block type
    private String getPermission(ItemStack item, BlockData bd) {
        if (bd instanceof Switch) {
            return "USE_BUTTONS";
        }

        if (bd instanceof Door) {
            return "USE_DOORS";
        }

        if (bd instanceof TrapDoor) {
            return "USE_TRAPDOORS";
        }

        if (bd instanceof Gate) {
            return "USE_FENCE_GATES";
        }

        if (bd instanceof Furnace) {
            return "USE_FURNACES";
        }

        if (bd instanceof Chest) {
            return "USE_CHESTS";
        }

        if (bd instanceof Bed) {
            return "USE_BEDS";
        }

        Material material = bd.getMaterial();

        if (material.equals(Material.SWEET_BERRY_BUSH)) {
            return "COLLECT_SWEET_BERRIES";
        }

        if (material.equals(Material.CAVE_VINES_PLANT) || material.equals(Material.CAVE_VINES)) {
            return "COLLECT_GLOW_BERRIES";
        }

        if (Arrays.asList(
                Material.ANVIL,
                Material.CHIPPED_ANVIL,
                Material.DAMAGED_ANVIL)
                .contains(material)) {
            return "USE_ANVILS";
        }

        Set<Material> usableBlocks = Set.of(
                Material.BELL,
                Material.BARREL,
                Material.COMPOSTER,
                Material.ENCHANTING_TABLE,
                Material.ENDER_CHEST,
                Material.HOPPER,
                Material.DISPENSER,
                Material.DROPPER
                );

        if (usableBlocks.contains(material)) {
            return "USE_" + material.name() + "S";
        }

        if (material.name().endsWith("PRESSURE_PLATE")) {
            return "USE_PRESSURE_PLATES";
        }

        if (material.equals(Material.BIG_DRIPLEAF)) {
            return "USE_DRIPLEAF";
        }

        if (item != null && (item.getType().equals(Material.ITEM_FRAME) || item.getType().equals(Material.GLOW_ITEM_FRAME))) {
            return "USE_ITEM_FRAMES";
        }

        return null;
    }
}