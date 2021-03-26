package dev.rudrecciah.admincore.report.data;

import dev.rudrecciah.admincore.playerdata.PlayerDataLoader;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

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
}
