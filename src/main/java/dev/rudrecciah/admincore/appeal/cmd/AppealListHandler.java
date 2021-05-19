package dev.rudrecciah.admincore.appeal.cmd;

import dev.rudrecciah.admincore.data.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static dev.rudrecciah.admincore.Main.plugin;

public class AppealListHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Boolean console = false;
        if(sender instanceof ConsoleCommandSender) {
            console = true;
        }
        if(!console && !DataHandler.getMetaBoolean((Player) sender, "staffmode")) {
            sender.sendMessage(ChatColor.YELLOW + "You must be in staffmode to view appeals!");
            return true;
        }
        File dir = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String[] files = dir.list();
        if(files.length == 0) {
            if(console) {
                plugin.getLogger().info("There are no open appeals!");
                return true;
            }
            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + "There are no open appeals!");
            return true;
        }
        if(console) {
            plugin.getLogger().info("[OPEN APPEALS]");
        }else{
            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[OPEN APPEALS]");
        }
        for(String name : files) {
            if(console) {
                plugin.getLogger().info("ID: " + name.replaceAll(".yml", ""));
            }else{
                sender.sendMessage(ChatColor.YELLOW + "ID: " + name.replaceAll(".yml", ""));
            }
        }
        return true;
    }
}
