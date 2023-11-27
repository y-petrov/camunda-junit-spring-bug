package com.optum.ibrcp.services;

import com.optum.ibrcp.spring.SomeSpringBean;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ServiceDelegate implements JavaDelegate {

    private static final Logger logger = Logger.getLogger(ServiceDelegate.class.getCanonicalName());

    @Autowired
    private SomeSpringBean theBean;

    public ServiceDelegate() {
        logger.info(">> <init()>");
        logger.info("<< <init()>");
        return;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info(">> execute()");

        logger.log(Level.INFO, "theBean=[{0}]", theBean);
        theBean.method();
        logger.info("<< execute()");
    }
}
