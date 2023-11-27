package com.optum.ibrcp.spring;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SomeSpringBean {

    private static final Logger logger = Logger.getLogger(SomeSpringBean.class.getCanonicalName());

    public SomeSpringBean() {
        logger.info(">> <init()>");
        logger.info("<< <init()>");
        return;
    }

    public void method() {
        logger.info(">> method()");
        logger.info("<< method()");
    }
}
