package pl.ynfuien.superiorskyblockextraflags.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ynfuien.superiorskyblockextraflags.SuperiorSkyblockExtraFlags;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messages {
    private static String hexPrefix = "&#";
    private static String islandProtectedMessage = "";
    private static List<UUID> islandProtectedMessages = new ArrayList<>();

    // Sets message hex colors prefix
    public static void setHexPrefix(String hexPrefix) {
        Messages.hexPrefix = hexPrefix;
    }
    // Sets island protected message
    public static void setIslandProtectedMessage(String islandProtectedMessage) {
        Messages.islandProtectedMessage = islandProtectedMessage;
    }

    // Formats colors
    public static String formatColors(String text) {
        Pattern pattern = Pattern.compile(hexPrefix+"[a-fA-F0-9]{6}");

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, ChatColor.of(color.substring(1)).toString());
            matcher = pattern.matcher(text);
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }

    // Formats placeholders
    public static String formatPapi(Player player, String text) {
        if (!Util.isPapiEnabled()) return text;

        return PlaceholderAPI.setPlaceholders(player, text);
    }

    // Formats colors and placeholders
    public static String format(Player player, String text) {
        text = formatColors(text);
        if (!Util.isPapiEnabled()) return text;

        return PlaceholderAPI.setPlaceholders(player, text);
    }

    // Formats colors and placeholders and then sends message
    public static void send(CommandSender sender, String message) {
        // If command sender is player, parse placeholders in message
        if (sender instanceof Player) message = formatPapi((Player) sender, message);

        // Color message
        message = formatColors(message);

        // Send message
        sender.sendMessage(message);
    }

    // Sends island protected message to player
    public static void sendIslandProtectedMessage(Player p) {
        // Get UUID
        UUID uuid = p.getUniqueId();

        // Return if list contains player's uuid
        if (islandProtectedMessages.contains(uuid)) return;
        // Send island protected message
        Messages.send(p, islandProtectedMessage);

        // Add player's uuid to list
        islandProtectedMessages.add(uuid);
        // Remove player's uuid from list after 3,5 seconds
        Bukkit.getScheduler().runTaskLater(SuperiorSkyblockExtraFlags.getInstance(), () -> {
            islandProtectedMessages.remove(uuid);
        }, 70);
    }
}
