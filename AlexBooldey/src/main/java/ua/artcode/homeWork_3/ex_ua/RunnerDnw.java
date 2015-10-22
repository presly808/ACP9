package ua.artcode.homeWork_3.ex_ua;


import ua.artcode.homeWork_3.ex_ua.control.ControlDownload;
import ua.artcode.homeWork_3.ex_ua.model.FileDownloader;
import ua.artcode.homeWork_3.ex_ua.model.PageParser;
import ua.artcode.homeWork_3.ex_ua.veiw.ViewDownloader;


public class RunnerDnw {
    public static void main(String[] args) {
        new ControlDownload(new ViewDownloader(),new PageParser(),new FileDownloader());
    }
}
