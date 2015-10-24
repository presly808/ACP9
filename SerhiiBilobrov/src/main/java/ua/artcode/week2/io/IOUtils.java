package ua.artcode.week2.io;

import java.io.*;


public class IOUtils {

    public static String readFile(String path) throws FileNotFoundException {
        InputStream is = new FileInputStream(path);
        String res = "";
        try {

            int b = 0;
            while((b = is.read()) != -1){
                res += (char)b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void writeViaPrintWriter(String path) throws IOException, InterruptedException {
        Writer writer = new PrintWriter(path);
        long l = System.currentTimeMillis();
        writer.write(" " + l);
        writer.flush();
        System.out.println(readFile(path));
    }

    public static String readFileReader(String path) throws FileNotFoundException {
        Reader is = new FileReader(path);
        String res = "";
        try {

            int b = 0;
            while((b = is.read()) != -1){
                res += (char)b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static byte[] getByteArr(InputStream is){
        ByteArrayOutputStream byos = new ByteArrayOutputStream();

        try {

            int b = 0;
            while((b = is.read()) != -1){
                byos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return byos.toByteArray();
    }

    public static void writeBytes(byte[] arr, String path) throws FileNotFoundException {
        OutputStream os = new FileOutputStream(path);
        try{
            os.write(arr);
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            if(os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    public static void writeToFile(String src, String path) throws IOException {
        Writer writer = null;
        try{
            writer = new FileWriter(path);
            writer.write(src);
            writer.flush();
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            if(writer != null)
                writer.close();
        }
    }

    public static void writeToFile2(String src, String path) throws IOException {
        // try with resources
        try(Writer writer = new FileWriter(path)){
            writer.write(src);
            writer.flush();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static boolean mkdir(String name){
        File file = new File(name); // file or dir
        // file.exists();
        // file.isFile()
        // file.isDirectory()
        // file.createNewFile()
        return file.mkdir();
    }

    public static void saveObject(Object obj, String path) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

        BufferedReader reader = new BufferedReader(new PushbackReader(new FileReader(path)));
        reader.read();

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


    public static InputStream getClassPathResource(String path){
        return IOUtils.class.getResourceAsStream(path);
    }




}
