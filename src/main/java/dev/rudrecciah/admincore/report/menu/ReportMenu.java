package dev.rudrecciah.admincore.report.menu;

import dev.rudrecciah.admincore.report.menu.provider.ReportProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportMenu {
    public static final SmartInventory ReportMenu = SmartInventory.builder()
            .provider(new ReportProvider())
            .id("playerReport")
            .title("Report: Reason")
            .type(InventoryType.CHEST)
            .size(1, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        ReportMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}
