package xyz.naphy.wallmaker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import xyz.naphy.wallmaker.Items.Bricks;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static xyz.naphy.wallmaker.WallMaker.plugin;

public class Scheduler {

    static Runnable runnable = new Runnable() {

        @Override
        public void run() {
            try {
                for (Bricks.BricksInfo brick : Bricks.bricks) {
                    if (brick.bricksTimer <= 0) {
                        for (Location tempLoc : brick.bricksList) {
                            Bukkit.getScheduler().runTask(plugin, () -> tempLoc.getBlock().setType(Material.AIR));
                        }
                        Bukkit.getScheduler().runTask(plugin, () -> brick.bricksLoc.getBlock().setType(Material.AIR));
                        if (Bricks.bricks.size() <= 1) {
                            Bricks.bricks = new ArrayList<>();
                        }
                        else {
                            Bricks.bricks.remove(brick);
                        }
                    }
                    else {
                        brick.bricksTimer--;
                    }
                }
            } catch (Exception e) {
                //System.err.println(e);
            }
        }
    };

    static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    public static void start() {
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }

    public static void stop() {
        service.shutdownNow();
    }
}
