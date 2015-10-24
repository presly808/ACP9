package ua.artcode.home.commandline.controller;

import ua.artcode.home.commandline.controller.helperUtilies.MyFileSearch;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 05.10.15
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
public class FileHelper {
    //reciever
    //here is a logic of all the commands
    private File currentLocation = new File("D:/");

    public FileHelper() {

    }


//    +       - show all available commands        help
//    +       - changeCurrentLocation              cd
//    +       - find file(dir)                     find
//    +       - show directoryContent              dir
//    +       - show file (content)                type
//    +       - delete file or dir                 del , rd
//    +       - create dir                         mkdir
//    +       - create file                        ? )))
//            +       - show folder structure              tree
//    +       - copy file                          copy
//    +       - compare content of files           fc

    public void showHelp(String command) {
        String absPath = "src/ua.artcode.home.commandline/controller/helpText";
        // if command == null show all commands help
        // if command != null then look if we have such command. if command exist show help else give message it doesn`t exist
        if (command == null) {
            showAllHelp(absPath);
        } else {
            showCommandHelp(command, absPath);
        }
    }


    private void showAllHelp(String absPath) {
        // show all commands help one after another
        String abPath = absPath;
        File textdirectory = new File(abPath);
        String[] commands = textdirectory.list();
        for (String command : commands) {
            try {
                Reader r = new FileReader(abPath + "/" + command);
                String res = "";
                int a = 0;
                while ((a = r.read()) != -1) {
                    res += (char) a;
                }
                System.out.println(res);
                System.out.println();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public File getCurrentLocation() {
        return currentLocation;
    }


    private void showCommandHelp(String command, String absPath) {
        //look in the directory if it contains info about such command and show it, else say that wrong command
        String abPath = absPath;
        File textdirectory = new File(abPath);
        String[] commands = textdirectory.list();
        boolean contains = false;
        for (String com : commands) {
            if (com.contains(command)) {
                contains = true;
                break;
            }
        }
        if (contains) {
            try {
                Reader r = new FileReader(abPath + "/" + command + ".txt");
                String res = "";
                int a = 0;
                while ((a = r.read()) != -1) {
                    res += (char) a;
                }
                System.out.println(res);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Wrong command, try again");
        }
    }

    public void changeCurrLocation(String atribute) {
        if (locationController(atribute) != null) currentLocation = locationController(atribute);
    }


    // location controller gives possibility to look if the file or directory exists to
    // do something with them
    private File locationController(String atribute) {
        File location = new File(currentLocation.getAbsolutePath());
        if (atribute.equals("OlexandrBrytskyi/src/test")) {
            if (location.getAbsolutePath().length() > 3) {
                location = new File(location.getParent());
                return location;
            }
        } else {
            if (atribute.charAt(1) == ':') {
                if (atribute.charAt(2) != '/') {
                    System.out.println("wrong path!");
                    return null;
                }
                File newPath = new File(atribute);
                if (newPath.exists() && newPath.isDirectory()) {
                    location = newPath;
                    return location;
                }
            } else {
                File newPath = new File(location + "/" + atribute);
                if (newPath.exists() && newPath.isDirectory()) {
                    location = newPath;
                    return location;
                }
            }
        }
        System.out.println("wrong path");
        return null;

    }

    // realisation of FindCommand
    public void find(String objtosearch, String atr2) {
        if (objtosearch != null) {
            if (atr2 == null) {
                new MyFileSearch(objtosearch, currentLocation.getAbsolutePath());
            } else {
                if (locationController(atr2) != null) {
                    new MyFileSearch(objtosearch, locationController(atr2).getAbsolutePath());
                } else {
                    System.out.println("Chek the path you enter");
                }
            }
        }
    }

    // shows current directory content
    public void dir() {
        String[] content = currentLocation.list();
        if (content.length > 0) {
            for (String s : content) {
                System.out.println(s);
            }
        } else {
            System.out.println("Directory is empty");
        }
    }

    //type command realisation
    public void type(String atribute) {
        File curr = fileController(atribute);
        if (curr != null) {
            if (!curr.isDirectory()) {
                try {
                    FileReader r = new FileReader(curr);
                    String res = "";
                    int ss = 0;
                    while ((ss = r.read()) != -1) {
                        res += (char) ss;
                    }
                    System.out.println(res);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("it is directory, use dir to look trough content of diretory");
            }
        }
    }

    private File fileController(String atribute) {
        File location = new File(currentLocation.getAbsolutePath());

        if (atribute.charAt(1) == ':') {
            if (atribute.charAt(2) != '/') {
                System.out.println("wrong path!");
                return null;
            }
            File newPath = new File(atribute);
            if (newPath.exists() && newPath.isFile()) {
                location = newPath;
                return location;
            }
        } else {
            File newPath = new File(location + "/" + atribute);
            if (newPath.exists() && newPath.isFile()) {
                location = newPath;
                return location;
            }
        }

        System.out.println("wrong path");
        return null;

    }

    //realisation of del command
    public void delete(String file) {
        File curr = fileController(file);
        if (curr != null && curr.canExecute()) {
            curr.delete();
        } else {
            System.out.println("File doesn`t exist or cant delete");
        }
    }

    //realisation of rd command
    public void rd(String path) {
        File curr = locationController(path);
        if (curr != null) {
            curr.delete();
        } else {
            System.out.println("Directory doesn`t exist or cant delete");
        }
    }


    //realisation mkdir
    public void mkdir(String name) {
        if (name.contains(".")) {
            System.out.println("cant make");
            return;
        }
        File newDirec = new File(currentLocation + "/" + name);
        if (newDirec.mkdir()) {
            System.out.println("success");
        } else {
            System.out.println("Cant make such directory");
        }
    }

    //realisation touch
    public void touch(String name) {
        File newFile = new File(currentLocation + "/" + name);
        try {
            newFile.createNewFile();
            System.out.println("success");
        } catch (IOException e) {
            System.out.println("Cant make such directory");
            e.printStackTrace();
        }
    }


    //copy file  copy
    public void copyDirectory(String sourceLocationn, String targetLocationn) {
        File targetLocation = locationController(targetLocationn);
        File direct = locationController(sourceLocationn);
        File file = fileController(sourceLocationn);
        File sourceLocation;
        if (direct == null) {
            sourceLocation = file;
        } else {
            sourceLocation = direct;
        }

        if (sourceLocation.isDirectory()) {

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]).getAbsolutePath(),
                        new File(targetLocation, children[i]).getAbsolutePath());
            }
        } else {

            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(sourceLocation);
                out = new FileOutputStream(targetLocation);
                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //compare file content
    public void compare(String file1, String file2) {
        File one = fileController(file1);
        File two = fileController(file2);
        InputStream in1 = null;
        InputStream in2 = null;
        try {
            in1 = new FileInputStream(one);
            in2 = new FileInputStream(two);
            int r;
            while ((r = in1.read()) != -1) {
                if (in2.read() != r) {
                    System.out.println("Files differs");
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, file doesn`t exist" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("files are similar");

    }

    //show tree


}
