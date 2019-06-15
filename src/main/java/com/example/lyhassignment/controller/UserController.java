package com.example.lyhassignment.controller;

import com.example.lyhassignment.entity.Invigilate;
import com.example.lyhassignment.entity.InvigilateDetail;
import com.example.lyhassignment.entity.User;
import com.example.lyhassignment.service.InvigilateService;
import com.example.lyhassignment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private InvigilateService invigilateService;

    @GetMapping("/getOnesAll/{tid}")
    public Map getOnesAll(@PathVariable int tid){
        List<InvigilateDetail> invigilateDetails = invigilateService.listTeacherInvigilates(tid);
        return Map.of("getOnesAll:", invigilateDetails);
    }

    @PostMapping("/deliberate")
    public Map deliberate(@RequestBody Invigilate invigilate){
        List<User> users = userService.findMinInvigilate();
        users.forEach(u -> {
            log.debug(u.getName());
        });
        InvigilateDetail invigilateDetail = new InvigilateDetail();
        User teacher = new User();
        teacher.setId(users.get(0).getId());
        int t = users.get(0).getInvigilateNumber();
        users.get(0).setInvigilateNumber(t+1);
        invigilateDetail.setTeacher(teacher);
        invigilateDetail.setInvigilate(invigilate);
        invigilateService.addInvigilateDetail(invigilateDetail);
        return Map.of("deliberate:", invigilateDetail);
    }
}
