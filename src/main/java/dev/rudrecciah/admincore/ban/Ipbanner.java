package dev.rudrecciah.admincore.ban;

import dev.rudrecciah.admincore.bot.chat.Chat;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.staffmode.menus.BanChoiceMenu;
import dev.rudrecciah.admincore.staffmode.menus.BanMenu;
import dev.rudrecciah.admincore.staffmode.menus.IpBanMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import static dev.rudrecciah.admincore.Main.plugin;

public class Ipbanner implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("This command can only be executed by a player! (Use minecraft:ban or minecraft:ban-ip instead if needed.)");
            return true;
        }
        Player p = (Player) sender;
        if(!plugin.getConfig().getBoolean("staffmode.punishment.ban.ip-ban")) {
            p.sendMessage(ChatColor.YELLOW + "Admincore's IP bans are disabled on this server! (Use /minecraft:ban-ip if needed.)");
            return true;
        }
        if(args.length != 1) {
            p.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
            return true;
        }
        if(!plugin.getServer().getOfflinePlayer(args[0]).hasPlayedBefore()) {
            p.sendMessage(ChatColor.YELLOW + "This player isn't online!");
            return true;
        }
        if(plugin.getServer().getPlayer(args[0]) == p) {
            p.sendMessage(ChatColor.YELLOW + "You can't ban yourself!");
            return true;
        }
        if(!DataHandler.getMetaBoolean((Player) sender, "staffmode")) {
            p.sendMessage(ChatColor.YELLOW + "You must be in staffmode to ban a player!");
            return true;
        }
        p.setMetadata("staffmodeChecking", new FixedMetadataValue(plugin, plugin.getServer().getOfflinePlayer(args[0]).getUniqueId()));
        if(plugin.getConfig().getBoolean("staffmode.punishment.ban.ip-ban")) {
            IpBanMenu.openMenu(p);
        }
        return true;
    }
}
