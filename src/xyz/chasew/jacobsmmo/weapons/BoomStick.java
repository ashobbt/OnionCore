package xyz.chasew.jacobsmmo.weapons;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagString;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
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
    public static String weaponNameWithFormat = "boom_stick";
    public static long weaponUseCooldown = 10;

    CooldownManager cooldownManager;

    public BoomStick(Plugin thisPlugin, CooldownManager cooldownManager) {
        this.thisPlugin = thisPlugin;
        this.cooldownManager = cooldownManager;
        this.weaponUseType = WeaponUseType.RIGHT_CLICK;
    }


    public void weaponExecute(Player player, PlayerInteractEvent interactEvent) {
        if(!cooldownManager.isOnMainCoolDown(player.getUniqueId(), weaponUseCooldown)) {
            player.sendMessage(ChatColor.GREEN + "Used item!");
            cooldownManager.setMainCooldown(player.getUniqueId(), System.currentTimeMillis());
        } else {
            player.sendMessage(ChatColor.RED + "There's a " + Long.toString(weaponUseCooldown - cooldownManager.getMainCooldownLeft(player.getUniqueId())) + " second cooldown on this!");
            return;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 5, 5);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 175, 100));
        BackIfNear.pushAwayFrom(player, player.getLocation(), player.getLocation().add(0, 20, 0), 2);
    }
}
