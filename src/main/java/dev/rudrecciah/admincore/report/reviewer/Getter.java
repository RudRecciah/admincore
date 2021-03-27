package dev.rudrecciah.admincore.report.reviewer;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.report.data.ReportDataHandler;
import dev.rudrecciah.admincore.report.data.ReportDataLoader;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import static dev.rudrecciah.admincore.Main.plugin;

public class Getter {
    public static ArrayList<Report> getReports(UUID uuid) {
        ArrayList<Report> reports = new ArrayList<>();
        Integer amount = ReportDataHandler.getAmount(uuid);
        if(amount != null && amount != 0) {
            for(int i = 0; i < amount; i++) {
                Report report = ReportDataHandler.getReport(uuid, i);
                if(report != null && !report.closed) {
                    reports.add(report);
                }
            }
            if(reports.size() != 0) {
                return reports;
            }
        }
        return null;
    }

    public static UUID getRandUUID(UUID last, Player p) {
        File dir = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "rd");
        if(!dir.exists()) {
            return null;
        }
        String[] files = dir.list();
        for(String fileName : files) {
            UUID uuid = UUID.fromString(fileName.replaceAll(".yml", ""));
            ArrayList<Report> reports = new ArrayList<>();
            Integer amount = ReportDataHandler.getAmount(uuid);
            if(amount != null && amount != 0) {
                for(int i = 0; i < amount; i++) {
                    Report report = ReportDataHandler.getReport(uuid, i);
                    if(report != null && !report.closed && report.reported != last) {
                        reports.add(report);
                    }
                }
                if(reports.size() != 0) {
                    return uuid;
                }
            }
        }
        return null;
    }
}
