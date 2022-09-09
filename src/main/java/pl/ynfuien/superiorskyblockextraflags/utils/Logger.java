package pl.ynfuien.superiorskyblockextraflags.utils;

import org.bukkit.Bukkit;

public class Logger {
    private static String prefix;

    // Sets logger prefix
    public static void setPrefix(String prefix) {
        Logger.prefix = prefix;
    }

    // Logs message
    private static void logColor(String message, String color) {
        Messages.send(Bukkit.getConsoleSender(),  color + prefix + message);
    }

    // Logs message
    public static void log(String message) {
        logColor(message, "&b");
    }

    // Logs warning message
    public static void logWarning(String message) {
        logColor(message, "&e");
    }

    // Logs error message
    public static void logError(String message) {
        logColor(message, "&c");
    }
}