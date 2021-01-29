package xyz.chasew.jacobsmmo.weapons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.chasew.jacobsmmo.commands.BackIfNear;
import xyz.chasew.jacobsmmo.managers.CooldownManager;
import xyz.chasew.jacobsmmo.recipes.BoomStickRecipe;

import java.lang.reflect.Method;
import java.util.HashMap;

public class WeaponsHandlers implements Listener {
    public HashMap<String, WeaponAbstract> weaponRegister = new HashMap<String, WeaponAbstract>();
    public Plugin thisPlugin;
    public CooldownManager cooldownManager;
    public void registerWeapon(String nameString, WeaponAbstract weaponClass) {
        weaponRegister.put(nameString, weaponClass);
    }
    public WeaponsHandlers(Plugin thisPlugin) {
        this.thisPlugin = thisPlugin;
        this.cooldownManager = new CooldownManager();
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interactEvent) {
        try {
            interactEvent.getItem().getItemMeta().getDisplayName();
        } catch (Exception exception) {
            return;
        }
        Player player = interactEvent.getPlayer();
        Action eventAction = interactEvent.getAction();
        registerWeapon(NecromanyStick.weaponNameWithFormat, new NecromanyStick(thisPlugin, cooldownManager));
        registerWeapon(BoomStick.weaponNameWithFormat, new BoomStick(thisPlugin, cooldownManager));
        registerWeapon(FireballStick.weaponNameWithFormat, new FireballStick(thisPlugin, cooldownManager));
        for(String i: weaponRegister.keySet()) {
            if(interactEvent.getItem().getItemMeta().getDisplayName().equals(i)) {
                Player p = interactEvent.getPlayer();
                WeaponAbstract weaponGot = weaponRegister.get(i);
                weaponGot.weaponExecute(p, interactEvent);
            }
        }
    }
}