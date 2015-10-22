package com.artcode.training.week2.commands;

import java.io.File;

public interface Command {

    String help();

    String execute(File file, String... args);
}
