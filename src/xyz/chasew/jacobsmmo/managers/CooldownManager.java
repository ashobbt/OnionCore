package xyz.chasew.jacobsmmo.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CooldownManager {

    private final Map<UUID, Long> mainCooldowns = new HashMap<>();
    private final Map<UUID, Long> secondaryCooldowns = new HashMap<>();

    public static final long DEFAULT_MAIN_COOLDOWN = 15;

    public void setMainCooldown(UUID player, long time){
        if(time < 1) {
            mainCooldowns.remove(player);
        } else {
            mainCooldowns.put(player, time);
        }
    }

    public long getMainCooldownLeft(UUID player) {
        long timeLeft = System.currentTimeMillis() - mainCooldowns.getOrDefault(player, DEFAULT_MAIN_COOLDOWN);
        return (long) Math.ceil(timeLeft / 1000);
    }


    public boolean isOnMainCoolDown(UUID playerID, long passedCooldown) {
        Long timeStamp = mainCooldowns.get(playerID);
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

    public void setSecondaryCooldown(UUID player, long time){
        if(time < 1) {
            secondaryCooldowns.remove(player);
        } else {
            secondaryCooldowns.put(player, time);
        }
    }

    public long getSecondaryCooldownLeft(UUID player) {
        long timeLeft = System.currentTimeMillis() - secondaryCooldowns.getOrDefault(player, DEFAULT_MAIN_COOLDOWN);
        return (long) Math.ceil(timeLeft / 1000);
    }


    public boolean isOnSecondayCooldown(UUID playerID, long passedCooldown) {
        Long timeStamp = secondaryCooldowns.get(playerID);
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
