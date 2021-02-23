package xyz.chasew.jacobsmmo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.chasew.jacobsmmo.commands.*;
import xyz.chasew.jacobsmmo.filesystem.PluginFileSystemHandler;
import xyz.chasew.jacobsmmo.handlers.PlayerJoin;
import xyz.chasew.jacobsmmo.recipes.BoomStickRecipe;
import xyz.chasew.jacobsmmo.specialitems.GiveSpecialCommand;
import xyz.chasew.jacobsmmo.specialitems.SpecialItem;
import xyz.chasew.jacobsmmo.specialitems.SpecialItemEventHandler;
import xyz.chasew.jacobsmmo.specialitems.SpecialItemsContainer;
import xyz.chasew.jacobsmmo.weapons.WeaponsHandlers;

public class JacobsMMO extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new WeaponsHandlers(this), this);
        Bukkit.addRecipe(new BoomStickRecipe().getRec(this));
        this.getCommand("edititem").setExecutor(new EditItem());
        this.getCommand("backifnear").setExecutor(new BackIfNear());
        this.getCommand("givespecialweapon").setExecutor(new GiveSpecialWeapon(this));
        this.getCommand("specialeffect").setExecutor(new SpecialEffect(this));
        this.getCommand("rotateallentities").setExecutor(new RotateAllEntities());
        SpecialItemsContainer specialItemsContainer = new SpecialItemsContainer(this);
        specialItemsContainer.initItems();
        this.getCommand("givespecial").setExecutor(new GiveSpecialCommand(specialItemsContainer));
        getServer().getPluginManager().registerEvents(new SpecialItemEventHandler(specialItemsContainer), this);
        PluginFileSystemHandler.enableHandler(getDataFolder());
    }

    @Override
    public void onDisable() {

    }
}
