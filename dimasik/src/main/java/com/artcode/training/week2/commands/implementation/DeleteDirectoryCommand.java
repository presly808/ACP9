package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;

public class DeleteDirectoryCommand extends AbstractCommand {
    @Override
    public String help() {
        return "rd <directory name> - remove directory";
    }

    @Override
    public String execute(File file, String... args) {
        newCurrentFile = file;
        String directoryName = String.join("", args);
        File directory = new File(file.getAbsolutePath() + File.separator + directoryName);
        String result = "";
        try {
            if (directory.exists()) {
                if (directory.isDirectory()) {
                    directory.delete();
                } else {
                    result = directoryName + " is not a directory";
                }
            } else {
                result = "There is no directory with name " + directoryName;
            }
        } catch (SecurityException e) {
            result = "You have no permissions to remove " + directoryName;
        }
        return result;
    }
}
