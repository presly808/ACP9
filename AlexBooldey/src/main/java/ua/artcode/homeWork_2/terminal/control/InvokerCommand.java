package ua.artcode.homeWork_2.terminal.control;

public class InvokerCommand {

    private Command commandCd;
    private Command commandCopy;
    private Command commandDir;
    private Command commandFind;
    private Command commandHelp;
    private Command commandMkdir;
    private Command commandPwd;
    private Command commandRm;
    private Command commandTouch;
    private Command commandType;

    public InvokerCommand(Command commandCd, Command commandCopy, Command commandDir,
                          Command commandFind, Command commandHelp, Command commandMkdir,
                          Command commandPwd, Command commandRm, Command commandTouch, Command commandType) {

        this.commandCd = commandCd;
        this.commandCopy = commandCopy;
        this.commandDir = commandDir;
        this.commandFind = commandFind;
        this.commandHelp = commandHelp;
        this.commandMkdir = commandMkdir;
        this.commandPwd = commandPwd;
        this.commandRm = commandRm;
        this.commandTouch = commandTouch;
        this.commandType = commandType;
    }

    public void getCommandCd() {
        commandCd.execute();
    }

    public void getCommandCopy() {
        commandCopy.execute();
    }

    public void getCommandDir() {
        commandDir.execute();
    }

    public void getCommandFind() {
        commandFind.execute();
    }

    public void getCommandHelp() {
        commandHelp.execute();
    }

    public void getCommandMkdir() {
        commandMkdir.execute();
    }

    public void getCommandPwd() {
        commandPwd.execute();
    }

    public void getCommandRm() {
        commandRm.execute();
    }

    public void getCommandTouch() {
        commandTouch.execute();
    }

    public void getCommandType() {
        commandType.execute();
    }
}
