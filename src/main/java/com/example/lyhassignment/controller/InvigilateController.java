package com.example.lyhassignment.controller;

import com.example.lyhassignment.respository.InvigilateRepository;
import com.example.lyhassignment.service.InvigilateDetailService;
import com.example.lyhassignment.service.InvigilateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class InvigilateController {
    @Autowired
    private InvigilateService invigilateService;
    @Autowired
    private InvigilateRepository invigilateRepository;
    @Autowired
    private InvigilateDetailService invigilateDetailService;

    //获取所有监考科目
    @GetMapping("/getAllInvigilate")
    public Map getAllInvigilate(){
        return Map.of("allInvigilates", invigilateService.listAllInvigilate());
    }


    //根据教师和科目确定监考场次
    @GetMapping("/teacher/{tid}/invigilate/{iid}")
    public Map getAccurateInvigilate(@PathVariable int tid, @PathVariable int iid){
        return Map.of("invigilate:", invigilateDetailService.getAccurateInvigilate(tid, iid));
    }

}
