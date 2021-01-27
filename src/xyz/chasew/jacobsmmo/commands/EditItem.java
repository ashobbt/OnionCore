package xyz.chasew.jacobsmmo.commands;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class EditItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player playerSender = (Player) sender;
            ItemStack toEdit = playerSender.getInventory().getItemInMainHand();
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
                default:
                    playerSender.sendMessage("That option isn't added yet!");
            }
        }
        return true;
    }
}
