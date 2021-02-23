package xyz.chasew.jacobsmmo.specialitems;

import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public interface SpecialItem {
    public ItemStack createMe(Integer amount, Plugin thisPlugin);
    public void executeEventFunction(Event event);
    public String getSpecialItemName();
    public String getRegisterToEvent();
    public boolean isThisEventForMe(Event event, SpecialItem specialItem, NamespacedKey itemnamePdcKey);
}
