package pl.ynfuien.superiorskyblockextraflags.utils;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ynfuien.superiorskyblockextraflags.SuperiorSkyblockExtraFlags;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static SuperiorSkyblockExtraFlags instance;
    private static String islandProtectedMessage;
    private static String hexPrefix;

    private static ArrayList<UUID> noInteractMessages = new ArrayList<>();

    public static void setInstance(SuperiorSkyblockExtraFlags instance) {
        Util.instance = instance;
    }

    public static void log(String message) {
        instance.getLogger().info(message);
    }

    public static String formatColors(String text) {
        Pattern pattern = Pattern.compile(hexPrefix + "[a-f0-9]{6}", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String hex = text.substring(matcher.start(), matcher.end());
            text = text.replace(hex, ChatColor.of("#" + hex.substring(hexPrefix.length())).toString());
            matcher = pattern.matcher(text);
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(formatColors(message));
    }

    public static void sendIslandProtectedMessage(Player p) {
        UUID uuid = p.getUniqueId();

        if (noInteractMessages.contains(uuid)) return;
        sendMessage(p, islandProtectedMessage);

        noInteractMessages.add(uuid);
        Bukkit.getScheduler().runTaskLater(instance, () -> {
            noInteractMessages.remove(uuid);
        }, 3500 / 50);
    }

    public static boolean checkIslandPrivilege(Location location, Player p, String permission) {
        // Get island on location and return if null
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) return false;

        IslandPrivilege islandPrivilege = IslandPrivilege.getByName(permission);

        // Check if player has permission
        if (island.hasPermission(p, islandPrivilege)) return false;

        // Cancel event and send player message
        sendIslandProtectedMessage(p);
        return true;
    }

    public static void setIslandProtectedMessage(String message) {
        Util.islandProtectedMessage = formatColors(message);
    }
    public static void setHexPrefix(String prefix) {
        Util.hexPrefix = prefix;
    }
}
