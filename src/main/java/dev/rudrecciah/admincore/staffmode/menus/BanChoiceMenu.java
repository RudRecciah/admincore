package dev.rudrecciah.admincore.staffmode.menus;

import dev.rudrecciah.admincore.staffmode.menus.providers.BanChoiceProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import static dev.rudrecciah.admincore.Main.plugin;

public class BanChoiceMenu {
    public static final SmartInventory BanChoiceMenu = SmartInventory.builder()
            .provider(new BanChoiceProvider())
            .id("staffmodeBanChoice")
            .title("Permanent Ban: Type")
            .type(InventoryType.CHEST)
            .size(1, 9)
            .closeable(true)
            .manager(plugin.getInvManager())
            .build();

    public static void openMenu(Player p) {
        BanChoiceMenu.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }
}
