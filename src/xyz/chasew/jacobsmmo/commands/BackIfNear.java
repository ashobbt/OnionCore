package xyz.chasew.jacobsmmo.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collection;


public class BackIfNear implements CommandExecutor {
    public void pushBack(int x, int y, int z, Player player) {
        Vector pushBackVector = player.getLocation().getDirection().normalize();
        player.setVelocity(player.getVelocity().add(pushBackVector.multiply(-2)));

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof BlockCommandSender)) {
            sender.sendMessage("Only command blocks can execute this command!");
            return true;
        }
        BlockCommandSender blockSender = (BlockCommandSender) sender;
        if(args.length > 2) {
            return false;
        }
        double radius = Double.parseDouble(args[1]);
        Block commandBlock = blockSender.getBlock();
        Location blockMiddle = commandBlock.getLocation().add(0.5, 0.5, 0.5);

        Collection<Entity> nearbyEntities = blockMiddle.getWorld().getNearbyEntities(blockMiddle, radius, radius, radius);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof Player) {
                Player hitPlayer = (Player) entity;
                pushBack(1, 2, 3, hitPlayer);
            }
        }
        return true;
    }
}
