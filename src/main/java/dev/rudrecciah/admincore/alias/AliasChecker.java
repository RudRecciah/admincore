package dev.rudrecciah.admincore.alias;

import dev.rudrecciah.admincore.bot.chat.Chat;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.staffmode.grabber.StatsGrabber;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

import static dev.rudrecciah.admincore.Main.plugin;

public class AliasChecker implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("This command can only be executed by a player!");
        }
        Player p = (Player) sender;
        if(args.length != 1) {
            p.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
            return true;
        }
        if(!plugin.getServer().getOfflinePlayer(args[0]).hasPlayedBefore()) {
            p.sendMessage(ChatColor.YELLOW + "This player has never joined the server before!");
            return true;
        }
        p.setMetadata("staffmodeChecking", new FixedMetadataValue(plugin, plugin.getServer().getOfflinePlayer(args[0]).getUniqueId()));
        Player t = (Player) plugin.getServer().getOfflinePlayer(args[0]);
        List<String> aliases = StatsGrabber.grabAliases(t, true);
        p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + t.getName().toUpperCase(Locale.ROOT) + "'S ALIASES]");
        for(String str : aliases) {
            p.sendMessage(str);
        }
        return true;
    }
}
