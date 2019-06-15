package com.example.lyhassignment.component;

import com.example.lyhassignment.entity.InvigilateDetail;
import com.example.lyhassignment.entity.TaskDetail;
import com.example.lyhassignment.service.InvigilateDetailService;
import com.example.lyhassignment.service.TaskDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class MyTimer {
    @Autowired
    private InvigilateDetailService invigilateDetailService;
    @Autowired
    private TaskDetailService taskDetailService;

    @Scheduled(cron = "0 0 * * * *")
    public void deliverMessage(){
        List<InvigilateDetail> invigilateDetails = invigilateDetailService.getAllInvigilateDetails();
        LocalDateTime time = LocalDateTime.now();
        invigilateDetails.forEach(i -> {
            if (i.getStartTime().isAfter(time)){
                log.debug(i.getTeacher().getName());
            }
            invigilateDetailService.updateInvigilateDetail(i);
        });
    }

    @Scheduled(cron = "30 * * * * *")
    public void changeTaskState(){
        List<TaskDetail> taskDetails = taskDetailService.findAllTaskDetail();
        LocalDateTime now = LocalDateTime.now();
        for (TaskDetail t : taskDetails) {
            if(now.isAfter(t.getCompleteTime())){
                t.setIsInTime(0);
            }
            taskDetailService.updateTaskDetail(t);
        }
    }
}
