package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.Main;
import dev.rudrecciah.admincore.staffmode.menus.providers.MainProvider;
import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class MainMenu {

    public static final SmartInventory MainMenu = SmartInventory.builder()
            .provider(new MainProvider())
            .id("staffmodeMain")
            .title("Main Menu")
            .type(InventoryType.HOPPER)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        MainMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
        if(p.hasMetadata("staffmodeChecking")) {
            p.removeMetadata("staffmodeChecking", plugin);
        }
    }
}