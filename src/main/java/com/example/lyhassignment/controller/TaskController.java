package com.example.lyhassignment.controller;

import com.example.lyhassignment.entity.TaskDetail;
import com.example.lyhassignment.service.TaskDetailService;
import com.example.lyhassignment.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskDetailService taskDetailService;

    @GetMapping("/getAllTasks")
    public Map getAllTasks(){
        return Map.of("AllTasks:", taskService.getAllTasks());
    }

    @GetMapping("/task/{tid}")
    public Map getTaskById(@PathVariable int tid){
        return Map.of("task:", taskService.findTaskById(tid));
    }

    @GetMapping("/getAllTaskDetail")
    public Map getAllTaskDetail(){
        return Map.of("allTaskDetail:", taskDetailService.findAllTaskDetail());
    }

    @GetMapping("/taskDetail/{tid}")
    public Map getTaskDetailById(@PathVariable int tid){
        return Map.of("taskDetail:", taskDetailService.findTaskDetailById(tid));
    }

    @GetMapping("/teacher/{tid}/task")
    public Map getTaskDetailByTeacherId(@PathVariable int tid){
        return Map.of("teacherTaskDetail:", taskDetailService.findTaskDetailByTeacherId(tid));
    }

    @PostMapping("/teacher/{tid}/task/{aid}/reply")
    public Map replyTask(@PathVariable int tid, @PathVariable int aid, @RequestBody TaskDetail taskDetail){
        Optional.ofNullable(taskDetailService.findTdByTeacherAndTask(tid, aid))
                .ifPresentOrElse(ta ->{
                    ta.setIsComplete(1);
                    ta.setReply(taskDetail.getReply());
                    taskDetailService.updateTaskDetail(ta);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "无此详细任务！！");
                });
        return Map.of("afterReply:", taskDetail);
    }
}
