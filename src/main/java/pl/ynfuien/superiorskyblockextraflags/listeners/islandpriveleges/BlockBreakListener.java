package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

import java.util.Arrays;
import java.util.Set;

public class BlockBreakListener implements Listener {
    // Island privileges that this event handles:
    // - USE_FURNACES
    // - USE_CHESTS
    // - USE_SHULKER_BOXES
    // - USE_ANVILS
    // - USE_BELLS
    // - USE_BEACONS
    // - USE_BARRELS
    // - USE_BREWING_STANDS
    // - USE_COMPOSTERS
    // - USE_ENDER_CHESTS
    // - USE_HOPPERS
    // - USE_DISPENSERS
    // - USE_DROPPERS

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        // Get block
        Block b = e.getBlock();

        // Get island privilege
        String permission = getPermission(b.getBlockData());
        // Return if no privilege was returned
        if (permission == null) return;
        // Check whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(b.getLocation(), e.getPlayer(), permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    // Get appropriate permission to the appropriate block type
    private String getPermission(BlockData bd) {
        // Return privilege for furnaces
        if (bd instanceof Furnace) {
            return "USE_FURNACES";
        }

        // Return privilege for chests
        if (bd instanceof Chest) {
            return "USE_CHESTS";
        }

        // Get block material
        Material material = bd.getMaterial();

        // Return privilege for shulker boxes
        if (material.name().endsWith("SHULKER_BOX")) {
            return "USE_SHULKER_BOXES";
        }


        // Return privilege for anvils
        if (Arrays.asList(
                        Material.ANVIL,
                        Material.CHIPPED_ANVIL,
                        Material.DAMAGED_ANVIL)
                .contains(material)) {
            return "USE_ANVILS";
        }

        // Materials that have privileges in format: USE_<material>S
        Set<Material> usableBlocks = Set.of(
                Material.BELL,
                Material.BEACON,
                Material.BARREL,
                Material.BREWING_STAND,
                Material.COMPOSTER,
                Material.ENDER_CHEST,
                Material.HOPPER,
                Material.DISPENSER,
                Material.DROPPER
        );

        // Return privilege for provided block
        if (usableBlocks.contains(bd.getMaterial())) {
            return "USE_" + material.name() + "S";
        }

        return null;
    }
}
