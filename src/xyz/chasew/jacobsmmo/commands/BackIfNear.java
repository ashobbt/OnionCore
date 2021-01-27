package xyz.chasew.jacobsmmo.commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;


public class BackIfNear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof BlockCommandSender)) {
            sender.sendMessage("Only command blocks can execute this command!");
            return true;
        }
        BlockCommandSender blockSender = (BlockCommandSender) sender;

        Block commandBlock = blockSender.getBlock();
        Location blockMiddle = commandBlock.getLocation().add(0.5, 0.5, 0.5);
        double radius = 3;
        Collection<Entity> nearbyEntities = blockMiddle.getWorld().getNearbyEntities(blockMiddle, radius, radius, radius);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof Player) {
                Player hitPlayer = (Player) entity;
                hitPlayer.sendMessage("You are near the CommandBlock @" + commandBlock.getX() + "|" + commandBlock.getY() + "|" + commandBlock.getZ());
            }
        }
        return true;
    }
}
