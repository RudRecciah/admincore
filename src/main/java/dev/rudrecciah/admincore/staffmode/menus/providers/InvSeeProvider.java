package dev.rudrecciah.admincore.staffmode.menus.providers;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.staffmode.items.ItemCreator;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        ItemStack helmet = target.getInventory().getHelmet();
        ItemStack chestplate = target.getInventory().getChestplate();
        ItemStack leggings = target.getInventory().getLeggings();
        ItemStack boots = target.getInventory().getBoots();
        ItemStack back = ItemCreator.createSimpleItemStack(Material.BARRIER, 1, "BACK", "Return to the main menu!");
        contents.set(0, 0, ClickableItem.of(helmet, e -> {
            //god why do i need the event lambada i dont even need anything to happen when it gets clicked
        }));
        contents.set(0, 1, ClickableItem.of(chestplate, e -> {
            //god why do i need the event lambada i dont even need anything to happen when it gets clicked
        }));
        contents.set(0, 2, ClickableItem.of(leggings, e -> {
            //god why do i need the event lambada i dont even need anything to happen when it gets clicked
        }));
        contents.set(0, 3, ClickableItem.of(boots, e -> {
            //god why do i need the event lambada i dont even need anything to happen when it gets clicked
        }));
        contents.set(0, 8, ClickableItem.of(back, e -> {
            //aaa
        }));
        for(int i = 0; i < 36; i++) {
            if(target.getInventory().getItem(i) != null) {
                contents.set(0, 0, ClickableItem.of(new ItemStack(target.getInventory().getItem(i)), e -> {
                    //hello i see you with your fancy maven decompiler
                }));
            }else{
                //TODO: finish this
                //TODO: offhand
                //TODO: placeholder item
            }
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
