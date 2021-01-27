package xyz.chasew.jacobsmmo.commands;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player playerSender = (Player) sender;
            ItemStack toEdit = playerSender.getInventory().getItemInMainHand();
            ItemMeta toEditMeta = toEdit.getItemMeta();
            if(toEditMeta == null) {
                playerSender.sendMessage("You have to be holding an item to edit it.");
            }
            ItemStack materialAir = new ItemStack(Material.AIR, 1);
            if(toEdit.equals(materialAir)) {
                playerSender.sendMessage("You need to be holding something.");
                return true;
            }
            if(args.length < 1) {
                playerSender.sendMessage("Please specify a value to edit");
                return false;
            }
            switch(args[0]) {
                case "enchant":
                    //edititem enchant efficiency 5 true
                    if(args.length < 4) {
                        playerSender.sendMessage(ChatColor.GREEN + "Hey! You need an enchantment WITHOUT the 'minecraft:', level, and value (true or false). Example: '/edititem enchant efficiency 5 false' which would remove the efficiency 5 enchant.");
                        return true;
                    }

                    //edititem enchant glint true
                    /*
                    if(args[1].equals("glint")) {
                        if(Boolean.parseBoolean(args[2])) {
                            toEdit.
                        }
                    }
                     */


                    try {
                        Enchantment enchantTo = Enchantment.getByKey(NamespacedKey.minecraft(args[1].toLowerCase()));
                        if(Boolean.parseBoolean(args[3])) {
                            toEdit.addUnsafeEnchantment(enchantTo, Integer.parseInt(args[2]));
                        } else {
                            toEdit.removeEnchantment(enchantTo);
                        }
                        return true;
                    } catch(IllegalArgumentException exception) {
                        playerSender.sendMessage("Hey! That's not a valid enchant");
                        return false;
                    }
                case "name":
                    String fullCommand = String.join(" ", args);
                    String searchPatternString = "(\".*\") (\".*\") (\".*\")";

                    Pattern searchPattern = Pattern.compile(searchPatternString);
                    Matcher matcher = searchPattern.matcher(fullCommand);

                    if(!matcher.find()) {
                        playerSender.sendMessage("Hey you need a new item name in between quotes! (NOF)");
                        return true;
                    }

                    String newName = matcher.group(0).replace("\"", "").split(" ")[0];
                    if(newName.equals("")) {
                        playerSender.sendMessage("Hey you need a new item name in between quotes! (NOE)");
                        return true;
                    }
                    toEditMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', newName));
                    List<String> potentialLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', matcher.group(2).replace("\"", "")), ChatColor.translateAlternateColorCodes('&', matcher.group(3).replace("\"", "")));
                    if(matcher.group(1) != null) {
                        toEditMeta.setLore(potentialLore);
                    }
                    toEdit.setItemMeta(toEditMeta);
                    playerSender.updateInventory();
                    break;
                default:
                    playerSender.sendMessage("That option isn't added yet!");
            }
        }
        return true;
    }
}
