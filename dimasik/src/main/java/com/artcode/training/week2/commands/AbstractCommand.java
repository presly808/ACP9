package com.artcode.training.week2.commands;

import java.io.File;

public abstract class AbstractCommand implements Command {

    public static final String HELP = "--help";
    protected File newCurrentFile;

    public String run(File file,String... args) {
        newCurrentFile = file;
        return args.length == 1 && args[0].equals(HELP) ? help() : execute(file, args);
    }

    public File getNewCurrentFile() {
        return newCurrentFile;
    }
}
