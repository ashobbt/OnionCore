package xyz.chasew.jacobsmmo.utilities;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class Utilities {
    public static PersistentDataType<String, String> stringType = PersistentDataType.STRING;
    public static NamespacedKey weaponNameAttrib(Plugin p) { return new NamespacedKey(p, "weapon_name"); }
}
