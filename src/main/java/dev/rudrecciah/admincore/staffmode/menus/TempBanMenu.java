package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.staffmode.menus.providers.MainProvider;
import dev.rudrecciah.admincore.staffmode.menus.providers.TempBanProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class TempBanMenu {
    public static final SmartInventory TempbanMenu = SmartInventory.builder()
            .provider(new TempBanProvider())
            .id("staffmodeTempban")
            .title("Temporary Ban: Reason")
            .type(InventoryType.CHEST)
            .size(1, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        TempbanMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}
