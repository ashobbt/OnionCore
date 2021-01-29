package xyz.chasew.jacobsmmo.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CooldownManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public static final long DEFAULT_COOLDOWN = 15;

    public void setCooldown(UUID player, long time){
        if(time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public long getCooldownLeft(UUID player) {
        long timeLeft = System.currentTimeMillis() - cooldowns.getOrDefault(player, 0L);
        return timeLeft;
    }


    public boolean isOnCoolDown(UUID playerID, long passedCooldown) {
        Long timeStamp = cooldowns.get(playerID);
        if (timeStamp == null) {
            return false;
        }
        long timePassed = System.currentTimeMillis() - timeStamp;
        long coolDownLeft = TimeUnit.SECONDS.toMillis(passedCooldown) - timePassed;
        if (coolDownLeft <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
