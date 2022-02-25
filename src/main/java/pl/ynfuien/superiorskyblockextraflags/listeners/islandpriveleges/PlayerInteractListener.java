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
    // Island privileges that this event handles:
    // - SHOOT_BOW
    // - SHOOT_CROSSBOW
    // - USE_PRESSURE_PLATES
    // - USE_DRIPLEAF
    // - USE_TRIPWIRES
    // - PAT_DRAGON_EGG
    // - USE_CANDLES
    // - USE_LEVERS
    // - USE_BUTTONS
    // - USE_DOORS
    // - USE_TRAPDOORS
    // - USE_FENCE_GATES
    // - USE_FURNACES
    // - USE_CHESTS
    // - USE_SHULKER_BOXES
    // - USE_BEDS
    // - USE_FLOWER_POTS
    // - EAT_CAKE
    // - CARVE_PUMPKINS
    // - COLLECT_SWEET_BERRIES
    // - COLLECT_GLOW_BERRIES
    // - USE_JUKEBOXES
    // - USE_LECTERNS
    // - USE_ANVILS
    // - USE_CAULDRONS
    // - USE_BELLS
    // - USE_BEACONS
    // - USE_BARRELS
    // - USE_BREWING_STANDS
    // - USE_COMPOSTERS
    // - USE_ENCHANTING_TABLES
    // - USE_ENDER_CHESTS
    // - USE_HOPPERS
    // - USE_DISPENSERS
    // - USE_DROPPERS
    // - USE_NOTE_BLOCKS
    // - USE_REPEATERS
    // - USE_COMPARATORS
    // - USE_DAYLIGHT_DETECTORS
    // - USE_ITEM_FRAMES

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        // Get action
        Action action = e.getAction();
        // Return if action is left click air
        if (action.equals(Action.LEFT_CLICK_AIR)) return;

        // Get clicked block
        Block b = e.getClickedBlock();

        // Get island privilege
        String permission = getPermission(action, e.getItem(), b);
        // Return if no privilege was returned
        if (permission == null) return;
        // Get whether to cancel event
        boolean cancelEvent = Util.checkIslandPrivilege(b.getLocation(), e.getPlayer(), permission);

        // Return if not cancel event
        if (!cancelEvent) return;
        // Cancel event
        e.setCancelled(true);
    }

    // Get appropriate permission to the appropriate block type
    private String getPermission(Action action, ItemStack item, Block b) {
        // If item isn't null and action is right click block or air
        if (item != null && (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))) {
            // Return privilege for bow
            if (item.getType().equals(Material.BOW)) {
                return "SHOOT_BOW";
            }

            // Return privilege for crossbow
            if (item.getType().equals(Material.CROSSBOW)) {
                return "SHOOT_CROSSBOW";
            }
        }

        // Return if clicked block is null
        if (b == null) return null;

        // Get block data
        BlockData bd = b.getBlockData();
        // Get block material
        Material material = b.getType();

        // If action is physical
        if (action.equals(Action.PHYSICAL)) {
            // Return privilege for pressure plates
            if (material.name().endsWith("PRESSURE_PLATE")) {
                return "USE_PRESSURE_PLATES";
            }
            // Return privilege for dripleaf
            if (material.equals(Material.BIG_DRIPLEAF)) {
                return "USE_DRIPLEAF";
            }
            // Return privilege for tripwires
            if (material.equals(Material.TRIPWIRE)) {
                return "USE_TRIPWIRES";
            }

            return null;
        }

        // If action is left or right click block
        if (action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            // Return privilege for dragon egg
            if (material.equals(Material.DRAGON_EGG)) {
                return "PAT_DRAGON_EGG";
            }
        }

        // If action is left click block
        if (action.equals(Action.LEFT_CLICK_BLOCK)) {
            // Return privilege for candles
            if (bd instanceof Candle) {
                return "USE_CANDLES";
            }

            return null;
        }

        // If action is right click block
        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            // Return privilege for levers
            if (material.equals(Material.LEVER)) {
                return "USE_LEVERS";
            }
            // Return privilege for buttons
            if (bd instanceof Switch) {
                return "USE_BUTTONS";
            }
            // Return privilege for doors
            if (bd instanceof Door) {
                return "USE_DOORS";
            }
            // Return privilege for trapdoors
            if (bd instanceof TrapDoor) {
                return "USE_TRAPDOORS";
            }
            // Return privilege for fence gates
            if (bd instanceof Gate) {
                return "USE_FENCE_GATES";
            }
            // Return privilege for furnaces
            if (bd instanceof Furnace) {
                return "USE_FURNACES";
            }
            // Return privilege for chests
            if (bd instanceof Chest) {
                return "USE_CHESTS";
            }
            // Return privilege for shulker boxes
            if (bd.getMaterial().name().endsWith("SHULKER_BOX")) {
                return "USE_SHULKER_BOXES";
            }
            // Return privilege for beds
            if (bd instanceof Bed) {
                return "USE_BEDS";
            }
            // Return privilege for candles
            if (bd instanceof Candle) {
                return "USE_CANDLES";
            }

            // Return privilege for flower pots
            if (material.equals(Material.FLOWER_POT) || material.name().startsWith("POTTED_")) {
                return "USE_FLOWER_POTS";
            }

            // Return privilege for cake
            if (material.name().endsWith("CANDLE_CAKE")) {
                if (item == null || !item.getType().equals(Material.FLINT_AND_STEEL)) {
                    return "EAT_CAKE";
                }
            }
            // Return privilege for cake
            if (material.equals(Material.CAKE)) {
                return "EAT_CAKE";
            }

            // Return privilege for pumpkins
            if (material.equals(Material.PUMPKIN)) {
                if (item != null && item.getType().equals(Material.SHEARS)) {
                    return "CARVE_PUMPKINS";
                }
            }

            // Return privilege for sweet berries
            if (material.equals(Material.SWEET_BERRY_BUSH)) {
                return "COLLECT_SWEET_BERRIES";
            }
            // Return privilege for glow berries
            if (material.equals(Material.CAVE_VINES_PLANT) || material.equals(Material.CAVE_VINES)) {
                return "COLLECT_GLOW_BERRIES";
            }

            // Return privilege for jukeboxes
            if (material.equals(Material.JUKEBOX)) {
                return "USE_JUKEBOXES";
            }

            // Return privilege for lecterns
            if (material.equals(Material.LECTERN)) {
                Lectern lectern = (Lectern) b.getState();
                if (lectern.getInventory().isEmpty()) {
                    return "USE_LECTERNS";
                }
            }

            // Return privilege for anvils
            if (Arrays.asList(
                            Material.ANVIL,
                            Material.CHIPPED_ANVIL,
                            Material.DAMAGED_ANVIL)
                    .contains(material)) {
                return "USE_ANVILS";
            }

            // Return privilege for cauldrons
            if (Arrays.asList(
                            Material.CAULDRON,
                            Material.LAVA_CAULDRON,
                            Material.WATER_CAULDRON,
                            Material.POWDER_SNOW_CAULDRON)
                    .contains(material)) {
                return "USE_CAULDRONS";
            }

            // Materials that have privileges in format: USE_<material>S
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

            // Return privilege for material
            if (usableBlocks.contains(material)) {
                return "USE_" + material.name() + "S";
            }

            // Return privilege for item frames
            if (item != null && (item.getType().equals(Material.ITEM_FRAME) || item.getType().equals(Material.GLOW_ITEM_FRAME))) {
                return "USE_ITEM_FRAMES";
            }

            return null;
        }

        return null;
    }
}