package xyz.chasew.jacobsmmo.weapons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import xyz.chasew.jacobsmmo.managers.CooldownManager;
import xyz.chasew.jacobsmmo.utilities.Utilities;

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
        registerWeapon(FireballWand.weaponNameWithFormat, new FireballWand(thisPlugin, cooldownManager));
        registerWeapon(ArrowRain.weaponNameWithFormat, new ArrowRain(thisPlugin, cooldownManager));
        try {
            for(String i: weaponRegister.keySet()) {
                if(
                        interactEvent.getItem().getItemMeta().getPersistentDataContainer().get(Utilities.weaponNameAttrib(thisPlugin), Utilities.stringType).equals(i)
                ) {
                    Player p = interactEvent.getPlayer();
                    WeaponAbstract weaponGot = weaponRegister.get(i);
                    switch(weaponGot.weaponUseType) {
                        case ANY:
                            weaponGot.weaponExecute(p, interactEvent);
                            break;
                        case LEFT_CLICK:
                            if(eventAction == Action.LEFT_CLICK_AIR || eventAction == Action.LEFT_CLICK_BLOCK) {
                                weaponGot.weaponExecute(p, interactEvent);
                            }
                            break;
                        case RIGHT_CLICK:
                            if(eventAction == Action.RIGHT_CLICK_AIR || eventAction == Action.RIGHT_CLICK_BLOCK) {
                                weaponGot.weaponExecute(p, interactEvent);
                            }
                            break;
                    }
                    interactEvent.setCancelled(true);
                }


            }
        } catch (Exception e) {
            return;
        }
    }
}