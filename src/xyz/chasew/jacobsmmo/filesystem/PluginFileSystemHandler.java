package xyz.chasew.jacobsmmo.filesystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PluginFileSystemHandler {
    public static boolean createFile() {
        return true;
    }
    public static void enableHandler(File dataFolder) {
        try {
            FileWriter myWriter = new FileWriter(dataFolder.getPath()+"/main.txt");
            myWriter.write("Awesome");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }
}
