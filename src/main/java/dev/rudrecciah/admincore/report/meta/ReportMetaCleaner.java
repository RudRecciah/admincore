package dev.rudrecciah.admincore.report.meta;

import org.bukkit.entity.Player;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportMetaCleaner {
    public static void cleanReportMeta(Player p) {
        if(p.hasMetadata("reporting")) {
            p.removeMetadata("reporting", plugin);
        }
    }
}
