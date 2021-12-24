package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

import java.util.Arrays;
import java.util.Set;

public class BlockBreakListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();

        String permission = getPermission(b.getBlockData());
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(b.getLocation(), e.getPlayer(), permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    // Get appropriate permission to the appropriate block type
    private String getPermission(BlockData bd) {
        if (bd instanceof Furnace) {
            return "USE_FURNACES";
        }

        if (bd instanceof Chest) {
            return "USE_CHESTS";
        }

        Material material = bd.getMaterial();

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
                Material.ENDER_CHEST,
                Material.HOPPER,
                Material.DISPENSER,
                Material.DROPPER
        );

        if (usableBlocks.contains(bd.getMaterial())) {
            return "USE_" + material.name() + "S";
        }

        return null;
    }
}
