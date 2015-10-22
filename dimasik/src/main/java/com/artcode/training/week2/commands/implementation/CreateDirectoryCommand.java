package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;

public class CreateDirectoryCommand extends AbstractCommand {
    @Override
    public String help() {
        return "mkdir <directory name> - create directory";
    }

    @Override
    public String execute(File file, String... args) {
        newCurrentFile = file;
        String directoryName = String.join("", args);
        File directory = new File(file.getAbsolutePath() + File.separator + directoryName);
        String result = "";
        try {
            if (directory.exists()) {
                    result = directoryName + " already exists";
            } else {
                directory.mkdir();
            }
        } catch (SecurityException e) {
            result = "You have no permissions to create directory " + directoryName;
        }
        return result;
    }
}
