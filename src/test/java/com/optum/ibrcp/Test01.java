package com.optum.ibrcp;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.HashMap;
import java.util.Map;


@ExtendWith({ProcessEngineExtension.class})
@SpringJUnitConfig(locations = {"/camunda.cfg.xml"})
@ActiveProfiles(profiles = {"test"})
public class Test01 {

    ProcessEngine processEngine;

    @Test
    @Deployment(resources = {"process.bpmn"})
    public void test01() {
        RuntimeService rts = processEngine.getRuntimeService();
        Map<String, Object> vars = new HashMap<>();
        ProcessInstance procInst = rts.startProcessInstanceByKey("junit5-process", "key01", vars);

        return;
    }
}
