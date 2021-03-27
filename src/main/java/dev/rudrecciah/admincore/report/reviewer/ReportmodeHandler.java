package dev.rudrecciah.admincore.report.reviewer;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportmodeHandler {
    public static void handleReport(ArrayList<Report> reports, Report report, Player p) {
        p.setMetadata("reportChecking", new FixedMetadataValue(plugin, report.reported));
        p.setMetadata("reportNum", new FixedMetadataValue(plugin, report.num));
        p.sendMessage(p.getMetadata("reportChecking").toString());
        p.sendMessage("YES");
        p.sendMessage(report.reported.toString());
        p.sendMessage(report.reporter.toString());
        p.sendMessage(String.valueOf(report.reason));
        p.sendMessage(String.valueOf(report.closed));
    }
}
