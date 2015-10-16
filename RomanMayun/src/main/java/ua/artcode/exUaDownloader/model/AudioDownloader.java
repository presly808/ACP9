package ua.artcode.exUaDownloader.model;


import java.io.IOException;
import java.util.Map;

public class AudioDownloader extends AbstractDownloader {

    @Override
    public void download(String path,Map<String, String> map) throws IOException {
        downLoadHelper(path,map);

    }


}
