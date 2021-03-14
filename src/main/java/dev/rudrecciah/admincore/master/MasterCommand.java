package dev.rudrecciah.admincore.master;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static dev.rudrecciah.admincore.Main.plugin;

public class MasterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getLogger().info("oi moate da botum geah");
        return true;
    }
}
