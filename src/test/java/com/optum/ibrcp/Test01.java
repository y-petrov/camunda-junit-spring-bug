package com.optum.ibrcp;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 extends AbstractProcessEngineRuleTest {

    @Autowired
    public RuntimeService rts;

    @Test
    @Deployment(resources = {"process.bpmn"})
    public void test01() {
        Map<String, Object> vars = new HashMap<>();

        ProcessInstance procInst = rts.startProcessInstanceByKey("junit5-process", "key01", vars);

        BpmnAwareTests.assertThat(procInst).isEnded();

        return;
    }
}
