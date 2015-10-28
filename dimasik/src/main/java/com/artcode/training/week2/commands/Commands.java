package com.artcode.training.week2.commands;

import com.artcode.training.week2.commands.implementation.*;

public enum Commands {
    CD(new ChangeLocationCommand()),
    DIR(new ShowDirectoryContentCommand()),
    TYPE(new ShowFileContentCommand()),
    DEL(new DeleteFileCommand()),
    RD(new DeleteDirectoryCommand()),
    MKDIR(new CreateDirectoryCommand()),
    FIND(new FindFileByNameUsingQueueCommand()),
    HELP(new HelpCommand());

    private AbstractCommand command;

    Commands(AbstractCommand command) {
        this.command = command;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
