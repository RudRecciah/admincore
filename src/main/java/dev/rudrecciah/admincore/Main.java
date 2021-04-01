package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.alias.AliasChecker;
import dev.rudrecciah.admincore.announcements.AnnouncementHandler;
import dev.rudrecciah.admincore.ban.Banner;
import dev.rudrecciah.admincore.ban.Tempbanner;
import dev.rudrecciah.admincore.bot.Bot;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import dev.rudrecciah.admincore.errors.ExceptionHandler;
import dev.rudrecciah.admincore.freeze.FreezeChecker;
import dev.rudrecciah.admincore.freeze.PlayerFreezer;
import dev.rudrecciah.admincore.freeze.PlayerUnfreezer;
import dev.rudrecciah.admincore.inspect.Inspector;
import dev.rudrecciah.admincore.master.MasterCommand;
import dev.rudrecciah.admincore.mute.Muter;
import dev.rudrecciah.admincore.mute.Unmuter;
import dev.rudrecciah.admincore.notifs.NotificationHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.punishlogs.PunishmentLogger;
import dev.rudrecciah.admincore.report.ReportCommandHandler;
import dev.rudrecciah.admincore.report.meta.ReportMetaCleaner;
import dev.rudrecciah.admincore.report.reviewer.Reviewer;
import dev.rudrecciah.admincore.serverstatus.ServerStatus;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;
import dev.rudrecciah.admincore.update.ConfigUpdateChecker;
import dev.rudrecciah.admincore.update.PluginUpdateChecker;
import dev.rudrecciah.admincore.webhook.BanLogger;
import dev.rudrecciah.admincore.webhook.ErrorLogger;
import fr.minuskube.inv.InventoryManager;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import javax.security.auth.login.LoginException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;
    private InventoryManager invManager;

    @Override
    public void onEnable() {
        int ps = getServer().getOnlinePlayers().size();
        plugin = this;
        Boolean configExists = true;
        if(!getDataFolder().exists()) {
            configExists = false;
        }
        saveDefaultConfig();
        if(ps > 0) {
            getLogger().severe("A server reload was detected! Never do this! Admincore, along with many other plugins, does not handle reloads gracefully and will generate errors. Stop your server and run your startup script manually to restart it. If you didn't reload, don't worry, this is just a false positive.");
            ErrorLogger.logWarn("A server reload was detected! Never do this! Admincore, along with many other plugins, does not handle reloads gracefully and will generate errors. Stop your server and run your startup script manually to restart it. If you didn't reload, don't worry, this is just a false positive.");
        }
        if(getConfig().getBoolean("bot.enable")) {
            try {
                Bot.enableBot();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        getCommand("unmute").setExecutor(new Unmuter());
        getCommand("reviewreport").setExecutor(new Reviewer());
        invManager = new InventoryManager(this);
        invManager.init();
        if(!configExists) {
            getLogger().info("It seems like you either didn't have a config file for Admincore before running or you've just installed Admincore. Either way, it cannot be used in this state and may generate errors. Your server has been shut down to prevent a potantially fatal error. You should set up your config file before starting your server again. Any errors that you've experienced right now shouldn't be worried about, and should only be investigated if the behavior continues. Thank you!");
            getServer().shutdown();
        }
        PluginUpdateChecker.checkForUpdates();
        ConfigUpdateChecker.checkVersion();
        getLogger().info("Admincore Enabled");
    }

    public InventoryManager getInvManager() {
        return invManager;
    }

    public static void rud() {
        return;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        StaffmodeHandler.hide();
        StaffmodeHandler.clearJoin(e.getPlayer());
        PlayerDataHandler.handlePlayerData(e.getPlayer());
        ReportMetaCleaner.cleanReportMeta(e.getPlayer());
        FreezeChecker.playerFrozen(e.getPlayer());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if(e.getPlayer().hasPermission("bukkit.command.unban.player") && e.getMessage().split(" ")[0].equalsIgnoreCase("pardon") && e.getMessage().split(" ").length > 1 && getServer().getPlayer(e.getMessage().split(" ")[1]) != null) {
            PunishmentLogger.logPardon(getServer().getPlayer(e.getMessage().split(" ")[1]), e.getPlayer().getName());
        }
        if(e.getPlayer().hasPermission("bukkit.command.unban.ip") && e.getMessage().split(" ")[0].equalsIgnoreCase("pardon") && e.getMessage().split(" ").length > 1) {
            PunishmentLogger.logIpPardon(e.getMessage().split(" ")[1], e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        StaffmodeHandler.clear(e.getPlayer(), e);
        if(e.getPlayer().isBanned() || getServer().getBanList(BanList.Type.IP).isBanned(e.getPlayer().getAddress().getHostString())) {
            if(getServer().getBanList(BanList.Type.IP).isBanned(e.getPlayer().getAddress().getHostString())) {
                PlayerDataHandler.ban(e.getPlayer());
                BanLogger.logBan(e.getPlayer());
                for(Player p : getServer().getOnlinePlayers()) {
                    if(p.getAddress().getHostString().equalsIgnoreCase(e.getPlayer().getAddress().getHostString())) {
                        p.kickPlayer("Another account on your network was IP banned forever! You probably live together, so go smack them a bit to get them to stop cheating.");
                    }
                }
                return;
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
        if(PlayerDataHandler.muteExpired(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.YELLOW + "You're currently muted!");
        }else if(e.getPlayer().hasPermission("admincore.staff")) {
            boolean b = StaffmodeHandler.checkStaffmodeChat(e.getPlayer(), e.getMessage());
            if(b) {
                e.setCancelled(true);
            }
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
        if(getConfig().getBoolean("bot.enable")) {
            Bot.disableBot();
        }
        getLogger().info("Admincore Disabled");
    }
}
