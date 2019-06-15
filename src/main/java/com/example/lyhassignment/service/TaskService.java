package com.example.lyhassignment.service;

import com.example.lyhassignment.entity.Task;
import com.example.lyhassignment.respository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task findTaskById(int tid){
        return taskRepository.findById(tid);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task addTask(Task task){
        taskRepository.save(task);
        return taskRepository.refresh(task);
    }

}
