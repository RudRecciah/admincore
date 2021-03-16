package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.announcements.AnnouncementHandler;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import dev.rudrecciah.admincore.master.MasterCommand;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataLoader;
import dev.rudrecciah.admincore.serverstatus.ServerStatus;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;
import fr.minuskube.inv.InventoryManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;
    private InventoryManager invManager;

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
        getCommand("admincore").setExecutor(new MasterCommand());
        getLogger().info("Admin Core Enabled");
        invManager = new InventoryManager(this);
        invManager.init();
    }

    public InventoryManager getInvManager() {
        return invManager;
    }

    public static void rud() {
        return;
    }

    /*
    * Other things:
    * die
    * be gay
    * do crime
    * pogchamp
    * TODO add something in the console about where to report bugs to, maybe also send that message when an exception is thrown
    * TODO killwhenoutdated in config
    *
    * Bugs to fix:
    * none yey
    *
    * Things you can do in staffmode:
    * /spectate
    * /invsee
    * fly
    * no hitbox (not spectator mode)
    * Invulnerable
    * Either saturation or nohunger task
    *
    * How it will work:
    * When you enter, you go into spectator mode
    * When you leave, you return to the gamemode you were in before
    * TODO: When you punch a player, the hit won't register but you will open a gui with 5 buttons, report player, inventory see, name/uuid etc, full stats (ip, location, etc), and ban
    * TODO: reports and bans have discord webhook integration
    *
    * Report Player/Ban:
    * to be handled by report module
    *
    * Invsee:
    * Opens up a bigger gui with armor, inventory, and hotbar see that changes with the player's changes (might outsource this, idk)
    *
    * Name/uuid stats:
    * Name, UUID
    *
    * Full stats:
    * Opens another gui with name, UUID, IP, all the important stuff from ipqs api, ban history, aliases, anything else i can think of
    *
    * Close:
    * Closes gui
    * */


    /*
    *
    * if(p.hasMetadata("")) {
            List meta = p.getMetadata("");
            FixedMetadataValue metaValue = (FixedMetadataValue) meta.get(0);
            if(metaValue.asBoolean()) {}
        }
    *
    * */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        StaffmodeHandler.hide();
        StaffmodeHandler.clearJoin(e.getPlayer());
        PlayerDataHandler.handlePlayerData(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        StaffmodeHandler.clear(e.getPlayer(), e);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        StaffmodeHandler.interactWithPlayer(e.getPlayer(), e);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if(e.getPlayer().hasPermission("admincore.staff")) {
            boolean b = StaffmodeHandler.checkStaffmodeChat(e.getPlayer(), e.getMessage());
            if(b) {
                e.setCancelled(true);
            }
        }else if(!PlayerDataHandler.muteExpired(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.YELLOW + getConfig().getString("staffmode.punishment.mute.reason"));
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
