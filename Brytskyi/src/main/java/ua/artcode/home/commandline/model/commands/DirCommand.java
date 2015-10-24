package ua.artcode.home.commandline.model.commands;

import ua.artcode.home.commandline.controller.FileHelper;

/**
 * User: huyti
 * Date: 08.10.15
 */
public class DirCommand implements Command {
    FileHelper helper;

    public DirCommand(FileHelper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.dir();
    }
}
