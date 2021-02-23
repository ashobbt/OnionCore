package xyz.chasew.jacobsmmo.specialitems;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class GiveSpecialCommand implements CommandExecutor {
    private final SpecialItemsContainer specialItemsContainer;

    public GiveSpecialCommand(SpecialItemsContainer specialItemsContainer) {
        this.specialItemsContainer = specialItemsContainer;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /givespecial <sel> <item> <amount>
        if(args.length < 3) {
            return false;
        }
        List<Entity> selected = Bukkit.selectEntities(sender, args[0]);
        String item = args[1];
        Integer amount = Integer.parseInt(args[2]);

        for(Entity e : selected) {
            if(!(e instanceof Player)) {
                continue;
            } else {
                if(specialItemsContainer.findSpecialItem(item) == null) {
                    sender.sendMessage("Not a valid item!");
                    return true;
                }
                Player player = (Player) e;
                player.getInventory().addItem(specialItemsContainer.findSpecialItem(item).createMe(amount, specialItemsContainer.thisPlugin));
            }
        }
        return true;
    }
}
