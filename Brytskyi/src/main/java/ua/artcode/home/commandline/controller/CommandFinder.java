package ua.artcode.home.commandline.controller;

import ua.artcode.home.commandline.model.commands.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 05.10.15
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public class CommandFinder {

    FileHelper fileHelper = new FileHelper();

    //this class has  to define command from comandline and start it

    CommandStarter commandStarter = new CommandStarter();


    // stroka is String which method get after pushing enter in command line
    public void definCommand(String stroka) {
        String atribute1 = null;
        String atribute2 = null;
        String comm = toSpaceWord(stroka);
        stroka = stroka.substring(comm.length());
        if (stroka.length() >= 2) {
            atribute1 = toSpaceWord(stroka);
            stroka = stroka.substring(atribute1.length() + 1);
        }
        if (stroka.length() >= 2) {
            atribute2 = toSpaceWord(stroka);

        }

        doCommand(comm, atribute1, atribute2);
    }

    private void doCommand(String command, String atrib1, String atrib2) {
        if (command.equals("help")) {
            commandStarter.runHelpCommand(new HelpCommand(fileHelper, atrib1));
        }
        if (command.equals("cd")) {
            commandStarter.runCDCommand(new CDCommand(fileHelper, atrib1));
        }
        if (command.equals("find")) {
            commandStarter.runFindCommand(new FindCommand(fileHelper, atrib1, atrib2));
        }
        if (command.equals("dir")) {
            commandStarter.runDirCommand(new DirCommand(fileHelper));
        }
        if (command.equals("type")) {
            commandStarter.runTypeCommand(new TypeCommand(fileHelper, atrib1));
        }
        if (command.equals("del")) {
            commandStarter.runDeleteCommand(new DeleteCommand(fileHelper, atrib1));
        }
        if (command.equals("rd")) {
            commandStarter.runRDCommand(new RDCommand(fileHelper, atrib1));
        }
        if (command.equals("mkdir")) {
            commandStarter.runMkdirCommand(new MkdirCommand(fileHelper, atrib1));
        }
        if (command.equals("touch")) {
            commandStarter.runTouchCommand(new TouchCommand(fileHelper, atrib1));
        }
        if (command.equals("copy")) {
            commandStarter.runCopyCommand(new CopyCommand(fileHelper, atrib1, atrib2));
        }
        if (command.equals("compare") || command.equals("fc")) {
            commandStarter.equals(new CompareCommand(fileHelper, atrib1, atrib2));
        }

    }


    private String toSpaceWord(String stroka) {
        String res = "";
        if (stroka.length() <= 1) return null;
        for (int i = 0; i < stroka.length(); i++) {
            if (stroka.charAt(0) == ' ' && i == 0) i++;
            if (stroka.charAt(i) == ' ') return res;
            res = res + stroka.charAt(i);

        }
        return res;
    }

    public String currLocation() {
        return fileHelper.getCurrentLocation().getAbsolutePath();
    }

}
