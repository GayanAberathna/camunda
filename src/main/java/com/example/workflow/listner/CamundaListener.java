package com.example.workflow.listner;

import com.example.workflow.service.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CamundaListener {

    @Autowired
    private CamundaService camundaService;

    @Scheduled(fixedDelay = 5 * 1000L, initialDelay = 3 * 1000L)
    public void scheduledEnterTheSchool() {
        camundaService.enterTheSchool("enter-the-school");
    }

    @Scheduled(fixedDelay = 5 * 1000L, initialDelay = 3 * 1000L)
    public void scheduledFaceUniversityEntranceExam() {
        camundaService.faceUniversityEntrance("face-university-entrance-exam");
    }

    @Scheduled(fixedDelay = 5 * 1000L, initialDelay = 3 * 1000L)
    public void scheduledEnterTheUniversity() {
        camundaService.enterTheUniversity("enter-the-university");
    }

    @Scheduled(fixedDelay = 5 * 1000L, initialDelay = 3 * 1000L)
    public void scheduledDotheJob() {
        camundaService.doTheJob("do-the-job");
    }

    @Scheduled(fixedDelay = 5 * 1000L, initialDelay = 3 * 1000L)
    public void scheduledJoinHomeBusiness() {
        camundaService.joinHomeBusiness("join-home-business");
    }
}
