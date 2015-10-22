package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;

public class DeleteFileCommand extends AbstractCommand {
    @Override
    public String help() {
        return "del <file name> - remove file";
    }

    @Override
    public String execute(File file, String... args) {
        newCurrentFile = file;
        String fileName = String.join("", args);
        File fileForRemove = new File(file.getAbsolutePath() + File.separator + fileName);
        String result = "";
        try {
            if (fileForRemove.exists()) {
                if (fileForRemove.isFile()) {
                    fileForRemove.delete();
                } else {
                    result = fileName + " is not a file";
                }
            } else {
                result = "There is no file with name " + fileName;
            }
        } catch (SecurityException e) {
            result = "You have no permissions to remove " + fileName;
        }
        return result;
    }
}
