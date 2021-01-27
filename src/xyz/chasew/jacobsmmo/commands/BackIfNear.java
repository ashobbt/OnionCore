package xyz.chasew.jacobsmmo.commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
//Location expLoc = new Location(player.getWorld(), x, y + 1, z, player.getLocation().getYaw(), player.getLocation().getPitch());
import java.util.Collection;


public class BackIfNear implements CommandExecutor {
    public static void pushAwayFrom(Player player, Location pushFromPoint, Location pushToPoint) {
        Vector pushToVector = pushToPoint.toVector(); // VS
        Vector pushFromVector = pushFromPoint.toVector(); // VP

        Vector pushDirection = pushToVector.subtract(pushFromVector);

        pushDirection.normalize();
        pushDirection.multiply(2.5);

        player.setVelocity(pushDirection);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof BlockCommandSender)) {
            sender.sendMessage("Only command blocks can execute this command!");
            return true;
        }
        BlockCommandSender blockSender = (BlockCommandSender) sender;
        if(args.length < 3) {
            return false;
        }
        double radius = Double.parseDouble(args[0]);
        Block commandBlock = blockSender.getBlock();
        Location blockMiddle = commandBlock.getLocation().add(0.5, 0.5, 0.5);

        Collection<Entity> nearbyEntities = blockMiddle.getWorld().getNearbyEntities(blockMiddle, radius, radius, radius);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof Player) {
                Player hitPlayer = (Player) entity;
                Location pushToPoint = hitPlayer.getLocation();
                Integer blockBack = Integer.parseInt(args[2]);
                switch(args[1]) {
                    case "x":
                        pushToPoint = pushToPoint.add(blockBack, 0, 0);
                        break;
                    case "y":
                        pushToPoint = pushToPoint.add(0, blockBack, 0);
                        break;
                    case "z":
                        pushToPoint = pushToPoint.add(0, 0, blockBack);
                        break;
                    case "-x":
                        pushToPoint = pushToPoint.subtract(blockBack, 0, 0);
                        break;
                    case "-y":
                        pushToPoint = pushToPoint.subtract(0, blockBack, 0);
                        break;
                    case "-z":
                        pushToPoint = pushToPoint.subtract(0, 0, blockBack);
                        break;
                    default:
                        return false;
                }
                pushAwayFrom(hitPlayer, blockMiddle, pushToPoint);
            }
        }
        return true;
    }
}