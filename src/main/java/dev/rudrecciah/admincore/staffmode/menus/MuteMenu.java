package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.staffmode.menus.providers.MainProvider;
import dev.rudrecciah.admincore.staffmode.menus.providers.MuteProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class MuteMenu {
    public static final SmartInventory MuteMenu = SmartInventory.builder()
            .provider(new MuteProvider())
            .id("staffmodeMute")
            .title("Mute: Reason")
            .type(InventoryType.CHEST)
            .size(1, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        MuteMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}
