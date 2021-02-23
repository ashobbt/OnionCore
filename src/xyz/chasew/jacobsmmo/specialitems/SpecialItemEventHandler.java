package xyz.chasew.jacobsmmo.specialitems;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpecialItemEventHandler implements Listener {
    private final SpecialItemsContainer container;

    public SpecialItemEventHandler(SpecialItemsContainer container) {
        this.container = container;
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        container.executeSpecialItemsEvents("onProjectileHit", event);
    }
    @EventHandler
    public void onProjectileLaunch(final ProjectileLaunchEvent event) {
        if(!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
        final ItemStack launchedItem = ((Player) event.getEntity().getShooter()).getInventory().getItemInMainHand();
        final Projectile projectile = event.getEntity();

        final PersistentDataContainer itemContainer = launchedItem.getItemMeta().getPersistentDataContainer();
        final PersistentDataContainer projectileContainer = projectile.getPersistentDataContainer();
        final NamespacedKey itemNameKey = SpecialItemsContainer.getSpecialNameKey(container.thisPlugin);

        final String someValue = itemContainer.get(itemNameKey, PersistentDataType.STRING);
        if (someValue != null) {
            projectileContainer.set(itemNameKey, PersistentDataType.STRING, someValue);
        }
    }
}
