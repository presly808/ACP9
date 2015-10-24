package ua.artcode.homeWork_2.terminal.model;

public class LogicCd implements LogicCommand {

    public LogicCd(String command) {
        //super(command);
    }

    @Override
    public void action() {
        System.out.println("Fuck you!!");
    }

    @Override
    public void help() {

    }
}
