package com.example.lyhassignment.respository;


import com.example.lyhassignment.entity.InvigilateDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvigilateDetailRepository extends CustomizedRepository<InvigilateDetail, Integer>{
    //根据教师id查询监考场次
    @Query("select i from InvigilateDetail i where i.teacher.id=:tid")
    List<InvigilateDetail> listByTeacherId(@Param("tid") int tid);

    //查询所有监考场次
    @Query("select i from InvigilateDetail i")
    List<InvigilateDetail> listAll();

    @Query("select i from InvigilateDetail i where i.invigilate.id=:iid and i.teacher.id=:tid")
    InvigilateDetail find(@Param("iid") int iid, @Param("tid") int tid);

}
