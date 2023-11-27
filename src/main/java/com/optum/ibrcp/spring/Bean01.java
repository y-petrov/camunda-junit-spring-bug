package com.optum.ibrcp.spring;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Bean01 {

    private static final Logger logger = Logger.getLogger(Bean01.class.getCanonicalName());

    public Bean01() {
        logger.info(">> <init()>");
        logger.info("<< <init()>");
        return;
    }

    public void method() {
        logger.info(">> method()");
        logger.info("<< method()");
    }
}
