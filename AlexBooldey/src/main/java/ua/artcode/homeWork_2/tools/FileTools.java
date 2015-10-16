package ua.artcode.homeWork_2.tools;

import java.io.*;

public class FileTools {
    public static String read(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        exists(fileName);
        try {
            BufferedReader brIn = new BufferedReader(new FileReader(fileName));
            String s;
            while ((s = brIn.readLine()) != null) {
                sb = sb.append(s);
                sb = sb.append("\n");
            }
            brIn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (file.exists()) new FileNotFoundException(file.getName());
    }

    public static void showFolderContent(File file, String step) {
        String result = step + file.getName();
        if (file.isDirectory())
            System.out.println(result + "/");
        else
            System.out.println(result);

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                showFolderContent(f, step + "----");
            }
        }
    }

    public static void findByName(File file, String key) {
        if (file.getName().contains(key)) {
            System.out.println(file.getAbsolutePath());
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                findByName(f, key);
            }
        }
    }

    public static String findByNameWithReq(File file, String key) {
        String res = "";
        if (file.getName().contains(key)) {
            res = file.getAbsolutePath() + "\n";
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                res += findByNameWithReq(f, key);
            }
        }
        return res;
    }

    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();

    }

    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();

    }

    public static void copy(String sourcePath, String destinationPath) {
        File source = new File(sourcePath);
        File dest = new File(destinationPath);

        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static File changeLocation(String path) {
        if (isDirectory(path))
            return new File(path);
        return new File(".");
    }

    public static String getPath(File file) {
        return file.getPath();

    }

    public static String getAbsolutePath(File file) {
        return file.getAbsolutePath();
    }

    public static String showFileContent(File file) {
        InputStream is = null;
        StringBuilder builder = new StringBuilder();
        try {
            is = new FileInputStream(file);
            int ch;
            while ((ch = is.read()) != -1) {
                builder.append((char) ch);
            }

            System.out.println(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static boolean createDir(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            throw new IOException("Cannot create. File exist");
        }
        return file.mkdirs();
    }

    public static boolean createFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            throw new IOException("Cannot create. File exist");
        }
        return file.createNewFile();
    }

    public static boolean deleteFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("Cannot delete. File doesn't exist");
        }
        return file.delete();
    }
}


