package com.example.lyhassignment.respository;

import com.example.lyhassignment.entity.TaskDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskDetailRepository extends CustomizedRepository<TaskDetail, Integer>{
    @Query("select td from TaskDetail td")
    List<TaskDetail> findAllTaskDetail();

    @Query("select td from TaskDetail td where td.id=:tid")
    TaskDetail findTdById(@Param("tid") int tid);

    @Query("select td from TaskDetail td where td.teacher.id=:tid")
    List<TaskDetail> findTaskDetailByTeacherId(@Param("tid") int tid);

    @Query("select td from TaskDetail td where td.teacher.id=:tid and td.task.id=:aid")
    TaskDetail findTdByTeacherAndTask(@Param("tid") int tid, @Param("aid") int aid);
}
