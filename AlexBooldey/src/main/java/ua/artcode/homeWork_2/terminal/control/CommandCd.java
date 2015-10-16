package ua.artcode.homeWork_2.terminal.control;

public class CommandCd implements Command {
    Recipient logic;

    public CommandCd(Recipient logic) {
        this.logic = logic;
    }

    @Override
    public void execute() {
        logic.cdCommand();
    }
}
