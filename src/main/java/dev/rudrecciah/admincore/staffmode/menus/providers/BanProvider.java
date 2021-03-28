package dev.rudrecciah.admincore.staffmode.menus.providers;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.report.data.ReportDataHandler;
import dev.rudrecciah.admincore.staffmode.items.ItemCreator;
import dev.rudrecciah.admincore.staffmode.menus.MainMenu;
import dev.rudrecciah.admincore.staffmode.menus.BanMenu;
import dev.rudrecciah.admincore.staffmode.menus.TempBanMenu;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class BanProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        if(!player.hasMetadata("staffmodeChecking")) {
            player.sendMessage(ChatColor.YELLOW + "Something went wrong! I have no idea what, but something did. Relog and try again, and if that doesn't help report this bug.");
            return;
        }
        String uuid = DataHandler.getMetaString(player, "staffmodeChecking");
        OfflinePlayer target = plugin.getServer().getOfflinePlayer(UUID.fromString(uuid));
        ItemStack reason1 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.1"), "");
        ItemStack reason2 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.2"), "");
        ItemStack reason3 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.3"), "");
        ItemStack reason4 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.4"), "");
        ItemStack reason5 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.5"), "");
        ItemStack reason6 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.6"), "");
        ItemStack reason7 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.7"), "");
        ItemStack reason8 = ItemCreator.createSimpleItemStack(Material.MAP, 1, plugin.getConfig().getString("staffmode.punishment.ban.reasons.8"), "");
        ItemStack back = ItemCreator.createSimpleItemStack(Material.BARRIER, 1, "BACK", "Return to the main menu!");
        ItemStack[] reasons = {reason1, reason2, reason3, reason4, reason5, reason6, reason7, reason8};
        for(int i = 0; i < 8; i++) {
            if(!reasons[i].getItemMeta().getDisplayName().equalsIgnoreCase("NOREASON")) {
                int finalI = i;
                StringBuilder appeal = new StringBuilder();
                if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.ban.allow-appeals")) {
                    appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                }
                contents.set(0, i, ClickableItem.of(reasons[i], e -> {
                    plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (finalI + 1)) + "\n" + appeal, null, player.getName());
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (finalI + 1)) + " forever!" + appeal.toString());
                    }
                    TempBanMenu.closeMenu(player);
                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + target.getName() + " has been permanently banned!");
                    if(DataHandler.getBoolean(player, "notifs")) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                    }
                }));
            }
        }
        contents.set(0, 8, ClickableItem.of(back, e -> {
            BanMenu.closeMenu(player);
            MainMenu.openMenu(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
