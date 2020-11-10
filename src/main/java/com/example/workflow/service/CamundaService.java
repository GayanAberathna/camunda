package com.example.workflow.service;

import com.example.workflow.model.LifeObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CamundaService {

    private static final Logger LOGGER = LogManager.getLogger(CamundaService.class);
    public static final String TRACE_ID = "Trace_Id";
    public static final String WORKER = "worker-";
    public static final String EXECUTION_ID = "execution-id";
    public static final String TOPIC = "topic";


    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    private static final long LOCK_DURATION = 25920000000l;

    public void enterTheSchool(String topic) {

        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        String workerId = WORKER + topic + "-" + UUID.randomUUID().toString();

        List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(10, workerId)
                .topic(topic, LOCK_DURATION)
                .execute();

        for (LockedExternalTask task : tasks) {
            try {
                String traceId = (String) task.getVariables().get(TRACE_ID);
                LifeObject lifeObject = (LifeObject) runtimeService.getVariable(task.getExecutionId(), "lifeObject");
                String executionId = task.getExecutionId();

                Map<String, Object> variables = new HashMap<>();
                variables.put(EXECUTION_ID, executionId);
                variables.put(TOPIC, topic);

                finishExecutionTask(externalTaskService, workerId, task, variables);


            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }

    public void faceUniversityEntrance(String topic) {

        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        String workerId = WORKER + topic + "-" + UUID.randomUUID().toString();

        List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(10, workerId)
                .topic(topic, LOCK_DURATION)
                .execute();

        for (LockedExternalTask task : tasks) {
            try {
                String executionId = task.getExecutionId();
                String examPass = "examPass";
                Random rd = new Random();

                Map<String, Object> variables = new HashMap<>();
                variables.put(EXECUTION_ID, executionId);
                variables.put(TOPIC, topic);
                variables.put(examPass,rd.nextBoolean());

                finishExecutionTask(externalTaskService, workerId, task, variables);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }

    }

    public void enterTheUniversity(String topic) {
        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        String workerId = WORKER + topic + "-" + UUID.randomUUID().toString();

        List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(10, workerId)
                .topic(topic, LOCK_DURATION)
                .execute();

        for (LockedExternalTask task : tasks) {
            try {
                String executionId = task.getExecutionId();
                Map<String, Object> variables = new HashMap<>();
                variables.put(EXECUTION_ID, executionId);
                variables.put(TOPIC, topic);

                finishExecutionTask(externalTaskService, workerId, task, variables);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }

    public void doTheJob(String topic) {
        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        String workerId = WORKER + topic + "-" + UUID.randomUUID().toString();

        List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(10, workerId)
                .topic(topic, LOCK_DURATION)
                .execute();

        for (LockedExternalTask task : tasks) {
            try {
                String executionId = task.getExecutionId();
                Map<String, Object> variables = new HashMap<>();
                variables.put(EXECUTION_ID, executionId);
                variables.put(TOPIC, topic);

                finishExecutionTask(externalTaskService, workerId, task, variables);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }

    public void joinHomeBusiness(String topic) {
        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        String workerId = WORKER + topic + "-" + UUID.randomUUID().toString();

        List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(10, workerId)
                .topic(topic, LOCK_DURATION)
                .execute();

        for (LockedExternalTask task : tasks) {
            try {
                String executionId = task.getExecutionId();
                Map<String, Object> variables = new HashMap<>();
                variables.put(EXECUTION_ID, executionId);
                variables.put(TOPIC, topic);

                finishExecutionTask(externalTaskService, workerId, task, variables);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }


    private void finishExecutionTask(ExternalTaskService externalTaskService, String workerId,
                                     LockedExternalTask task, Map<String, Object> variables) throws InterruptedException {
        Thread.sleep(10000);

        externalTaskService.complete(task.getId(), workerId, variables);
    }
}
