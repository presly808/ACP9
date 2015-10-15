package ua.artcode.week3.logs;


import org.apache.log4j.Logger;

/**
 * Created by serhii on 08.10.15.
 */
public class TestLog4JWithProp {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("simple");
        logger.info("test message");
    }
}
