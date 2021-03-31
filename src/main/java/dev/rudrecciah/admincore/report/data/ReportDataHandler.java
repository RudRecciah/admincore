package dev.rudrecciah.admincore.report.data;

import dev.rudrecciah.admincore.report.reviewer.Report;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReportDataHandler {

    public static int handleReportData(OfflinePlayer p, int reason, Player r) {
        ReportDataLoader loader = new ReportDataLoader();
        loader.saveDefaultReportData(p.getUniqueId());
        loader.get().options().copyDefaults(true);
        loader.get().addDefault("amount", 0);
        loader.get().set("amount", loader.get().getInt("amount") + 1);
        int i = loader.get().getInt("amount");
        loader.get().addDefault("report" + i + ".reason", reason);
        loader.get().addDefault("report" + i + ".reporter", String.valueOf(r.getUniqueId()));
        loader.get().addDefault("report" + i + ".closed", false);
        loader.saveReportData();
        return i;
    }

    public static Integer getAmount(UUID uuid) {
        ReportDataLoader loader = new ReportDataLoader();
        if(loader.checkForReportData(uuid)) {
            loader.saveDefaultReportData(uuid);
            loader.get().options().copyDefaults(true);
            loader.get().addDefault("amount", 0);
            loader.saveReportData();
            return loader.get().getInt("amount");
        }
        return null;
    }

    public static Report getReport(UUID uuid, int i) {
        ReportDataLoader loader = new ReportDataLoader();
        if(loader.checkForReportData(uuid)) {
            loader.saveDefaultReportData(uuid);
            loader.get().options().copyDefaults(true);
            int reason = loader.get().getInt("report" + (i + 1) + ".reason");
            UUID reporter = UUID.fromString(loader.get().getString("report" + (i + 1) + ".reporter"));
            boolean closed = loader.get().getBoolean("report" + (i + 1) + ".closed");
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

    public static boolean closeReport(UUID uuid, int i) {
        ReportDataLoader loader = new ReportDataLoader();
        loader.saveDefaultReportData(uuid);
        loader.get().options().copyDefaults(true);
        if(loader.get().getBoolean("report" + i + ".closed")) {
            return true;
        }
        loader.get().set("report" + (i) + ".closed", true);
        loader.saveReportData();
        return false;
    }

    public static void closeAllReports(UUID uuid) {
        ReportDataLoader loader = new ReportDataLoader();
        loader.saveDefaultReportData(uuid);
        loader.get().options().copyDefaults(true);
        int amount = loader.get().getInt("amount");
        for(int i = 1; i < amount + 1; i++) {
            loader.get().set("report" + i + ".closed", true);
        }
        loader.saveReportData();
    }
}
