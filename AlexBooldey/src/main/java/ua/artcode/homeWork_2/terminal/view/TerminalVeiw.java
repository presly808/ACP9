package ua.artcode.homeWork_2.terminal.view;


import ua.artcode.homeWork_2.terminal.control.CommandCd;
import ua.artcode.homeWork_2.terminal.control.InvokerCommand;
import ua.artcode.homeWork_2.terminal.control.Recipient;
import ua.artcode.homeWork_2.tools.FileTools;
import ua.artcode.homeWork_2.tools.ScarnerTools;
import ua.artcode.homeWork_2.tools.StringParseTools;

import java.io.FileNotFoundException;

public class TerminalVeiw implements ITerminalView {
    InvokerCommand invokerCommand = null;
    private String innerData = null;


    public TerminalVeiw() {
        showCommands();
        runCommand();
    }

    private void runCommand() {
        innerData = ScarnerTools.keyboardString("");
        Recipient recipient = new Recipient(innerData);
        invokerCommand = new InvokerCommand(new CommandCd(recipient), null, null,
                null, null, null, null, null, null, null);
        actionByChoose(innerData);
    }

    @Override
    public void showCommands() {
        try {
            String FILE_HELP_PATH = "C:\\Users\\collide\\Documents\\Idea\\Home(PRO)\\src\\homeWork_2\\terminal\\HelpFile.txt";
            System.out.println(FileTools.read(FILE_HELP_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void actionByChoose(String innerData) {
        switch (StringParseTools.parseCommand(innerData)) {
            case "exit":
                System.exit(0);
                break;
            case "cd":
                invokerCommand.getCommandCd();
                runCommand();
                break;
            default:
                runCommand();
                break;
        }
    }
}
