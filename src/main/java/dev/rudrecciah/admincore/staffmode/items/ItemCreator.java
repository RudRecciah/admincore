package dev.rudrecciah.admincore.staffmode.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemCreator {

    public static ItemStack createSimpleItemStack(Material type, int amount, String name, String lore) {
        ItemStack item = new ItemStack(type, amount);
        if(!name.isEmpty()) {
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + name + "]");
            item.setItemMeta(itemMeta);
        }
        if(!lore.isEmpty()) {
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + lore));
            item.setItemMeta(itemMeta);
        }
        return item;
    }

}
