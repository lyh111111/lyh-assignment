package com.example.lyhassignment.respository;

import com.example.lyhassignment.entity.Invigilate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvigilateRepository extends CustomizedRepository<Invigilate, Integer> {
    //指定监考id查询监考信息
    @Query("select i from Invigilate i where i.id=:iid")
    Invigilate find(@Param("iid") int iid);

    //查询所有监考信息
    @Query("select i from Invigilate i")
    List<Invigilate> listAll();
}
