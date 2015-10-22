package ua.artcode.home.commandline.model.commands;

import ua.artcode.home.commandline.controller.FileHelper;

/**
 * User: huyti
 * Date: 08.10.15
 */
public class CopyCommand implements Command {
    FileHelper helper;
    String atr1;
    String atr2;

    public CopyCommand(FileHelper helper, String atr1, String atr2) {
        this.helper = helper;
        this.atr1 = atr1;
        this.atr2 = atr2;
    }

    @Override
    public void execute() {
        helper.copyDirectory(atr1, atr2);
    }
}
