package ua.artcode.home.commandline.controller;

import ua.artcode.home.commandline.model.commands.*;

/**
 * User: Alexandr
 */
public class CommandStarter {
    // this class must be like invoker
    //invoker has all the methods are implemented by Command (model)
    //invoker must define is real command or not, then if all is ok it runs a command (run method execute)
    //

    Command helpCommand;
    Command cdCommand;
    Command findCommand;
    Command dirCommand;
    Command typeCommand;
    Command delCommand;
    Command rdCommand;
    Command mkdirCommand;
    Command touchCommand;
    Command copyCommand;
    Command compareCommand;


    public void runHelpCommand(HelpCommand helpCommand) {
        this.helpCommand = helpCommand;
        helpCommand.execute();
    }

    public void runCDCommand(CDCommand cdCommand) {
        this.cdCommand = cdCommand;
        cdCommand.execute();
    }

    public void runFindCommand(FindCommand findCommand) {
        this.findCommand = findCommand;
        findCommand.execute();
    }

    public void runDirCommand(DirCommand dirCommand) {
        this.dirCommand = dirCommand;
        dirCommand.execute();
    }

    public void runTypeCommand(TypeCommand typeCommand) {
        this.typeCommand = typeCommand;
        typeCommand.execute();
    }

    public void runRDCommand(RDCommand rdCommand) {
        this.rdCommand = rdCommand;
        rdCommand.execute();
    }

    public void runDeleteCommand(DeleteCommand delCommand) {
        this.delCommand = delCommand;
        delCommand.execute();
    }

    public void runMkdirCommand(MkdirCommand mkdirCommand) {
        this.mkdirCommand = mkdirCommand;
        mkdirCommand.execute();
    }

    public void runTouchCommand(TouchCommand touchCommand) {
        this.touchCommand = touchCommand;
        touchCommand.execute();
    }

    public void runCopyCommand(CopyCommand copyCommand) {
        this.copyCommand = copyCommand;
        copyCommand.execute();
    }

    public void runCompareCommand(CompareCommand compareCommand) {
        this.compareCommand = compareCommand;
        compareCommand.execute();
    }

}
