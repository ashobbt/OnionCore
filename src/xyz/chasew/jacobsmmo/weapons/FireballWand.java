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

public class FireballWand extends WeaponAbstract{
    public static String weaponNameWithFormat = "fireball_wand";
    public static long weaponUseCooldown = 10;
    public static long secondaryWeaponUseCooldown = 2;

    CooldownManager cooldownManager;

    public FireballWand(Plugin thisPlugin, CooldownManager cooldownManager) {
        this.thisPlugin = thisPlugin;
        this.cooldownManager = cooldownManager;
        this.weaponUseType = WeaponUseType.ANY;
    }


    public void weaponExecute(Player player, PlayerInteractEvent interactEvent) {
        Action eventAction = interactEvent.getAction();

        if(eventAction == Action.LEFT_CLICK_AIR || eventAction == Action.LEFT_CLICK_BLOCK) {

            if(!cooldownManager.isOnMainCoolDown(player.getUniqueId(), weaponUseCooldown)) {
                player.sendMessage(ChatColor.GREEN + "Used item!");
                cooldownManager.setMainCooldown(player.getUniqueId(), System.currentTimeMillis());
            } else {
                player.sendMessage(ChatColor.RED + "There's a " + Long.toString(weaponUseCooldown - cooldownManager.getMainCooldownLeft(player.getUniqueId())) + " second cooldown left on this!");
                return;
            }
            spawnNearFireBall(player, 2);
            spawnNearFireBall(player, 2);
            spawnNearFireBall(player, 3);
            spawnNearFireBall(player, 3);
            spawnNearFireBall(player, 4);
            spawnNearFireBall(player, 4);
            spawnNearFireBall(player, 5);
            spawnNearFireBall(player, 5);


            player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 2, 2);
        } else if(eventAction == Action.RIGHT_CLICK_BLOCK || eventAction == Action.RIGHT_CLICK_AIR) {
            //Check cooldown
            if (!cooldownManager.isOnSecondayCooldown(player.getUniqueId(), secondaryWeaponUseCooldown)) {
                player.sendMessage(ChatColor.GREEN + "Used item!");
                cooldownManager.setSecondaryCooldown(player.getUniqueId(), System.currentTimeMillis());
            } else {
                player.sendMessage(ChatColor.RED + "There's a " + Long.toString(secondaryWeaponUseCooldown - cooldownManager.getSecondaryCooldownLeft(player.getUniqueId())) + " second cooldown left on this!");
                return;
            }

            spawnNearFireBall(player, 2);

            player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 2, 2);
        }
    }

    private void spawnNearFireBall(Player player, int upY) {
        LargeFireball fireball = player.getWorld().spawn(player.getLocation().add(0, upY, 0), LargeFireball.class);
        fireball.setYield(0);
        fireball.setIsIncendiary(true);
        fireball.setVelocity(player.getLocation().getDirection().multiply(2));
    }
}