package xyz.chasew.jacobsmmo.specialitems;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;

public class SpleefSnowball implements SpecialItem {
    @Override
    public String getSpecialItemName() {
        return "spleef_snowball";
    }
    @Override
    public String getRegisterToEvent() {
        return "onProjectileHit";
    }
    @Override
    public ItemStack createMe(Integer amount, Plugin thisPlugin) {
        ItemStack finalItems = new ItemStack(Material.SNOWBALL, amount);
        ItemMeta finalItemMeta = finalItems.getItemMeta();
        PersistentDataContainer pdc = finalItemMeta.getPersistentDataContainer();
        pdc.set(SpecialItemsContainer.getSpecialNameKey(thisPlugin), SpecialItemsContainer.getStrPDCT(), "spleef_snowball");
        finalItemMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.YELLOW + "SpleefBall");
        finalItems.setItemMeta(finalItemMeta);
        return finalItems;
    }

    @Override
    public void executeEventFunction(Event event) {
        ProjectileHitEvent projectileHitEvent = (ProjectileHitEvent) event;
        if (projectileHitEvent.getHitBlock() == null) {
            return;
        } else {
            Block hitBlock = projectileHitEvent.getHitBlock();
            if(hitBlock.getBlockData().getMaterial() != Material.SNOW_BLOCK) {
                return;
            }
            hitBlock.setType(Material.AIR);
            Player shooter = (Player) projectileHitEvent.getEntity().getShooter();
            shooter.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Nice one!"));
            hitBlock.getWorld().playSound(hitBlock.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL,2, 0);
        }
    }
    @Override
    public boolean isThisEventForMe(Event event, SpecialItem specialItem, NamespacedKey itemnamePdcKey) {
        ProjectileHitEvent projectileHitEvent = (ProjectileHitEvent) event;
        PersistentDataContainer entityPDC = projectileHitEvent.getEntity().getPersistentDataContainer();
        if(entityPDC.has(itemnamePdcKey, SpecialItemsContainer.getStrPDCT())) {
            return entityPDC.get(itemnamePdcKey, SpecialItemsContainer.getStrPDCT()).equals("spleef_snowball");
        } else {
            return false;
        }
    }
}
