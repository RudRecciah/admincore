package dev.rudrecciah.admincore.report;

import dev.rudrecciah.admincore.report.menu.ReportMenu;
import org.bukkit.ChatColor;
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
        if(sender.getName().equalsIgnoreCase(args[0])) {
            sender.sendMessage(ChatColor.YELLOW + "You can't report yourself!");
            return true;
        }
        if(args.length != 1 || plugin.getServer().getPlayer(args[0]) == null) {
            return false;
        }
        Player p = (Player) sender;
        Player t = plugin.getServer().getPlayer(args[0]);
        p.setMetadata("reporting", new FixedMetadataValue(plugin, String.valueOf(t.getUniqueId())));
        ReportMenu.openMenu(p);
        return true;
    }
}
