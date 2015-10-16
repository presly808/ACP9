package ua.artcode.homeWork_2.terminal.control;


import ua.artcode.homeWork_2.terminal.model.LogicCd;

public class Recipient {
    private String string;

    public Recipient(String string) {
        this.string = string;
    }

    public void cdCommand() {
        new LogicCd(string).action();
    }
}

