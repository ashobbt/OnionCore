package xyz.chasew.jacobsmmo.weapons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.chasew.jacobsmmo.managers.CooldownManager;

public abstract class WeaponAbstract {
    public static String weaponNameWithFormat;
    public static long weaponUseCooldown;
    public static long secondaryWeaponUseCooldown;
    public WeaponUseType weaponUseType;
    public Plugin thisPlugin;

    /*
    public boolean checkCool(Player player, Plugin thisPlugin, Integer weaponUseCooldown) {
        if(weaponUseCooldown > 0) {
            CooldownManager cooldownManager = new CooldownManager();
            int timeLeft = cooldownManager.getCooldown(player.getUniqueId());
            //If the cooldown has expired
            if(timeLeft == 0){
                //Use the feature
                player.sendMessage(ChatColor.GREEN + "Weapon used!");
                //Start the countdown task
                cooldownManager.setCooldown(player.getUniqueId(), CooldownManager.DEFAULT_COOLDOWN);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        int timeLeft = cooldownManager.getCooldown(player.getUniqueId());
                        cooldownManager.setCooldown(player.getUniqueId(), --timeLeft);
                        if(timeLeft == 0){
                            this.cancel();
                        }
                    }
                }.runTaskTimer(thisPlugin, weaponUseCooldown, weaponUseCooldown);
                return true;
            }else{
                //Hasn't expired yet, shows how many seconds left until it does
                player.sendMessage(ChatColor.RED.toString() + timeLeft + " seconds before you can use this weapon again.");
                return false;
            }
        }
        return true;
    }
     */

    public void weaponExecute(Player player, PlayerInteractEvent interactEvent) {
        System.out.println("None exec");
    }
}
