package dev.rudrecciah.admincore;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
    public List<Player> mutedFromStaffNotifs = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Admin Core Enabled");
    }

    //TODO: make staffchat pings toggleable for each staff member rather than the whole plugin

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("staffchat") && sender instanceof Player) {
            Player p = (Player) sender;
            if(listeningToStaffChat.contains(p)) {
                listeningToStaffChat.remove(p);
                p.sendMessage(ChatColor.YELLOW + "Chat mode: " + ChatColor.BOLD + "Global");
            }else{
                listeningToStaffChat.add(p);
                p.sendMessage(ChatColor.YELLOW + "Chat mode: " + ChatColor.BOLD + "Staff");
            }
        }else if(command.getName().equalsIgnoreCase("staffnotifications") && sender instanceof Player) {
            Player p = (Player) sender;
            if(mutedFromStaffNotifs.contains(p)) {
                mutedFromStaffNotifs.remove(p);
                p.sendMessage(ChatColor.YELLOW + "Notifications: " + ChatColor.BOLD + "ON");
            }else{
                mutedFromStaffNotifs.add(p);
                p.sendMessage(ChatColor.YELLOW + "Notifications: " + ChatColor.BOLD + "OFF");
            }
        }else if(sender instanceof ConsoleCommandSender){
            getLogger().severe("This command can only be executed by a player!");
        }
        return true;
    }

    @EventHandler
    public void onMessageSend(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        Set<Player> playerSet = e.getRecipients();
        if(listeningToStaffChat.contains(p)) {
            playerSet.clear();
            playerSet.addAll(listeningToStaffChat);
            List<Player> players = (List) getServer().getOnlinePlayers();
            for(Player player : players) {
                if(player.hasPermission("admincore.staff") && !listeningToStaffChat.contains(player) && !mutedFromStaffNotifs.contains(player)) {
                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.YELLOW + p.getName() + ": " + message);
                    FileConfiguration config = plugin.getConfig();
                    if(config.getBoolean("staffchatpings")) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                    }
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
