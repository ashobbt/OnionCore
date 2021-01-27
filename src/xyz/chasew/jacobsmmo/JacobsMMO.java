package xyz.chasew.jacobsmmo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.chasew.jacobsmmo.commands.BackIfNear;
import xyz.chasew.jacobsmmo.commands.EditItem;
import xyz.chasew.jacobsmmo.handlers.BoomStickHandler;
import xyz.chasew.jacobsmmo.handlers.PlayerJoin;
import xyz.chasew.jacobsmmo.recipes.BoomStickRecipe;

import java.util.List;

public class JacobsMMO extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("\n----\nJacob's MORPG Plugin is enabled\n----");
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new BoomStickHandler(), this);
        Bukkit.addRecipe(new BoomStickRecipe().getRec(this));
        this.getCommand("edititem").setExecutor(new EditItem());
        this.getCommand("backifnear").setExecutor(new BackIfNear());
    }

    @Override
    public void onDisable() {

    }
}
