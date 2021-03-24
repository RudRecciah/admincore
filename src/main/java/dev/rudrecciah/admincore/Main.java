package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.alias.AliasChecker;
import dev.rudrecciah.admincore.announcements.AnnouncementHandler;
import dev.rudrecciah.admincore.ban.Banner;
import dev.rudrecciah.admincore.ban.Tempbanner;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import dev.rudrecciah.admincore.errors.ExceptionHandler;
import dev.rudrecciah.admincore.freeze.FreezeChecker;
import dev.rudrecciah.admincore.freeze.PlayerFreezer;
import dev.rudrecciah.admincore.freeze.PlayerUnfreezer;
import dev.rudrecciah.admincore.inspect.Inspector;
import dev.rudrecciah.admincore.master.MasterCommand;
import dev.rudrecciah.admincore.mute.Muter;
import dev.rudrecciah.admincore.notifs.NotificationHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.report.ReportCommandHandler;
import dev.rudrecciah.admincore.report.meta.ReportMetaCleaner;
import dev.rudrecciah.admincore.serverstatus.ServerStatus;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;
import dev.rudrecciah.admincore.webhook.BanLogger;
import fr.minuskube.inv.InventoryManager;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;
    private InventoryManager invManager;

    @Override
    public void onEnable() {
        plugin = this;
        Boolean configExists = true;
        if(!getDataFolder().exists()) {
            configExists = false;
        }
        saveDefaultConfig();
        if(getConfig().getBoolean("logexceptions")) {
            ExceptionHandler handler = new ExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(handler);
        }
        getServer().getPluginManager().registerEvents(this, this);
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("status").setExecutor(new ServerStatus());
        getCommand("announce").setExecutor(new AnnouncementHandler());
        getCommand("staffmode").setExecutor(new StaffmodeHandler());
        getCommand("admincore").setExecutor(new MasterCommand());
        getCommand("freeze").setExecutor(new PlayerFreezer());
        getCommand("unfreeze").setExecutor(new PlayerUnfreezer());
        getCommand("report").setExecutor(new ReportCommandHandler());
        getCommand("inspect").setExecutor(new Inspector());
        getCommand("ban").setExecutor(new Banner());
        getCommand("mute").setExecutor(new Muter());
        getCommand("aliases").setExecutor(new AliasChecker());
        getCommand("staffnotifications").setExecutor(new NotificationHandler());
        getCommand("tempban").setExecutor(new Tempbanner());
        getLogger().info("Admin Core Enabled");
        invManager = new InventoryManager(this);
        invManager.init();
        if(!configExists) {
            getLogger().info("It seems like you either didn't have a config file for Admincore before running or you've just installed Admincore. Either way, it cannot be used in this state and may generate errors. Your server has been shut down to prevent a potantially fatal error. You should set up your config file before starting your server again. Any errors that you've experienced right now shouldn't be worried about, and should only be investigated if the behavior continues. Thank you!");
            getServer().shutdown();
        }
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
    * TODO killwhenoutdated in config
    * TODO: command testing, discord testing
    * Bugs to fix:
    * none yey
    * TODO: discord integration
    * */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        StaffmodeHandler.hide();
        StaffmodeHandler.clearJoin(e.getPlayer());
        PlayerDataHandler.handlePlayerData(e.getPlayer());
        ReportMetaCleaner.cleanReportMeta(e.getPlayer());
        FreezeChecker.playerFrozen(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        StaffmodeHandler.clear(e.getPlayer(), e);
        if(e.getPlayer().isBanned() || getServer().getBanList(BanList.Type.IP).isBanned(e.getPlayer().getAddress().getHostString())) {
            if(getServer().getBanList(BanList.Type.IP).isBanned(e.getPlayer().getAddress().getHostString())) {
                for(Player p : getServer().getOnlinePlayers()) {
                    if(p.getAddress().getHostString().equalsIgnoreCase(e.getPlayer().getAddress().getHostString())) {
                        p.kickPlayer("Another account on your network was IP banned forever! You probably live together, so go smack them a bit to get them to stop cheating.");
                    }
                }
                PlayerDataHandler.ban(e.getPlayer());
                BanLogger.logBan(e.getPlayer());
            }
            PlayerDataHandler.ban(e.getPlayer());
            BanLogger.logBan(e.getPlayer());
        }
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
        }else if(PlayerDataHandler.muteExpired(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.YELLOW + "You're currently muted! Reason: " + getConfig().getString("staffmode.punishment.mute.reason"));
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().hasMetadata("frozen")) {
            boolean b = DataHandler.getMetaBoolean(e.getPlayer(), "frozen");
            if(b) {
                e.setCancelled(true);
            }
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
