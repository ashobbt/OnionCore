package xyz.chasew.jacobsmmo;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.chasew.jacobsmmo.commands.EditItem;
import xyz.chasew.jacobsmmo.handlers.PlayerJoin;

import java.util.List;

public class JacobsMMO extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("\n----\nJacob's MORPG Plugin is enabled\n----");
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        this.getCommand("edititem").setExecutor(new EditItem());
    }

    @Override
    public void onDisable() {

    }
}