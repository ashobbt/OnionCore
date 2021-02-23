package xyz.chasew.jacobsmmo.utilities;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

import java.util.Set;

public class NBTEditor {
    public static boolean hasTag(Entity entity, String tagName) {
        net.minecraft.server.v1_16_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();

        NBTTagCompound entityCompound = new NBTTagCompound();

        Set<String> entityTags = nmsEntity.getScoreboardTags();
        return entityTags.contains(tagName);
    }
}