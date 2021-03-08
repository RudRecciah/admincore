package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Admin Core Enabled");
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("staffchat") && sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length > 0) {
                StringBuilder message = new StringBuilder();
                for(String arg : args) {
                    message.append(arg + " ");
                }
                List<Player> players = (List) getServer().getOnlinePlayers();
                for(Player player : players) {
                    if(player.hasPermission("admincore.staff")) {
                        player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.YELLOW + p.getName() + ": " + message);
                        FileConfiguration config = plugin.getConfig();
                        if(DataLoader.get().getBoolean("players." + player.getUniqueId() + ".notifs") && p != player) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        }
                    }
                }
            }else{
                return false;
            }
        }else if(sender instanceof ConsoleCommandSender) {
            getLogger().severe("This command can only be executed by a player!");
        }
        return true;
    }

//    @EventHandler
//    public void onMessageSend(AsyncPlayerChatEvent e) {
//        Player p = e.getPlayer();
//        String message = e.getMessage();
//        Set<Player> playerSet = e.getRecipients();
//        if(listeningToStaffChat.contains(p)) {
//            playerSet.clear();
//            playerSet.addAll(listeningToStaffChat);
//            List<Player> players = (List) getServer().getOnlinePlayers();
//            for(Player player : players) {
//                if(player.hasPermission("admincore.staff") && !listeningToStaffChat.contains(player)) {
//                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.YELLOW + p.getName() + ": " + message);
//                    FileConfiguration config = plugin.getConfig();
//                    if(DataLoader.get().getBoolean("players." + player.getUniqueId() + ".notifs")) {
//                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
//                    }
//                }
//            }
//        }
//    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("admincore.staff")) {
            DataHandler.handlePlayerData(p);
        }
    }



    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
