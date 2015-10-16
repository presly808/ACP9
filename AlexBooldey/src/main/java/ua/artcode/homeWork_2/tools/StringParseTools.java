package ua.artcode.homeWork_2.tools;

import java.util.NoSuchElementException;

public class StringParseTools {

    private static String command;
    private static String[] commandSignature;
    private static String string;


    public static String parseCommand(String string){
        StringParseTools.string = string;
        if (string != null){
            commandSignature = string.split(" ");
            return commandSignature[0];
        }
        else throw new NoSuchElementException("null command");
    }

}
