package com.example.lyhassignment.service;

import com.example.lyhassignment.entity.TaskDetail;
import com.example.lyhassignment.respository.TaskDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskDetailService {
    @Autowired
    private TaskDetailRepository taskDetailRepository;

    public TaskDetail findTaskDetailById(int tid){
        return taskDetailRepository.findTdById(tid);
    }

    public List<TaskDetail> findAllTaskDetail(){
        return taskDetailRepository.findAllTaskDetail();
    }

    public List<TaskDetail> findTaskDetailByTeacherId(int tid){
        return taskDetailRepository.findTaskDetailByTeacherId(tid);
    }

    public TaskDetail addTaskDetail(TaskDetail taskDetail){
        taskDetailRepository.save(taskDetail);
        return taskDetailRepository.refresh(taskDetail);
    }

    public TaskDetail findTdByTeacherAndTask(int tid, int aid){
        return taskDetailRepository.findTdByTeacherAndTask(tid, aid);
    }

    public TaskDetail updateTaskDetail(TaskDetail taskDetail){
        taskDetailRepository.save(taskDetail);
        return taskDetail;
    }
}
