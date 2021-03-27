package dev.rudrecciah.admincore.report.data;

import dev.rudrecciah.admincore.report.reviewer.Report;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportDataHandler {

    public static int handleReportData(OfflinePlayer p, int reason, Player r) {
        ReportDataLoader.saveDefaultReportData(p.getUniqueId());
        ReportDataLoader.get().options().copyDefaults(true);
        ReportDataLoader.get().addDefault("amount", 0);
        ReportDataLoader.get().set("amount", ReportDataLoader.get().getInt("amount") + 1);
        int i = ReportDataLoader.get().getInt("amount");
        ReportDataLoader.get().addDefault("report" + i + ".reason", reason);
        ReportDataLoader.get().addDefault("report" + i + ".reporter", String.valueOf(r.getUniqueId()));
        ReportDataLoader.get().addDefault("report" + i + ".closed", false);
        ReportDataLoader.saveReportData();
        return i;
    }

    public static Integer getAmount(UUID uuid) {
        if(ReportDataLoader.checkForReportData(uuid)) {
            ReportDataLoader.saveDefaultReportData(uuid);
            ReportDataLoader.get().options().copyDefaults(true);
            ReportDataLoader.get().addDefault("amount", 0);
            ReportDataLoader.saveReportData();
            return ReportDataLoader.get().getInt("amount");
        }
        return null;
    }

    public static Report getReport(UUID uuid, int i) {
        if(ReportDataLoader.checkForReportData(uuid)) {
            ReportDataLoader.saveDefaultReportData(uuid);
            ReportDataLoader.get().options().copyDefaults(true);
            int reason = ReportDataLoader.get().getInt("report" + (i + 1) + ".reason");
            UUID reporter = UUID.fromString(ReportDataLoader.get().getString("report" + (i + 1) + ".reporter"));
            boolean closed = ReportDataLoader.get().getBoolean("report" + (i + 1) + ".closed");
            Report report = new Report();
            report.reported = uuid;
            report.reason = reason;
            report.reporter = reporter;
            report.closed = closed;
            report.num = i + 1;
            return report;
        }
        return null;
    }

    public static void closeReport(UUID uuid, int i) {
        ReportDataLoader.saveDefaultReportData(uuid);
        ReportDataLoader.get().options().copyDefaults(true);
        ReportDataLoader.get().set("report" + (i) + ".closed", true);
        ReportDataLoader.saveReportData();
    }
}
