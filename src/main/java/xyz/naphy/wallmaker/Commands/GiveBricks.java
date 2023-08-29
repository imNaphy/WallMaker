package xyz.naphy.wallmaker.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.naphy.wallmaker.Items.Bricks;

public class GiveBricks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("givebricks")) return true;
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage("You can only give bricks to players!");
                return true;
            }
            else {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Bukkit.getPlayer(args[0]).getInventory().addItem(Bricks.bricks());
                    return true;
                }
            }
        }
        if (!sender.hasPermission("wallmaker.givebricks")) {
            return true;
        }
        else {
            if (args.length == 0) {
                ((Player) sender).getInventory().addItem(Bricks.bricks());
            }
            else {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Bukkit.getPlayer(args[0]).getInventory().addItem(Bricks.bricks());
                }
            }
        }
        return true;
    }
}
