package pl.ynfuien.superiorskyblockextraflags.utils;

import org.bukkit.Bukkit;

public class Logger {
    private static String prefix;

    // Sets logger prefix
    public static void setPrefix(String prefix) {
        Logger.prefix = prefix;
    }

    // Logs message
    public static void log(String message) {
        Messages.send(Bukkit.getConsoleSender(),  prefix + message);
    }

    // Logs warning message
    public static void logWarning(String message) {
        log("&e" + message);
    }

    // Logs error message
    public static void logError(String message) {
        log("&c" + message);
    }
}