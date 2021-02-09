package xyz.chasew.jacobsmmo.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import xyz.chasew.jacobsmmo.utilities.Utilities;

public class GiveSpecial implements CommandExecutor {
    public Plugin thisPlugin;
    public NamespacedKey weaponNameAttrib;
    public GiveSpecial(Plugin thisPlugin) {
        this.thisPlugin = thisPlugin;
        this.weaponNameAttrib = new NamespacedKey(thisPlugin, "weapon_name");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            switch(args[1]) {
                case "boom_stick":
                    ItemStack boomStick = new ItemStack(Material.STICK, 1);
                    ItemMeta boomMeta = boomStick.getItemMeta();
                    PersistentDataContainer boomPDC = boomMeta.getPersistentDataContainer();
                    boomPDC.set(Utilities.weaponNameAttrib(thisPlugin), Utilities.stringType, "boom_stick");
                    boomMeta.setDisplayName(ChatColor.RED + "Boom Stick");
                    boomStick.setItemMeta(boomMeta);
                    Player toGiveStick = Bukkit.getPlayer(args[0]);

                    if(toGiveStick == null) {
                        return false;
                    }
                    toGiveStick.getInventory().addItem(boomStick);
                    break;
                case "fireball_wand":
                    ItemStack fireballWand = new ItemStack(Material.STICK, 1);
                    ItemMeta wandMeta = fireballWand.getItemMeta();
                    PersistentDataContainer wandPDC = wandMeta.getPersistentDataContainer();
                    wandPDC.set(Utilities.weaponNameAttrib(thisPlugin), Utilities.stringType, "fireball_wand");
                    wandMeta.setDisplayName(ChatColor.GOLD + "Fireball Wand");
                    fireballWand.setItemMeta(wandMeta);
                    Player toGiveFireWand = Bukkit.getPlayer(args[0]);
                    if(toGiveFireWand == null) {
                        return false;
                    }
                    toGiveFireWand.getInventory().addItem(fireballWand);
                    break;
                case "necromancy_stick":
                    ItemStack necromancyStick = new ItemStack(Material.STICK, 1);
                    ItemMeta necroMeta = necromancyStick.getItemMeta();
                    PersistentDataContainer necroPDC = necroMeta.getPersistentDataContainer();
                    necroPDC.set(Utilities.weaponNameAttrib(thisPlugin), Utilities.stringType, "necromancy_wand");
                    necroMeta.setDisplayName(ChatColor.GOLD + "Necromancy Wand");
                    necromancyStick.setItemMeta(necroMeta);
                    Player toGiveNecroWand = Bukkit.getPlayer(args[0]);
                    if(toGiveNecroWand == null) {
                        return false;
                    }
                    toGiveNecroWand.getInventory().addItem(necromancyStick);
                    break;
                default:
                    player.sendMessage("Haven't added that yet!");
            }
        }
        return true;
    }

}
