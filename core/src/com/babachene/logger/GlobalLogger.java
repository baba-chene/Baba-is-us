package com.babachene.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GlobalLogger {

    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static public void setup(boolean file) throws IOException {

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.INFO);

        if (file) {
            fileTxt = new FileHandler("logs.txt");
            formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);
        }
    }
}
