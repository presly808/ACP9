package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;
import java.util.Arrays;

/**
 * Created by dmitriyg on 10/7/2015.
 */
public class ShowDirectoryContentCommand extends AbstractCommand {
    @Override
    public String help() {
        return "dir - show directory content";
    }

    @Override
    public String execute(File file, String... args) {
        newCurrentFile = file;
        return String.join("\n", Arrays.stream(file.listFiles()).map(File::getName).toArray(String[]::new));
    }
}
