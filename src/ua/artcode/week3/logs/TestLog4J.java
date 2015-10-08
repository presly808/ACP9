package ua.artcode.week3.logs;


import org.apache.log4j.*;

import java.io.IOException;

public class TestLog4J {


    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("main");

        /*
        logger.trace();
        logger.debug();
        logger.info();
        logger.warn();
        logger.error();
        logger.fatal();
        */

        ConsoleAppender newAppender = new ConsoleAppender(new HTMLLayout());
        logger.addAppender(newAppender);

        logger.addAppender(new FileAppender(new SimpleLayout(), "log.txt"));
        logger.info("User in system");

    }
}
