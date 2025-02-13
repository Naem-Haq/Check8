package com.check.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataLogger {

    private static final Logger logger = LoggerFactory.getLogger(DataLogger.class);

    private DataLogger() {
    }
    public static void info(String message) {
        logger.info(message);
    }
    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

}
