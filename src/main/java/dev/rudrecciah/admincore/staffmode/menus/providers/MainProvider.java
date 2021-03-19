package dev.rudrecciah.admincore.staffmode.menus.providers;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.staffmode.grabber.StatsGrabber;
import dev.rudrecciah.admincore.staffmode.items.ItemCreator;
import dev.rudrecciah.admincore.staffmode.menus.BanChoiceMenu;
import dev.rudrecciah.admincore.staffmode.menus.BanMenu;
import dev.rudrecciah.admincore.staffmode.menus.MainMenu;
import dev.rudrecciah.admincore.staffmode.menus.TempBanMenu;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        ItemStack report = ItemCreator.createSimpleItemStack(Material.WRITABLE_BOOK, 1, "REPORT " + target.getName().toUpperCase(Locale.ROOT), "Report " + target.getName() + " for an infraction!");
        ItemStack mute = ItemCreator.createSimpleItemStack(Material.MAP, 1, "MUTE " + target.getName().toUpperCase(Locale.ROOT), "Mute " + target.getName() + " for a chat infraction!");
        ItemStack ban = ItemCreator.createSimpleItemStack(Material.FIREWORK_STAR, 1, "BAN " + target.getName().toUpperCase(Locale.ROOT) + " PERMANENTLY", "Ban " + target.getName() + " permanently for an infraction!");
        ItemStack tempban = ItemCreator.createSimpleItemStack(Material.FIRE_CHARGE, 1, "BAN " + target.getName().toUpperCase(Locale.ROOT) + " FOR 30 DAYS", "Ban " + target.getName() + " for 30 days for an infraction!");
        ItemStack head = itemFromUuid(UUID.fromString(uuid));
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + target.getName().toUpperCase(Locale.ROOT) + "]");
        //TODO: aliases for head
//        headMeta.setLore(StatsGrabber.grabAliases(target));
        headMeta.setLore(StatsGrabber.grabAliases(target));
        head.setItemMeta(headMeta);
        ItemStack inventory = ItemCreator.createSimpleItemStack(Material.ENDER_CHEST, 1, "SEE " + target.getName().toUpperCase(Locale.ROOT) + "'S INVENTORY", "See " + target.getName() + "'s inventory to verify their items' legitimacy!");
        ItemStack stats = ItemCreator.createSimpleItemStack(Material.NAME_TAG, 1, "SEE " + target.getName().toUpperCase(Locale.ROOT) + "'S STATISTICS", "See " + target.getName() + "'s statistics in detail to verify their legitimacy!");
        ItemStack pastBans = ItemCreator.createBanItem(Material.REDSTONE, 1, "REVIEW " + target.getName().toUpperCase(Locale.ROOT) + "'S BAN HISTORY", target);
        ItemStack close = ItemCreator.createSimpleItemStack(Material.BARRIER, 1, "EXIT", "Exit this menu!");
        contents.set(0, 0, ClickableItem.of(report, e -> {
            MainMenu.closeMenu(player);
            //TODO: open report menu
        }));
        contents.set(0, 1, ClickableItem.of(mute, e -> {
            int muteLength = plugin.getConfig().getInt("staffmode.punishment.mute.mute-length");
            long muteEnd = System.currentTimeMillis() + (muteLength * 60000L);
            PlayerDataHandler.mute(target, muteEnd);
            MainMenu.closeMenu(player);
            player.sendMessage(ChatColor.YELLOW + target.getName() + " has been muted for " + muteLength + " minutes!");
            target.sendMessage(ChatColor.YELLOW + "You have been muted for 30 minutes! Reason: " + plugin.getConfig().getString("staffmode.punishment.mute.reason"));
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
        contents.set(0, 4, ClickableItem.of(head, e -> {
            //TODO: nothing bish
        }));
        contents.set(0, 5, ClickableItem.of(inventory, e -> {
            MainMenu.closeMenu(player);
            //TODO: open invsee menu
        }));
        contents.set(0, 6, ClickableItem.of(stats, e -> {
            StatsGrabber.grabStats(target);
            //TODO: set lore
        }));
        contents.set(0, 7, ClickableItem.of(pastBans, e -> {
            //TODO: nothing bish
        }));
        contents.set(0, 8, ClickableItem.of(close, e -> {
            MainMenu.closeMenu(player);
        }));

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        //TODO: if player is banned, close all guis for this player
        //TODO: if player leaves, close gui (maybe)
    }
}
