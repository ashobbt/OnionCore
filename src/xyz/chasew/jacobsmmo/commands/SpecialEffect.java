package xyz.chasew.jacobsmmo.commands;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import xyz.chasew.jacobsmmo.preset.PresetDecoder;

public class SpecialEffect implements CommandExecutor {
    private final Plugin thisPlugin;

    public SpecialEffect(Plugin thisPlugin) {
        this.thisPlugin = thisPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof BlockCommandSender)) {
            return true;
        }
        BlockCommandSender blockSender = (BlockCommandSender) sender;

        PresetDecoder decoder = new PresetDecoder();

        if(args[0] == null ||args[1] == null || args[2] == null || args[3] == null) {
            return false;
        }

        if(decoder.useManifest("particles").presetFunctionDoesExist(args[0])) {
            decoder.useManifest("particles").executePresetFunction(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), sender, thisPlugin);
        }

        return true;
    }
}
