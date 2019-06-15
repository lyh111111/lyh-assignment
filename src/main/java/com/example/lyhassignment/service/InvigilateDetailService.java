package com.example.lyhassignment.service;

import com.example.lyhassignment.entity.InvigilateDetail;
import com.example.lyhassignment.respository.InvigilateDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class InvigilateDetailService {
    @Autowired
    private InvigilateDetailRepository invigilateDetailRepository;

    public InvigilateDetail getAccurateInvigilate(int tid, int iid){
        return invigilateDetailRepository.find(iid,tid);
    }

    public List<InvigilateDetail> getAllInvigilateDetails(){
        return invigilateDetailRepository.listAll();
    }

    public InvigilateDetail updateInvigilateDetail(InvigilateDetail invigilateDetail){
        invigilateDetailRepository.save(invigilateDetail);
        return invigilateDetail;
    }
}
