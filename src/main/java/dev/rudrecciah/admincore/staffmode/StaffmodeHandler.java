package dev.rudrecciah.admincore.staffmode;

import dev.rudrecciah.admincore.data.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class StaffmodeHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender,  Command command,  String label,  String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasMetadata("staffmode")) {
                List staffmode = p.getMetadata("staffmode");
                FixedMetadataValue staffmodeValue = (FixedMetadataValue) staffmode.get(0);
                if(staffmodeValue.asBoolean()) {
                    update((Player) sender, false);
                }else{
                    update((Player) sender, true);
                }
            }else{
                update((Player) sender, true);
            }
        }else{
            plugin.getLogger().severe("This command can only be executed by a player!");
        }
        return true;
    }

    public void update(Player p, boolean toggle) {
        if(toggle) {
            p.setMetadata("staffmode", new FixedMetadataValue(plugin, true));
            hide();
            if(plugin.getConfig().getBoolean("pseudodisconnect.send-message"))  {
                String configMessage = plugin.getConfig().getString("pseudodisconnect.message");
                if(!configMessage.isEmpty()) {
                    configMessage = configMessage.replaceAll("%p%", p.getName());
                    configMessage = ChatColor.translateAlternateColorCodes('&', configMessage);
                    for(Player player : plugin.getServer().getOnlinePlayers()) {
                        player.sendMessage(configMessage);
                    }
                }else{
                    p.sendMessage(ChatColor.YELLOW + "There is no pseudo disconnect message in config.yml, but pseudo disconnect messages are set to send. Please aleart an admin about this issue! For now, you can enter staffmode, but no disconnect message will be sent. This may compromise your secrecy.");
                }
                for(Player player : plugin.getServer().getOnlinePlayers()) {
                    if(p != player && player.hasPermission("admincore.staff")) {
                        player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL]" + ChatColor.YELLOW + "" + p.getName() + " has entered staffmode!");
                        if(DataHandler.getBoolean(player, "notifs")) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        }
                    }
                }
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL]" + ChatColor.YELLOW + " You have entered staffmode!");
                if(DataHandler.getBoolean(p, "notifs")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }
        }else{
            p.setMetadata("staffmode", new FixedMetadataValue(plugin, false));
            show(p);
            if(plugin.getConfig().getBoolean("pseudojoin.send-message")) {
                String configMessage = plugin.getConfig().getString("pseudojoin.message");
                if(!configMessage.isEmpty()) {
                    configMessage = configMessage.replaceAll("%p%", p.getName());
                    configMessage = ChatColor.translateAlternateColorCodes('&', configMessage);
                    for(Player player : plugin.getServer().getOnlinePlayers()) {
                        player.sendMessage(configMessage);
                    }
                }else{
                    p.sendMessage(ChatColor.YELLOW + "There is no pseudo join message in config.yml, but pseudo join messages are set to send. Please aleart an admin about this issue! For now, you can leave staffmode, but no join message will be sent. This may compromise your secrecy.");
                }
                for(Player player : plugin.getServer().getOnlinePlayers()) {
                    if(p != player && player.hasPermission("admincore.staff")) {
                        player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL]" + ChatColor.YELLOW + "" + p.getName() + " has left staffmode!");
                        if(DataHandler.getBoolean(player, "notifs")) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        }
                    }
                }
            }
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL]" + ChatColor.YELLOW + " You have left staffmode!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
        }
    }

    public static void hide() {
        for(Player playerToCheck : plugin.getServer().getOnlinePlayers()) {
            if(playerToCheck.hasMetadata("staffmode")) {
                List staffmode = playerToCheck.getMetadata("staffmode");
                FixedMetadataValue staffmodeValue = (FixedMetadataValue) staffmode.get(0);
                if(staffmodeValue.asBoolean()) {
                    for(Player player : plugin.getServer().getOnlinePlayers()) {
                        if(!player.hasPermission("admincore.staff")) {
                            player.hidePlayer(plugin, playerToCheck);
                        }
                    }
                }
            }
        }
    }

    //TODO: Use entity#setMetadata to store staff or not

    public void show(Player p) {
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            player.showPlayer(plugin, p);
        }
    }

    public static void clear(Player p) {
        if(p.hasMetadata("staffmode")) {
            p.removeMetadata("staffmode", plugin);
        }
    }
}
