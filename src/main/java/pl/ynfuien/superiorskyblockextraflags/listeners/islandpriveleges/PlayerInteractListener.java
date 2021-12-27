package pl.ynfuien.superiorskyblockextraflags.listeners.islandpriveleges;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.ynfuien.superiorskyblockextraflags.utils.Util;

import java.util.Arrays;
import java.util.Set;

public class PlayerInteractListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        // Check for left click air
        Action action = e.getAction();
        if (action.equals(Action.LEFT_CLICK_AIR)) return;

        Block b = e.getClickedBlock();

        String permission = getPermission(action, e.getItem(), b);
        if (permission == null) return;
        boolean cancelEvent = Util.checkIslandPrivilege(b.getLocation(), e.getPlayer(), permission);

        if (!cancelEvent) return;
        e.setCancelled(true);
    }

    // Get appropriate permission to the appropriate block type
    private String getPermission(Action action, ItemStack item, Block b) {
        if (item != null && (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))) {
            if (item.getType().equals(Material.BOW)) {
                return "SHOOT_BOW";
            }

            if (item.getType().equals(Material.CROSSBOW)) {
                return "SHOOT_CROSSBOW";
            }
        }

        if (b == null) return null;

        BlockData bd = b.getBlockData();
        Material material = b.getType();

        if (action.equals(Action.PHYSICAL)) {
            if (material.name().endsWith("PRESSURE_PLATE")) {
                return "USE_PRESSURE_PLATES";
            }

            if (material.equals(Material.BIG_DRIPLEAF)) {
                return "USE_DRIPLEAF";
            }

            if (material.equals(Material.TRIPWIRE)) {
                return "USE_TRIPWIRES";
            }

            return null;
        }

        if (action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (material.equals(Material.DRAGON_EGG)) {
                return "PAT_DRAGON_EGG";
            }
        }

        if (action.equals(Action.LEFT_CLICK_BLOCK)) {
            if (bd instanceof Candle) {
                return "USE_CANDLES";
            }

            return null;
        }

        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (material.equals(Material.LEVER)) {
                return "USE_LEVERS";
            }

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

            if (bd.getMaterial().name().endsWith("SHULKER_BOX")) {
                return "USE_SHULKER_BOXES";
            }

            if (bd instanceof Bed) {
                return "USE_BEDS";
            }

            if (bd instanceof Candle) {
                return "USE_CANDLES";
            }


            if (material.equals(Material.FLOWER_POT) || material.name().startsWith("POTTED_")) {
                return "USE_FLOWER_POTS";
            }

            if (material.name().endsWith("CANDLE_CAKE")) {
                if (item == null || !item.getType().equals(Material.FLINT_AND_STEEL)) {
                    return "EAT_CAKE";
                }
            }

            if (material.equals(Material.CAKE)) {
                return "EAT_CAKE";
            }

            if (material.equals(Material.PUMPKIN)) {
                if (item != null && item.getType().equals(Material.SHEARS)) {
                    return "CARVE_PUMPKINS";
                }
            }

            if (material.equals(Material.SWEET_BERRY_BUSH)) {
                return "COLLECT_SWEET_BERRIES";
            }

            if (material.equals(Material.CAVE_VINES_PLANT) || material.equals(Material.CAVE_VINES)) {
                return "COLLECT_GLOW_BERRIES";
            }

            if (material.equals(Material.JUKEBOX)) {
                return "USE_JUKEBOXES";
            }

            if (material.equals(Material.LECTERN)) {
                Lectern lectern = (Lectern) b.getState();
                if (lectern.getInventory().isEmpty()) {
                    return "USE_LECTERNS";
                }
            }

            if (Arrays.asList(
                            Material.ANVIL,
                            Material.CHIPPED_ANVIL,
                            Material.DAMAGED_ANVIL)
                    .contains(material)) {
                return "USE_ANVILS";
            }

            if (Arrays.asList(
                            Material.CAULDRON,
                            Material.LAVA_CAULDRON,
                            Material.WATER_CAULDRON,
                            Material.POWDER_SNOW_CAULDRON)
                    .contains(material)) {
                return "USE_CAULDRONS";
            }

            Set<Material> usableBlocks = Set.of(
                    Material.BELL,
                    Material.BEACON,
                    Material.BARREL,
                    Material.BREWING_STAND,
                    Material.COMPOSTER,
                    Material.ENCHANTING_TABLE,
                    Material.ENDER_CHEST,
                    Material.HOPPER,
                    Material.DISPENSER,
                    Material.DROPPER,
                    Material.NOTE_BLOCK,
                    Material.REPEATER,
                    Material.COMPARATOR,
                    Material.DAYLIGHT_DETECTOR
            );

            if (usableBlocks.contains(material)) {
                return "USE_" + material.name() + "S";
            }

            if (item != null && (item.getType().equals(Material.ITEM_FRAME) || item.getType().equals(Material.GLOW_ITEM_FRAME))) {
                return "USE_ITEM_FRAMES";
            }

            return null;
        }

        return null;
    }
}