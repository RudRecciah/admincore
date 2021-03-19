package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.staffmode.menus.providers.BanProvider;
import dev.rudrecciah.admincore.staffmode.menus.providers.IpBanProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class IpBanMenu {
    public static final SmartInventory IpBanMenu = SmartInventory.builder()
            .provider(new IpBanProvider())
            .id("staffmodeIpBan")
            .title("Permanent IP Ban: Reason")
            .type(InventoryType.CHEST)
            .size(1, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        IpBanMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}
