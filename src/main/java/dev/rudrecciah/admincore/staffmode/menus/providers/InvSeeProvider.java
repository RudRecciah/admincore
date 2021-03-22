package dev.rudrecciah.admincore.staffmode.menus.providers;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.staffmode.items.ItemCreator;
import dev.rudrecciah.admincore.staffmode.menus.InvSeeMenu;
import dev.rudrecciah.admincore.staffmode.menus.MainMenu;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class InvSeeProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        if(!player.hasMetadata("staffmodeChecking")) {
            player.sendMessage(ChatColor.YELLOW + "Something went wrong! I have no idea what, but something did. Relog and try again, and if that doesn't help report this bug.");
            return;
        }
        String uuid = DataHandler.getMetaString(player, "staffmodeChecking");
        Player target = plugin.getServer().getPlayer(UUID.fromString(uuid));
        fill(player, target, contents);
        ItemStack reload = ItemCreator.createSimpleItemStack(Material.SUNFLOWER, 1, "RELOAD", "Reload this inventory to see changes!");
        contents.set(0, 7, ClickableItem.of(reload, e -> {
            fill(player, target, contents);
            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "Inventory Reloaded!");
            if(DataHandler.getBoolean(player, "notifs")) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }

    private void fill(Player player, Player target, InventoryContents contents) {
        ItemStack helmet = target.getInventory().getHelmet();
        ItemStack chestplate = target.getInventory().getChestplate();
        ItemStack leggings = target.getInventory().getLeggings();
        ItemStack boots = target.getInventory().getBoots();
        ItemStack offhand = target.getInventory().getItemInOffHand();
        ItemStack pHelmet = ItemCreator.createSimpleItemStack(Material.CHAINMAIL_HELMET, 1, "NO HELMET");
        ItemStack pChestplate = ItemCreator.createSimpleItemStack(Material.CHAINMAIL_CHESTPLATE, 1, "NO CHESTPLATE");
        ItemStack pLeggings = ItemCreator.createSimpleItemStack(Material.CHAINMAIL_LEGGINGS, 1, "NO LEGGINGS");
        ItemStack pBoots = ItemCreator.createSimpleItemStack(Material.CHAINMAIL_BOOTS, 1, "NO BOOTS");
        ItemStack pOffhand = ItemCreator.createSimpleItemStack(Material.SHIELD, 1, "NO OFFHAND ITEM");
        ItemStack pItem = ItemCreator.createSimpleItemStack(Material.STRUCTURE_VOID, 1, "EMPTY SLOT");
        ItemStack back = ItemCreator.createSimpleItemStack(Material.BARRIER, 1, "BACK", "Return to the main menu!");
        if(helmet != null) {
            contents.set(0, 0, ClickableItem.of(helmet, e -> {}));
        }else{
            contents.set(0, 0, ClickableItem.of(pHelmet, e -> {}));
        }
        if(chestplate != null) {
            contents.set(0, 1, ClickableItem.of(chestplate, e -> {}));
        }else{
            contents.set(0, 1, ClickableItem.of(pChestplate, e -> {}));
        }
        if(leggings != null) {
            contents.set(0, 2, ClickableItem.of(leggings, e -> {}));
        }else{
            contents.set(0, 2, ClickableItem.of(pLeggings, e -> {}));
        }
        if(boots != null) {
            contents.set(0, 3, ClickableItem.of(boots, e -> {}));
        }else{
            contents.set(0, 3, ClickableItem.of(pBoots, e -> {}));
        }
        if(offhand.getType() != Material.AIR) {
            contents.set(0, 5, ClickableItem.of(offhand, e -> {}));
        }else{
            contents.set(0, 5, ClickableItem.of(pOffhand, e -> {}));
        }
        contents.set(0, 8, ClickableItem.of(back, e -> {
            InvSeeMenu.closeMenu(player);
            MainMenu.openMenu(player);
        }));
        contents.newIterator("hotbar", SlotIterator.Type.HORIZONTAL, 5, 0);
        contents.newIterator("invR1", SlotIterator.Type.HORIZONTAL, 2, 0);
        contents.newIterator("invR2", SlotIterator.Type.HORIZONTAL, 3, 0);
        contents.newIterator("invR3", SlotIterator.Type.HORIZONTAL, 4, 0);
        for(int i = 0; i < 36; i++) {
            if(8 >= i) {
                SlotIterator sI = contents.iterator("hotbar").get();
                sI.next();
                if(target.getInventory().getItem(i) != null) {
                    sI.set(ClickableItem.of(new ItemStack(target.getInventory().getItem(i)), e ->{}));
                }else{
                    sI.set(ClickableItem.of(pItem, e -> {}));
                }
            }else if(17 >= i) {
                SlotIterator sI = contents.iterator("invR1").get();
                sI.next();
                if(target.getInventory().getItem(i) != null) {
                    sI.set(ClickableItem.of(new ItemStack(target.getInventory().getItem(i)), e ->{}));
                }else{
                    sI.set(ClickableItem.of(pItem, e -> {}));
                }
            }else if(26 >= i) {
                SlotIterator sI = contents.iterator("invR2").get();
                sI.next();
                if(target.getInventory().getItem(i) != null) {
                    sI.set(ClickableItem.of(new ItemStack(target.getInventory().getItem(i)), e ->{}));
                }else{
                    sI.set(ClickableItem.of(pItem, e -> {}));
                }
            }else{
                SlotIterator sI = contents.iterator("invR3").get();
                sI.next();
                if(target.getInventory().getItem(i) != null) {
                    sI.set(ClickableItem.of(new ItemStack(target.getInventory().getItem(i)), e ->{}));
                }else{
                    sI.set(ClickableItem.of(pItem, e -> {}));
                }
            }
        }
    }
}
