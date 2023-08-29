package xyz.naphy.wallmaker.Events;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.naphy.wallmaker.Items.Bricks;

import java.util.ArrayList;
import java.util.List;

import static xyz.naphy.wallmaker.WallMaker.plugin;

public class BricksEvents implements Listener {

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event) {

        if (!event.getItemInHand().isSimilar(Bricks.bricks())) return;
        Location loc = new Location(event.getBlockPlaced().getWorld(), event.getBlockPlaced().getX(), event.getBlockPlaced().getY(), event.getBlockPlaced().getZ());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(loc));
        Boolean temp = true;
        for (ProtectedRegion pr : set) {
            if (pr.getFlag(Flags.PVP) != null) {
                if (pr.getFlag(Flags.PVP).equals(StateFlag.State.DENY)) temp = false;
            }
        }
        if (temp) {
            event.setCancelled(true);
            float yaw = event.getPlayer().getLocation().getYaw();
            List<Location> blockLocations = new ArrayList<>();
            if ((yaw >= 135 && yaw <= 180) || (yaw >= -180 && yaw <= -135) || (yaw >= -45 && yaw <= 0) || (yaw >= 0 && yaw <= 45)) {
                for (int i = 0; i <= 3; ++i) {
                    for (int j = -2; j <= 2; ++j) {
                        Location tempLoc = new Location(loc.getWorld(), loc.getX() + j, loc.getY() + i, loc.getZ());
                        if (tempLoc.getBlock().getType().equals(Material.AIR)) {
                            blockLocations.add(tempLoc);
                            Bukkit.getScheduler().runTask(plugin, () -> tempLoc.getBlock().setType(Material.BRICKS));
                        }
                    }
                }
            }
            else {
                for (int i = 0; i <= 3; ++i) {
                    for (int j = -3; j <= 3; ++j) {
                        Location tempLoc = new Location(loc.getWorld(), loc.getX(), loc.getY() + i, loc.getZ() + j);
                        if (tempLoc.getBlock().getType().equals(Material.AIR)) {
                            blockLocations.add(tempLoc);
                            Bukkit.getScheduler().runTask(plugin, () -> tempLoc.getBlock().setType(Material.BRICKS));
                        }
                    }
                }
            }
            Bukkit.getScheduler().runTask(plugin, () -> loc.getBlock().setType(Material.BRICKS));
            Bricks.BricksInfo tempBricks = new Bricks.BricksInfo();
            tempBricks.bricksLoc = loc;
            tempBricks.bricksTimer = 120;
            tempBricks.bricksList = blockLocations;
            Bricks.bricks.add(tempBricks);
            event.getItemInHand().setAmount(event.getItemInHand().getAmount() - 1);
        }
    }

}
