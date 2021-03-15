package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.staffmode.menus.providers.MainProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.metadata.FixedMetadataValue;

import static dev.rudrecciah.admincore.Main.plugin;

public class MainMenu {

    public static final SmartInventory MainMenu = SmartInventory.builder()
            .provider(new MainProvider())
            .id("staffmodeMain")
            .title("Staffmode Menu")
            .type(InventoryType.CHEST)
            .size(1, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        MainMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}