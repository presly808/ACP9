package ua.artcode.home.commandline.model.commands;

import ua.artcode.home.commandline.controller.FileHelper;

/**
 * User: huyti
 * Date: 08.10.15
 */
public class RDCommand implements Command {
    FileHelper helper;
    String path;

    public RDCommand(FileHelper helper, String path) {
        this.helper = helper;
        this.path = path;
    }

    @Override
    public void execute() {
        helper.rd(path);
    }
}
