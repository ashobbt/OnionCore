package xyz.chasew.jacobsmmo.preset.particles;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import xyz.chasew.jacobsmmo.preset.PresetManifest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ParticlePresetManifest implements PresetManifest {
    @Override
    public boolean presetFunctionDoesExist(String functionName) {
        return false;
    }

    @Override
    public void executePresetFunction(String functionName, Integer x, Integer y, Integer z, CommandSender sender, Plugin thisPlugin) {
        //TEST
        File pluginPresetFile = new File("test/test1.ogpp");
        //PRODUCTION
        //File pluginPresetFile = new File(thisPlugin.getDataFolder().getPath()+"/presetFiles/"+functionName+".ogpp");

        try {
            BufferedReader presetRdr = new BufferedReader(new FileReader(pluginPresetFile.getPath()));
            String onLineString = presetRdr.readLine();
        } catch (IOException ignored) {

        }
    }
    /*
    public static void main(String[] args) {
        //TEST
        File pluginPresetFile = new File("/Users/chasewitzansky/Documents/GitHub/JacobsMMO/src/xyz/chasew/jacobsmmo/preset/particles/test1.ogpp");
        //PRODUCTION
        //File pluginPresetFile = new File(thisPlugin.getDataFolder().getPath()+"/presetFiles/"+functionName+".ogpp");

        try {
            BufferedReader presetRdr = new BufferedReader(new FileReader(pluginPresetFile.getPath()));
            String onLineString = presetRdr.readLine();
            while (onLineString != null) {
                onLineString = onLineString.replaceAll("\\/\\*.*\\*\\/", "");
                onLineString = onLineString.replaceAll("\\/\\/.*",  "");
                System.out.println(onLineString);
                String[] lineArgs = onLineString.split(" ");
                switch (lineArgs[0]) {
                    case "particle":

                        break;
                }


                onLineString = presetRdr.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
     */
}
