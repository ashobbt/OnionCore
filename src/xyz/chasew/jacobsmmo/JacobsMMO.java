package xyz.chasew.jacobsmmo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.chasew.jacobsmmo.commands.BackIfNear;
import xyz.chasew.jacobsmmo.commands.EditItem;
import xyz.chasew.jacobsmmo.filesystem.PluginFileSystemHandler;
import xyz.chasew.jacobsmmo.handlers.BoomStickHandler;
import xyz.chasew.jacobsmmo.handlers.PlayerJoin;
import xyz.chasew.jacobsmmo.recipes.BoomStickRecipe;
import xyz.chasew.jacobsmmo.weapons.WeaponsHandlers;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacobsMMO extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("\n----\nJacob's MORPG Plugin is enabled\n----");
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new WeaponsHandlers(this), this);
        Bukkit.addRecipe(new BoomStickRecipe().getRec(this));
        this.getCommand("edititem").setExecutor(new EditItem());
        this.getCommand("backifnear").setExecutor(new BackIfNear());

        PluginFileSystemHandler.enableHandler(getDataFolder());
    }

    @Override
    public void onDisable() {

    }
}
