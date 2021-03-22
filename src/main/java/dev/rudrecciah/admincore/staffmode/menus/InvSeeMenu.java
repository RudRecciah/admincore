package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.staffmode.menus.providers.InvSeeProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class InvSeeMenu {
    public static final SmartInventory InvSeeMenu = SmartInventory.builder()
            .provider(new InvSeeProvider())
            .id("staffmodeInvsee")
            .title("Inventory See Menu")
            .type(InventoryType.CHEST)
            .size(6, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        InvSeeMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}
