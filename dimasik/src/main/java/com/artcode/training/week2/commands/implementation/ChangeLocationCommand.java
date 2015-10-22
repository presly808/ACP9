package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;

public class ChangeLocationCommand extends AbstractCommand {
    @Override
    public String help() {
        return "cd < .. | directory[" + File.separator + "directory] | absolute path>  - go to parent directory | "
                + " go to child directory | "
                + "go to specified directory";
    }

    @Override
    public String execute(File file, String... args) {
        String join = String.join("", args);
        if ("..".equals(join)) {
            newCurrentFile = file.getAbsoluteFile().getParentFile();
        } else if (join.contains(":")) {
            File newFile = new File(join);
            newCurrentFile = newFile.isDirectory() && newFile.exists() ? newFile : file;
        } else {
            File newFile = new File(file.getAbsolutePath() + File.separator + join);
            newCurrentFile = newFile.isDirectory() && newFile.exists() ? newFile : file;
        }
        return "";
    }
}
