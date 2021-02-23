package xyz.chasew.jacobsmmo.preset;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public interface PresetManifest {
    public boolean presetFunctionDoesExist(String functionName);
    public void executePresetFunction(String functionName, Integer x, Integer y, Integer z, CommandSender sender, Plugin thisPlugin);
}
