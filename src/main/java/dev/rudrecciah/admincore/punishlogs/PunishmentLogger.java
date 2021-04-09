package dev.rudrecciah.admincore.punishlogs;

import dev.rudrecciah.admincore.errors.SilentErrorHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PunishmentLogger {
    public static void logTempban(OfflinePlayer t, String r, String l, String p) {
        StringBuilder str = new StringBuilder().append("*TEMPBAN*").append(System.lineSeparator()).append("Player: ").append(t.getName()).append(System.lineSeparator()).append("Length: ").append(l).append(System.lineSeparator()).append("Reason: ").append(r).append(System.lineSeparator()).append("Banner: ").append(p);
        doFileWork(str.toString());
    }

    public static void logBan(OfflinePlayer t, String r, String p) {
        StringBuilder str = new StringBuilder().append("*BAN*").append(System.lineSeparator()).append("Player: ").append(t.getName()).append(System.lineSeparator()).append("Length: ").append("Forever").append(System.lineSeparator()).append("Reason: ").append(r).append(System.lineSeparator()).append("Banner: ").append(p);
        doFileWork(str.toString());
    }

    public static void logIpBan(Player t, String r, String p) {
        StringBuilder str = new StringBuilder().append("*IPBAN*").append(System.lineSeparator()).append("Player: ").append(t.getAddress().getHostString()).append(System.lineSeparator()).append("Length: ").append("Forever").append(System.lineSeparator()).append("Reason: ").append(r).append(System.lineSeparator()).append("Banner: ").append(p);
        doFileWork(str.toString());
    }

    public static void logMute(Player t, String r, int i, Player p) {
        StringBuilder str = new StringBuilder().append("*MUTE*").append(System.lineSeparator()).append("Player: ").append(t.getDisplayName()).append(System.lineSeparator()).append("Reason: ").append(r).append(System.lineSeparator()).append("Length: ").append(i).append(System.lineSeparator()).append("Muter: ").append(p);
        doFileWork(str.toString());
    }

    public static void logUnmute(Player t, String p) {
        StringBuilder str = new StringBuilder().append("*UNMUTE*").append(System.lineSeparator()).append("Player: ").append(t.getDisplayName()).append(System.lineSeparator()).append("Unmuter: ").append(p);
        doFileWork(str.toString());
    }

    public static void logPardon(String t, String p) {
        StringBuilder str = new StringBuilder().append("*PARDON*").append(System.lineSeparator()).append("Player: ").append(t).append(System.lineSeparator()).append("Pardoner: ").append(p);
        doFileWork(str.toString());
    }

    public static void logIpPardon(String t, String p) {
        StringBuilder str = new StringBuilder().append("*IPPARDON*").append(System.lineSeparator()).append("IP/Hostname: ").append(t).append(System.lineSeparator()).append("Pardoner: ").append(p);
        doFileWork(str.toString());
    }

    public static void doFileWork(String str) {
        File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "log");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "PUNISHMENT_LOGS.rtl");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                SilentErrorHandler.onSilentError(e, 0);
                return;
            }
        }
        FileWriter writer;
        try {
            StringBuilder logs = new StringBuilder();
            logs.append(new String(Files.readAllBytes(Paths.get(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "log" + File.separator + "PUNISHMENT_LOGS.rtl")), StandardCharsets.UTF_8)).append(System.lineSeparator()).append("------").append(System.lineSeparator()).append("-EPOCH ").append(System.currentTimeMillis()).append("-").append(System.lineSeparator()).append(str);
            writer = new FileWriter(file);
            writer.write(logs.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
