package com.example.lyhassignment.service;

import com.example.lyhassignment.entity.Invigilate;
import com.example.lyhassignment.entity.InvigilateDetail;
import com.example.lyhassignment.respository.InvigilateDetailRepository;
import com.example.lyhassignment.respository.InvigilateRepository;
import com.example.lyhassignment.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvigilateService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvigilateRepository invigilateRepository;
    @Autowired
    private InvigilateDetailRepository invigilateDetailRepository;

    //获取所有监考
    public List<Invigilate> listAllInvigilate(){
        return invigilateRepository.listAll();
    }

    //获取指定教师的所有监考
    public List<InvigilateDetail> listTeacherInvigilates(int tid){
        return invigilateDetailRepository.listByTeacherId(tid);
    }

    //添加监考事项
    public Invigilate addInvigilate(Invigilate invigilate){
        invigilateRepository.save(invigilate);
        return invigilateRepository.refresh(invigilate);
    }

    //添加监考的详细信息
    public InvigilateDetail addInvigilateDetail(InvigilateDetail invigilateDetail){
        invigilateDetailRepository.save(invigilateDetail);
        return invigilateDetailRepository.refresh(invigilateDetail);
    }

    //获取指定id监考信息
    public Invigilate findInvigilateById(int iid){
        return invigilateRepository.find(iid);
    }



}
