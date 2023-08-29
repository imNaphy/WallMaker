package xyz.naphy.wallmaker.Items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Bricks {

    public static class BricksInfo {
        public Location bricksLoc;
        public int bricksTimer;
        public List<Location> bricksList;
    }

    public static List<BricksInfo> bricks = new ArrayList<>();

    public static ItemStack bricks() {
        ItemStack item = new ItemStack(Material.BRICKS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§lʙʀɪᴄᴋs");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8ɪᴛᴇᴍ ᴄᴀʀᴇ ᴘᴏᴀᴛᴇ ғɪ ᴘʟᴀsᴀᴛ ɪɴ ʟᴜᴍᴇ");
        lore.add("§8ᴘʟᴀsᴇᴀᴢᴀ ᴜɴ ᴢɪᴅ ᴅᴇ ᴄᴀʀᴀᴍɪᴢɪ");
        lore.add("§8ᴅɪsᴘᴀʀᴇ ᴅᴜᴘᴀ ᴏ ᴘᴇʀɪᴏᴀᴅᴀ ᴅᴇ ᴛɪᴍᴘ");
        lore.add(" ");
        lore.add("§8ᴛɪᴍᴘ ᴅᴇ ᴇxɪsᴛᴇɴᴛᴀ: §c§n2 ᴍɪɴᴜᴛᴇ");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
