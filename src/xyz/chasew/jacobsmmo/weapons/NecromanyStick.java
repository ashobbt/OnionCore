package xyz.chasew.jacobsmmo.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import xyz.chasew.jacobsmmo.managers.CooldownManager;

import java.util.concurrent.TimeUnit;

public class NecromanyStick extends WeaponAbstract{
    public static String weaponNameWithFormat = ChatColor.BOLD + "Necromancy Stick";
    public static long weaponUseCooldown = 10;
    public Plugin thisPlugin;

    CooldownManager cooldownManager;

    public NecromanyStick(Plugin thisPlugin, CooldownManager cooldownManager) {
        this.thisPlugin = thisPlugin;
        this.cooldownManager = cooldownManager;
    }


    public void weaponExecute(Player player, PlayerInteractEvent interactEvent) {
        if(!cooldownManager.isOnCoolDown(player.getUniqueId(), weaponUseCooldown)) {
            player.sendMessage(ChatColor.GREEN + "Used item!");
            cooldownManager.setCooldown(player.getUniqueId(), System.currentTimeMillis());
        } else {
            player.sendMessage(ChatColor.RED + "There's a " + Long.toString(weaponUseCooldown) + " second cooldown on this!");
            return;
        }
        Integer zombieSpawnCount = 10;
        World world = player.getWorld();
        while(zombieSpawnCount > 0) {
            Location loc;
            if(zombieSpawnCount % 2 == 0) {
                //System.out.println("E");
                loc = player.getLocation().subtract(2, 0, 0);
            } else {
                loc = player.getLocation().add(2, 0, 2);
                //System.out.println("O");
            }
            LivingEntity spawnedZombie = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
            spawnedZombie.getEquipment().setHelmet(new ItemStack(Material.GLASS));
            zombieSpawnCount= zombieSpawnCount - 1;
        }
    }
}