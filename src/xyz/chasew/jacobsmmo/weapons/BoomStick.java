package xyz.chasew.jacobsmmo.weapons;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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

public class BoomStick extends WeaponAbstract{
    public static String weaponNameWithFormat = ChatColor.BOLD + "Boom Stick";
    public static long weaponUseCooldown = 10;
    public Plugin thisPlugin;

    CooldownManager cooldownManager;

    public BoomStick(Plugin thisPlugin, CooldownManager cooldownManager) {
        this.thisPlugin = thisPlugin;
        this.cooldownManager = cooldownManager;
    }


    public void weaponExecute(Player player, PlayerInteractEvent interactEvent) {
        if(!cooldownManager.isOnCoolDown(player.getUniqueId(), weaponUseCooldown)) {
            player.sendMessage(ChatColor.GREEN + "Used item!");
            cooldownManager.setCooldown(player.getUniqueId(), System.currentTimeMillis());
        } else {
            player.sendMessage(ChatColor.RED + "There's a " + Long.toString(cooldownManager.getCooldownLeft(player.getUniqueId())) + " second cooldown on this!");
            return;
        }
        Action eventAction = interactEvent.getAction();
        if (eventAction == Action.LEFT_CLICK_AIR || eventAction == Action.LEFT_CLICK_BLOCK) {
            interactEvent.setCancelled(true);
            return;
        }
        Fireball fireball = player.getWorld().spawn(player.getLocation(), Fireball.class);
        fireball.setVelocity(player.getLocation().getDirection().multiply(3));
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 5, 5);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 175, 100));
        BackIfNear.pushAwayFrom(player, player.getLocation(), player.getLocation().add(0, 20, 0), 2.5);
    }
}
