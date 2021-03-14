package dev.rudrecciah.admincore.staffmode.menus.providers;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.staffmode.menus.MainMenu;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static dev.rudrecciah.admincore.Main.plugin;

import static dev.dbassett.skullcreator.SkullCreator.itemFromUuid;

public class MainProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        if(!player.hasMetadata("staffmodeChecking")) {
            player.sendMessage(ChatColor.YELLOW + "Something went wrong! I have no idea what, but something did. Relog and try again, and if that doesn't help report this bug.");
            return;
        }
        String uuid = DataHandler.getMetaString(player, "staffmodeChecking");
        Player target = plugin.getServer().getPlayer(UUID.fromString(uuid));
        ItemStack punishment = new ItemStack(Material.NAME_TAG);
        ItemMeta punishmentMeta = punishment.getItemMeta();
        punishmentMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[PUNISH]");
        punishmentMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Report or ban this player!"));
        punishment.setItemMeta(punishmentMeta);
        ItemStack inventory = new ItemStack(Material.ENDER_CHEST);
        ItemMeta inventoryMeta = inventory.getItemMeta();
        inventoryMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[INVENTORY]");
        inventoryMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "See this player's inventory!"));
        inventory.setItemMeta(inventoryMeta);
        ItemStack head = itemFromUuid(UUID.fromString(uuid));
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + target.getName().toUpperCase(Locale.ROOT) + "]");
        headMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "UUID: " + uuid));
        head.setItemMeta(headMeta);
        ItemStack stats = new ItemStack(Material.BOOK);
        ItemMeta statsMeta = stats.getItemMeta();
        statsMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[STATS]");
        statsMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "add statistics here"));
        stats.setItemMeta(statsMeta);
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[EXIT]");
        closeMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Exit this menu!"));
        close.setItemMeta(closeMeta);
        contents.set(0, 0, ClickableItem.of(punishment, e -> {
            
        }));
        contents.set(0, 1, ClickableItem.of(inventory, e -> {

        }));
        contents.set(0, 2, ClickableItem.of(head, e -> {

        }));
        contents.set(0, 3, ClickableItem.of(stats, e -> {

        }));
        contents.set(0, 4, ClickableItem.of(close, e -> {
            MainMenu.closeMenu(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
