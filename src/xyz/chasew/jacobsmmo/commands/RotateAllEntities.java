package xyz.chasew.jacobsmmo.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import xyz.chasew.jacobsmmo.utilities.NBTEditor;

import java.util.concurrent.TimeUnit;


public class RotateAllEntities implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof BlockCommandSender)) {
            return true;
        }
        BlockCommandSender blockCommandSender = (BlockCommandSender) commandSender;
        World inWorld = blockCommandSender.getBlock().getWorld();
        if(args.length != 3) {
            return false;
        }
        for (Entity worldEntity : inWorld.getEntities()) {

            if(NBTEditor.hasTag(worldEntity, args[0])) {
                float entityYaw = worldEntity.getLocation().getYaw();
                float entityPitch = worldEntity.getLocation().getPitch();
                try {
                    float yawIntervalInt = Float.parseFloat(args[1]);
                    float pitchIntervalInt = Float.parseFloat(args[2]);
                    if(worldEntity instanceof Player) {
                        Player playerWorldEntity = (Player) worldEntity;
                        Location turnedLocation = playerWorldEntity.getLocation();
                        turnedLocation.setYaw(entityYaw + yawIntervalInt);
                        turnedLocation.setPitch(entityPitch + pitchIntervalInt);
                        playerWorldEntity.teleport(turnedLocation);
                        return true;
                    }

                    worldEntity.setRotation(entityYaw + yawIntervalInt, entityPitch + pitchIntervalInt);
                } catch (NumberFormatException e) {
                    return true;
                }

            }
        }


        return true;
    }
}
