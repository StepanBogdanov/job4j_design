package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());


    public static void main(String[] args) {
        byte byteNumber = 13;
        short shortNumber = 34;
        int intNumber = 281;
        long longNumber = 342L;
        float floatNumber = 3.52f;
        double doubleNumber = 7.34325;
        char character = 'a';
        boolean bool = true;

        LOG.debug("debug message {} {}", byteNumber, shortNumber);
        LOG.info("info message {} {}", intNumber, longNumber);
        LOG.warn("warn message {} {}", floatNumber, doubleNumber);
        LOG.error("error message {} {}", character, bool);
    }
}