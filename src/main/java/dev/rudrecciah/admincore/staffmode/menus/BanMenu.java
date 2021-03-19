package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.staffmode.menus.providers.BanProvider;
import dev.rudrecciah.admincore.staffmode.menus.providers.TempBanProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class BanMenu {
    public static final SmartInventory BanMenu = SmartInventory.builder()
            .provider(new BanProvider())
            .id("staffmodeBan")
            .title("Permanent Ban: Reason")
            .type(InventoryType.CHEST)
            .size(1, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        BanMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}
