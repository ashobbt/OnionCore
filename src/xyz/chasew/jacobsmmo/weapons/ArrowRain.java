package xyz.chasew.jacobsmmo.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import xyz.chasew.jacobsmmo.managers.CooldownManager;

public class ArrowRain extends WeaponAbstract {
    public static String weaponNameWithFormat = "arrow_rain";
    public static long weaponUseCooldown = 25;

    CooldownManager cooldownManager;

    public ArrowRain(Plugin thisPlugin, CooldownManager cooldownManager) {
        this.thisPlugin = thisPlugin;
        this.cooldownManager = cooldownManager;
        this.weaponUseType = WeaponUseType.ANY;
    }


    public void weaponExecute(Player player, PlayerInteractEvent interactEvent) {
        if(!cooldownManager.isOnMainCoolDown(player.getUniqueId(), weaponUseCooldown)) {
            player.sendMessage(ChatColor.GREEN + "Used item!");
            cooldownManager.setMainCooldown(player.getUniqueId(), System.currentTimeMillis());
        } else {
            player.sendMessage(ChatColor.RED + "There's a " + Long.toString(weaponUseCooldown - cooldownManager.getMainCooldownLeft(player.getUniqueId())) + " second cooldown on this!");
            return;
        }


    }

}
