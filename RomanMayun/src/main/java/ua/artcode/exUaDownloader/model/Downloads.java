package ua.artcode.exUaDownloader.model;


import java.util.*;

public class Downloads {
    public static final String DEFAULT_DIRECTORY = "D:/";
    public static String directory = DEFAULT_DIRECTORY;
    public static String url;
    public static final Map<String, AbstractDownloader>ALL_FORMATS;

    static {
        Map<String, AbstractDownloader> map = new HashMap<>();
        map.put("audio",new AudioDownloader());
        map.put("video",null);
        map.put("images",null);
        map.put("other",null);
        ALL_FORMATS = Collections.unmodifiableMap(map);
    }

    public static final String[] LIST_OF_FORMATS = {"audio","video","images","other"};

    public static final List<String > MUSIC_FORMATS = Collections.unmodifiableList(
            Arrays.asList(new String []{".mp3",".flac",".m4p",".ogg",".wav",".webm",".aiff",".au"}));

    public static final List<String > VIDEO_FORMATS = Collections.unmodifiableList(
            Arrays.asList(new String []{".wmv",".vob",".vid",".vcd",".swf",".rm",".mpg",".mpeg",".mov",".mp4",".mod",
                    ".mkv", ".f4v",".dat",".avi",".asx",".asf",".3gp",".3g2"}));

    public static final List<String > IMAGE_FORMATS = Collections.unmodifiableList(
            Arrays.asList(new String []{".apt", ".bmp", ".dds", ".djvu", ".dng", ".gbr", ".gif", ".gz", ".iff", ".iso",
                    ".jpeg", ".jpg", ".kdc", ".mng", ".php", ".png", ".pot", ".psd", ".pspimage", ".scr", ".tga",
                    ".thm", ".tif", ".tiff", ".vst", ".xcf"}));


}
