package dev.rudrecciah.admincore.staffmode.menus.providers;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.report.menu.ReportMenu;
import dev.rudrecciah.admincore.staffmode.grabber.StatsGrabber;
import dev.rudrecciah.admincore.staffmode.items.ItemCreator;
import dev.rudrecciah.admincore.staffmode.menus.*;
import dev.rudrecciah.admincore.webhook.MuteLogger;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

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
        if(target == null) {
            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "This player is offline, you cannot inspect them!");
            if(DataHandler.getBoolean(player, "notifs")) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            return;
        }
        ItemStack report = ItemCreator.createSimpleItemStack(Material.WRITABLE_BOOK, 1, "REPORT " + target.getName().toUpperCase(Locale.ROOT), "Report " + target.getName() + " for an infraction!");
        ItemStack mute = ItemCreator.createSimpleItemStack(Material.MAP, 1, "MUTE " + target.getName().toUpperCase(Locale.ROOT), "Mute " + target.getName() + " for a chat infraction!");
        ItemStack ban = ItemCreator.createSimpleItemStack(Material.FIREWORK_STAR, 1, "BAN " + target.getName().toUpperCase(Locale.ROOT) + " PERMANENTLY", "Ban " + target.getName() + " permanently for an infraction!");
        ItemStack tempban = ItemCreator.createSimpleItemStack(Material.FIRE_CHARGE, 1, "BAN " + target.getName().toUpperCase(Locale.ROOT) + " FOR 30 DAYS", "Ban " + target.getName() + " for 30 days for an infraction!");
        ItemStack head = itemFromUuid(UUID.fromString(uuid));
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + target.getName().toUpperCase(Locale.ROOT) + "]");
        headMeta.setLore(StatsGrabber.grabAliases(target));
        head.setItemMeta(headMeta);
        ItemStack inventory = ItemCreator.createSimpleItemStack(Material.ENDER_CHEST, 1, "SEE " + target.getName().toUpperCase(Locale.ROOT) + "'S INVENTORY", "See " + target.getName() + "'s inventory to verify their items' legitimacy!");
        ItemStack stats = ItemCreator.createSimpleItemStack(Material.NAME_TAG, 1, "SEE " + target.getName().toUpperCase(Locale.ROOT) + "'S STATISTICS", "See " + target.getName() + "'s statistics in detail to verify their legitimacy!");
        ItemStack pastBans = ItemCreator.createBanItem(Material.REDSTONE, 1, "REVIEW " + target.getName().toUpperCase(Locale.ROOT) + "'S BAN HISTORY", target);
        ItemStack close = ItemCreator.createSimpleItemStack(Material.BARRIER, 1, "EXIT", "Exit this menu!");
        contents.set(0, 0, ClickableItem.of(report, e -> {
            MainMenu.closeMenu(player);
            player.setMetadata("reporting", new FixedMetadataValue(plugin, String.valueOf(target.getUniqueId())));
            ReportMenu.openMenu(player);
        }));
        contents.set(0, 1, ClickableItem.of(mute, e -> {
            MainMenu.closeMenu(player);
            MuteMenu.openMenu(player);
        }));
        contents.set(0, 2, ClickableItem.of(ban, e -> {
            MainMenu.closeMenu(player);
            if(plugin.getConfig().getBoolean("staffmode.punishment.ban.ip-ban")) {
                MainMenu.closeMenu(player);
                BanChoiceMenu.openMenu(player);
            }else{
                MainMenu.closeMenu(player);
                BanMenu.openMenu(player);
            }
        }));
        contents.set(0, 3, ClickableItem.of(tempban, e -> {
            MainMenu.closeMenu(player);
            TempBanMenu.openMenu(player);
        }));
        contents.set(0, 4, ClickableItem.of(head, e -> {}));
        contents.set(0, 5, ClickableItem.of(inventory, e -> {
            MainMenu.closeMenu(player);
            InvSeeMenu.openMenu(player);
        }));
        contents.set(0, 6, ClickableItem.of(stats, e -> {
            if(player.hasPermission("admincore.stats")) {
                ItemMeta sMeta = stats.getItemMeta();
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "Loading statistics. . .");
                if(DataHandler.getBoolean(player, "notifs")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
                sMeta.setLore(StatsGrabber.grabStats(player, target));
                stats.setItemMeta(sMeta);
                contents.set(0, 6, ClickableItem.empty(stats));
            }else{
                ItemMeta sMeta = stats.getItemMeta();
                sMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "You don't have permission to check a player's statistics!"));
                stats.setItemMeta(sMeta);
                contents.set(0, 6, ClickableItem.empty(stats));
            }
        }));
        contents.set(0, 7, ClickableItem.of(pastBans, e -> {}));
        contents.set(0, 8, ClickableItem.of(close, e -> {
            MainMenu.closeMenu(player);
        }));

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
