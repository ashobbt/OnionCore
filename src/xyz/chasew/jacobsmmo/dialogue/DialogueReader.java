package xyz.chasew.jacobsmmo.dialogue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialogueReader {
    public static List<String> readFile(String fileName, File dataFolder) {

        List<String> finalLines = new ArrayList<String>();
        File file = new File(dataFolder.getPath() + "/dialogueFiles/" + fileName);

        try {
            BufferedReader diFileReader = new BufferedReader(new FileReader(file.getPath()));
            String readLine = diFileReader.readLine();
            System.out.println(readLine);

            while(readLine != null) {
                String readStringPattern = "(d: \".*\")|(c: \".*\")";
                Pattern readPattern = Pattern.compile(readStringPattern);
                Matcher readMatcher = readPattern.matcher(readLine);
                if(readMatcher.find()) {
                    if(readMatcher.group(0) != null) {
                        finalLines.add(readMatcher.group(0));
                    } else if(readMatcher.group(1) != null) {
                        finalLines.add(readMatcher.group(1));
                    }
                }

                readLine = diFileReader.readLine();
            }

            diFileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        return finalLines;
    }

}
