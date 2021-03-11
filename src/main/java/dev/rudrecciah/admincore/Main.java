package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.announcements.AnnouncementHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import dev.rudrecciah.admincore.serverstatus.ServerStatus;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("status").setExecutor(new ServerStatus());
        getCommand("announce").setExecutor(new AnnouncementHandler());
        getCommand("staffmode").setExecutor(new StaffmodeHandler());
        getLogger().info("Admin Core Enabled");
    }

    /*
    * How staffmode will work:
    * Someone does /staffmode, which then executes a method in staffmodetracker to put the staff in a locked down spectator mode and add them to a staffmode array
    * When this method is called, it sends a disconnect message if config is true and executes another method to stop sending the member's packets to other players who are not staff
    * It also notifies other staff that this staff member went into staffmode
    * When someone joins, it executes the method that stops sending packets and if the player isnt staff it wont send packets
    * When someone leaves staffmode, the player will be teleported to spawn and removed from the array, will also start sending its packets to other players again and will send a join message
    * */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        StaffmodeHandler.hide();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        StaffmodeHandler.clear(e.getPlayer());
    }

    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
