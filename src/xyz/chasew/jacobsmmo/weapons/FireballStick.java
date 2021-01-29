package xyz.chasew.jacobsmmo.weapons;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.chasew.jacobsmmo.commands.BackIfNear;
import xyz.chasew.jacobsmmo.managers.CooldownManager;
import xyz.chasew.jacobsmmo.recipes.BoomStickRecipe;

import java.util.concurrent.TimeUnit;

public class FireballStick extends WeaponAbstract{
    public static String weaponNameWithFormat = ChatColor.BOLD + "Fireball Stick";
    public static long weaponUseCooldown = 0L;
    public Plugin thisPlugin;

    CooldownManager cooldownManager;

    public FireballStick(Plugin thisPlugin, CooldownManager cooldownManager) {
        this.thisPlugin = thisPlugin;
        this.cooldownManager = cooldownManager;
    }


    public void weaponExecute(Player player, PlayerInteractEvent interactEvent) {
        Action eventAction = interactEvent.getAction();
        if(eventAction == Action.LEFT_CLICK_AIR || eventAction == Action.LEFT_CLICK_BLOCK) {
            if(!cooldownManager.isOnCoolDown(player.getUniqueId(), 10)) {
                player.sendMessage(ChatColor.GREEN + "Used item!");
                cooldownManager.setCooldown(player.getUniqueId(), System.currentTimeMillis());
            } else {
                player.sendMessage(ChatColor.RED + "There's a " + Long.toString(cooldownManager.getCooldownLeft(player.getUniqueId())) + " second cooldown left on this!");
                return;
            }
            DragonFireball fireball = player.getWorld().spawn(player.getLocation(), DragonFireball.class);
            fireball.setYield(0);
            fireball.setVelocity(player.getLocation().getDirection().multiply(0.5));
            player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 2, 2);
        } else if(eventAction == Action.RIGHT_CLICK_BLOCK || eventAction == Action.RIGHT_CLICK_AIR) {
            if(!cooldownManager.isOnCoolDown(player.getUniqueId(), 3)) {
                player.sendMessage(ChatColor.GREEN + "Used item!");
                cooldownManager.setCooldown(player.getUniqueId(), System.currentTimeMillis());
            } else {
                player.sendMessage(ChatColor.RED + "There's a " + Long.toString(cooldownManager.getCooldownLeft(player.getUniqueId())) + " second cooldown left on this!");
                return;
            }
            LargeFireball fireball = player.getWorld().spawn(player.getLocation(), LargeFireball.class);
            fireball.setYield(0);
            fireball.setVelocity(player.getLocation().getDirection().multiply(1.5));

            player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 2, 2);
        }



    }
}