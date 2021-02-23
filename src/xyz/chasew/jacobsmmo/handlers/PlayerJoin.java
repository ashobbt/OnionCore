package xyz.chasew.jacobsmmo.handlers;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public class PlayerJoin implements Listener {
    private final JavaPlugin thisPlugin;
    public PlayerJoin(JavaPlugin thisPlugin) {
        this.thisPlugin = thisPlugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {
        List<String> joinMessages = thisPlugin.getConfig().getStringList("join-messages");
        Random rand = new Random();
        String randJoinMsg = joinMessages.get(rand.nextInt(joinMessages.size()));
        joinEvent.setJoinMessage(ChatColor.GREEN + joinEvent.getPlayer().getName() + ChatColor.DARK_GREEN + " joined the server, " + randJoinMsg);
        joinEvent.getPlayer().sendTitle(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "Welcome!", ChatColor.BOLD + "Good to see you, " + joinEvent.getPlayer().getName(), 10, 70, 20);
    }
}
