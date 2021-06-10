package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteNumber = 10;
        short shortNumber = 29;
        int intNumber = 3;
        long longNumber = 365;
        float floatNumber = 24.0f;
        double doubleNumber = 48.3;
        boolean bool = true;
        char symbol = '@';

        LOG.trace("trace message");
        LOG.debug("Byte number: {}: short number: {}", byteNumber, shortNumber);
        LOG.info("Int number: {}: long number: {}", intNumber, longNumber);
        LOG.warn("Float number: {}; doubleNumber: {}", floatNumber, doubleNumber);
        LOG.error("Boolean: {}; char: {}", bool, symbol);
    }
}
