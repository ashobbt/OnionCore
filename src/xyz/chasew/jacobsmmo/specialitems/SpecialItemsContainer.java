package xyz.chasew.jacobsmmo.specialitems;

import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class SpecialItemsContainer {
    public final Plugin thisPlugin;
    public List<SpecialItem> specialItemList;

    public SpecialItemsContainer(Plugin thisPlugin) {
        this.thisPlugin = thisPlugin;
        this.specialItemList = new ArrayList<SpecialItem>();
    }

    public SpecialItem findSpecialItem(String name) {
        for(SpecialItem specialItem : specialItemList) {
            if(specialItem.getSpecialItemName().equals(name)) {
                return specialItem;
            }
        }
        return null;
    }
    public void addSpecialItem(SpecialItem item) {
        specialItemList.add(item);
    }
    public void executeSpecialItemsEvents(String eventName, Event event) {
        for(SpecialItem specialItem : specialItemList) {
            if(specialItem.getRegisterToEvent().equals(eventName) && specialItem.isThisEventForMe(event, specialItem, getSpecialNameKey(thisPlugin))) {
                specialItem.executeEventFunction(event);
            }
        }
    }
    public List<SpecialItem> getSpecialItemList() {
        return specialItemList;
    }
    public SpecialItemsContainer initItems() {
        addSpecialItem(new SpleefSnowball());
        return this;
    }
    public static NamespacedKey getSpecialNameKey(Plugin thisInputtedPlugin) {
        return new NamespacedKey(thisInputtedPlugin, "special_item_name");
    }
    public static PersistentDataType<String, String> getStrPDCT() {
        return PersistentDataType.STRING;
    }
}
