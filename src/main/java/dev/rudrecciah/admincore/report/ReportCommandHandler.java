package dev.rudrecciah.admincore.report;

import dev.rudrecciah.admincore.report.menu.ReportMenu;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportCommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().severe("This command can only be executed by a player!");
            return true;
        }
        if(args.length != 1) {
            sender.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
            return true;
        }
        if(!plugin.getServer().getOfflinePlayer(args[0]).hasPlayedBefore()) {
            sender.sendMessage(ChatColor.YELLOW + "This player has never played before!");
            return true;
        }
        if(sender.getName().equalsIgnoreCase(args[0])) {
            sender.sendMessage(ChatColor.YELLOW + "You can't report yourself!");
            return true;
        }
        Player p = (Player) sender;
        OfflinePlayer t = plugin.getServer().getOfflinePlayer(args[0]);
        p.setMetadata("reporting", new FixedMetadataValue(plugin, String.valueOf(t.getUniqueId())));
        ReportMenu.openMenu(p);
        return true;
    }
}
