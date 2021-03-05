package dev.rudrecciah.admincore;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;
    List<Player> listeningToStaffChat = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Admin Core Enabled");
    }

    /*
    * How staffchat will work:
    * Player uses /staffchat to enter staffchat and see messages and talk to staff
    * Every time a message is sent from a player in staffchat, it gets sent to only other staff in staffchat
    * Every time a message is sent in staffchat, staff who are not in staffchat will get a notification
    * Config Options:
    * Pings for staff (if staff hear a notification as well as see one in chat): false
    * */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("staffchat") && sender instanceof Player) {
            Player p = (Player) sender;
            if(listeningToStaffChat.contains(p)) {
                listeningToStaffChat.remove(p);
                p.sendMessage(ChatColor.YELLOW + "Chat mode:" + ChatColor.BOLD + "Global");
            }else{
                listeningToStaffChat.add(p);
                p.sendMessage(ChatColor.YELLOW + "Chat mode:" + ChatColor.BOLD + "Staff");
            }
        }
        return true;
    }

    @EventHandler
    public void onMessageSend(AsyncPlayerChatEvent e, Player p, String message, Set<Player> playerSet) {
        if(listeningToStaffChat.contains(p)) {
            for(Player player : listeningToStaffChat) {
                player.sendMessage(message);
            }
            List<Player> players = (List) getServer().getOnlinePlayers();
            for(Player player : players) {
                if(player.hasPermission("admincore.staff")) {
                    player.sendMessage(ChatColor.YELLOW + "A staff member has sent a message in the staff chat room. Use /staffchat to join." + "(" + message + ")");
                    FileConfiguration config = plugin.getConfig();
                    if(config.getBoolean("staffchatpings")) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                    }
                }
            }
        }else{
            for(Player player : playerSet) {
                player.sendMessage(message);
            }
        }
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
