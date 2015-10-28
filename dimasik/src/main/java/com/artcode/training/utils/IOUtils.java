package com.artcode.training.utils;

import java.io.*;

public class IOUtils {

    public static final String EMPTY_STRING = "";
    public static final String INCORRECT_FOLDER_NAME_PREFIX = "^\\.+";
    public static final String INCORRECT_FOLDER_NAME_CHARACTERS = "[\\\\/:*?\"<>|]";

    public static String makeCorrectFolderName(String title) {
        return title.replace(INCORRECT_FOLDER_NAME_PREFIX, EMPTY_STRING).replaceAll(INCORRECT_FOLDER_NAME_CHARACTERS, EMPTY_STRING);
    }


    public static byte[] getByteArr(InputStream is) {
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        try {
            int b;
            while ((b = is.read()) != -1) {
                byos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byos.toByteArray();
    }


    public static boolean recreateFolder(String title) {
        File folder = new File(title);
        if (folder.exists()) {
            for (File file : folder.listFiles()) {
                file.delete();
            }
            folder.delete();
        }
        return folder.mkdir();
    }

    public static String readAll(InputStream is){
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = "";

        try {
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
}
