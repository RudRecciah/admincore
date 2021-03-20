package dev.rudrecciah.admincore.staffmode.menus.providers;

import dev.rudrecciah.admincore.staffmode.items.ItemCreator;
import dev.rudrecciah.admincore.staffmode.menus.*;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static dev.rudrecciah.admincore.Main.plugin;

public class BanChoiceProvider implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack ip = ItemCreator.createSimpleItemStack(Material.BLAZE_ROD, 1, "IP BAN", "Ban this player's IP address for an infraction!");
        ItemStack acc = ItemCreator.createSimpleItemStack(Material.FEATHER, 1, "PLAYER BAN", "Ban this player for an infraction!");
        ItemStack exit = ItemCreator.createSimpleItemStack(Material.BARRIER, 1, "EXIT", "Exit this menu!");
        contents.set(0, 0, ClickableItem.of(ip, e ->{
            BanChoiceMenu.closeMenu(player);
            IpBanMenu.openMenu(player);
        }));
        contents.set(0, 1, ClickableItem.of(acc, e ->{
            BanChoiceMenu.closeMenu(player);
            BanMenu.openMenu(player);
        }));
        contents.set(0, 8, ClickableItem.of(exit, e ->{
            BanChoiceMenu.closeMenu(player);
            MainMenu.openMenu(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
