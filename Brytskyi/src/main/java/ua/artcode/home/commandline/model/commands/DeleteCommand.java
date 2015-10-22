package ua.artcode.home.commandline.model.commands;

import ua.artcode.home.commandline.controller.FileHelper;

/**
 * User: huyti
 * Date: 08.10.15
 */
public class DeleteCommand implements Command {
    FileHelper helper;
    String file;

    public DeleteCommand(FileHelper helper, String file) {
        this.helper = helper;
        this.file = file;
    }

    @Override
    public void execute() {
        helper.delete(file);
    }
}
