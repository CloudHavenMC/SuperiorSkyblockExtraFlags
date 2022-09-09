package pl.ynfuien.superiorskyblockextraflags.utils;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Util {
    // Check whether player has permission in current island
    public static boolean checkIslandPrivilege(Location location, Player p, String permission) {
        // Get island on location and return if null
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) return false;

        // Get island privilege
        IslandPrivilege islandPrivilege = IslandPrivilege.getByName(permission);

        // Return if player has permission
        if (island.hasPermission(p, islandPrivilege)) return false;

        // Send island protected message to player
        Messages.sendIslandProtectedMessage(p, permission);
        return true;
    }


    // Get true if papi is enabled
    public static boolean isPapiEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }
}