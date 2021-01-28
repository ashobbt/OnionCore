package xyz.chasew.jacobsmmo.handlers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.chasew.jacobsmmo.commands.BackIfNear;
import xyz.chasew.jacobsmmo.recipes.BoomStickRecipe;

import java.util.Arrays;
import java.util.List;

public class BoomStickHandler implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interactEvent) {
        Player player = interactEvent.getPlayer();
        Action eventAction = interactEvent.getAction();
        if(player.getInventory().getItemInMainHand().equals(BoomStickRecipe.item)) {
            if(eventAction == Action.RIGHT_CLICK_AIR || eventAction == Action.RIGHT_CLICK_BLOCK) {
                interactEvent.setCancelled(true);
                return;
            }
            player.sendMessage(ChatColor.LIGHT_PURPLE + "BOOM!");
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 175, 255));
            BackIfNear.pushAwayFrom(player, player.getLocation(), player.getLocation().add(0, 20, 0), 2.5);
        }

    }
}
