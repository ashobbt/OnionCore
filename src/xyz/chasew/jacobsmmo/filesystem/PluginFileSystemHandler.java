package xyz.chasew.jacobsmmo.filesystem;

import xyz.chasew.jacobsmmo.dialogue.DialogueReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PluginFileSystemHandler {
    public static boolean createFile() {
        return true;
    }
    public static void enableHandler(File dataFolder) {
        File checkDirectory = new File(dataFolder.getPath()+"/dialogueFiles");
        if(!checkDirectory.exists()) {
            checkDirectory.mkdirs();
        }

    }
}
