package com.example.lyhassignment.respository;

import com.example.lyhassignment.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CustomizedRepository<Task, Integer> {
    @Query("select t from Task t where t.id=:tid")
    Task findById(@Param("tid") int tid);

    @Query("select t from Task t")
    List<Task> findAll();

}
