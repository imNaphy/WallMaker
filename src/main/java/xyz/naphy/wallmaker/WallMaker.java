package xyz.naphy.wallmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.naphy.wallmaker.Commands.GiveBricks;
import xyz.naphy.wallmaker.Events.BricksEvents;
import xyz.naphy.wallmaker.Items.Bricks;

import java.util.ArrayList;

public final class WallMaker extends JavaPlugin {

    public static WallMaker plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("givebricks").setExecutor(new GiveBricks());
        getServer().getPluginManager().registerEvents(new BricksEvents(), this);
        Scheduler.start();
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[WallMaker] The plugin has been loaded!"));

    }

    @Override
    public void onDisable() {
        for (Bricks.BricksInfo brick : Bricks.bricks) {
            for (Location tempLoc : brick.bricksList) {
                tempLoc.getBlock().setType(Material.AIR);
            }
            brick.bricksLoc.getBlock().setType(Material.AIR);
        }
        Bricks.bricks = new ArrayList<>();
        Scheduler.stop();
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[WallMaker] The plugin has been unloaded!"));
    }
}
