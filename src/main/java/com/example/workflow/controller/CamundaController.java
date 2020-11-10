package com.example.workflow.controller;



import com.example.workflow.model.LifeObject;
import com.example.workflow.model.LifeObjectMain;
import com.example.workflow.payload.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.persistence.StrongUuidGenerator;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CamundaController {

    private static final Logger LOGGER = LogManager.getLogger(CamundaController.class);

    private static final String TRACE_ID = "Trace_Id";

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/start-life")
    public ResponseEntity<Object> postProvision(@RequestHeader(value = "Trace_Id") String traceId,
                                                @RequestBody LifeObjectMain dto) throws Exception {
        LOGGER.info("life-started : traceId={}|{}", traceId, dto);
        long startTime = System.currentTimeMillis();

        LifeObject lifeObject = dto.getLifeObject();

        if(lifeObject == null) {
            throw new Exception("No life object found");
        }

        Map<String, Object> map = new HashMap<>();

        try {
            ObjectValue provisionObject = Variables.objectValue(lifeObject)
                    .serializationDataFormat(Variables.SerializationDataFormats.JAVA).create();

            ProcessInstance pc = processEngine.getRuntimeService().startProcessInstanceByKey("human-life-cycle-today", new StrongUuidGenerator().getNextId(),
                    Variables.putValue("Trace_Id", traceId)
                            .putValue("lifeObject", provisionObject));

            map.put("businessKey", pc.getBusinessKey());
            map.put("procInstance", pc.getProcessInstanceId());
            map.put("lifeObject", lifeObject);

        } catch (Exception e) {
            LOGGER.info(e);
        }

        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime);
        LOGGER.info("life-ended : traceId={}|{}|{}", traceId, timeTaken, map);

        return new ResponseEntity<>(getResponseMessage("postProvisionObject", map, "0000", null, null, "success", null), HttpStatus.CREATED);
    }

    private ResponseMessage getResponseMessage(String dataType, Object obj, String code, String origin, String details,
                                               String message, String type) {
        HashMap<String, Object> data = new HashMap<>();
        data.put(dataType, obj);
        return new ResponseMessage.ResponseBuilder()
                .setCode(code)
                .setMessage(message)
                .setTraceId(request.getHeader(TRACE_ID))
                .setType(type)
                .setOrigin(origin)
                .setDetails(details)
                .setData(data)
                .build();
    }

}
