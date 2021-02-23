package xyz.chasew.jacobsmmo.filesystem;

import xyz.chasew.jacobsmmo.dialogue.DialogueReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PluginFileSystemHandler {

    public static void registerCustomDir(String dirName, File dataFolder) {
        File checkDir = new File(dataFolder.getPath()+dirName);
        if(!checkDir.exists()) {
            checkDir.mkdirs();
        }
    }

    public static void enableHandler(File dF) {
        PluginFileSystemHandler.registerCustomDir("/dialogueFiles", dF);
        PluginFileSystemHandler.registerCustomDir("/presetFiles", dF);
    }
}
