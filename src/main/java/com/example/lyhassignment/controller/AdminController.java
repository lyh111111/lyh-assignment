package com.example.lyhassignment.controller;

import com.example.lyhassignment.entity.*;
import com.example.lyhassignment.respository.InvigilateRepository;
import com.example.lyhassignment.service.InvigilateService;
import com.example.lyhassignment.service.TaskDetailService;
import com.example.lyhassignment.service.TaskService;
import com.example.lyhassignment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private InvigilateRepository invigilateRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private InvigilateService invigilateService;
    @Autowired
    private TaskDetailService taskDetailService;
    //增加用户
    @PostMapping("/addUser")
    public Map addUser(@RequestBody User user){
        userService.addUser(user);
        return Map.of("newuser", user);
    }
    //增加监考科目
    @PostMapping("/addInvigilate")
    public Map addInvigilate(@RequestBody Invigilate invigilate){
        invigilateRepository.save(invigilate);
        return Map.of("newInvigilate", invigilate);
    }
    //增加任务
    @PostMapping("/addTask")
    public Map addTask(@RequestBody Task task){
        taskService.addTask(task);
        return Map.of("newTask:", task);
    }
    //增加监考明细
    @PostMapping("/addInvigilateDetail")
    public Map addInvigilateDetail(@RequestBody InvigilateDetail invigilateDetail){
        invigilateService.addInvigilateDetail(invigilateDetail);
        return Map.of("newInvigilateDetail", invigilateDetail);
    }
    //增加任务明细
    @PostMapping("/addTaskDetail")
    public Map addTaskDetail(@RequestBody TaskDetail taskDetail){
        LocalDateTime nowTime = LocalDateTime.now();
        if(nowTime.isAfter(taskDetail.getCompleteTime())){
            taskDetail.setIsInTime(1);
        }else{
            taskDetail.setIsInTime(0);
        }
        taskDetailService.addTaskDetail(taskDetail);
        return Map.of("newTaskDetail:", taskDetail);
    }
    //修改用户信息
    @PostMapping("/updateUser")
    public Map updateUser(@RequestBody User user){
        Optional.ofNullable(userService.getUserById(user.getId()))
                .ifPresentOrElse(u -> {
                    u.setPassword(user.getPassword());
                    u.setPhoneNumber(user.getPhoneNumber());
                    u.setBriefIntroduction(user.getBriefIntroduction());
                    u.setTitle(user.getTitle());
                    u.setAuthority(user.getAuthority());
                    userService.updateUser(user);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "无此用户！！");
                });
        return Map.of("newUser:", user);
    }
}
