package dev.rudrecciah.admincore.staffmode.items;

import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import java.util.ArrayList;

public class ItemCreator {

    public static ItemStack createSimpleItemStack(Material type, int amount, String name, String lore) {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta itemMeta = item.getItemMeta();
        if(!name.equals("")) {
            itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + name + "]");
            item.setItemMeta(itemMeta);
        }else{
            itemMeta.setDisplayName("NOREASON");
            item.setItemMeta(itemMeta);
        }
        if(!lore.isEmpty()) {
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + lore));
            item.setItemMeta(itemMeta);
        }
        return item;
    }

    public static ItemStack createBanItem(Material type, int amount, String name, Player target) {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta itemMeta = item.getItemMeta();
        //TODO: lore
        int bans = PlayerDataHandler.getBans(target);
        int mutes = PlayerDataHandler.getMutes(target);
        if(!name.equals("")) {
            itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + name + "]");
            item.setItemMeta(itemMeta);
        }else{
            itemMeta.setDisplayName("NOREASON");
            item.setItemMeta(itemMeta);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "Times Banned: " + bans);
        lore.add(ChatColor.YELLOW + "Times Muted: " + mutes);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

}
