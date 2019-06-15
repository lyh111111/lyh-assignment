package com.example.lyhassignment.respository;

import com.example.lyhassignment.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CustomizedRepository<User, Integer>{
    @Query("SELECT u FROM User u WHERE u.name=:name")
    User findByName(@Param("name") String name);

    @Query("select u from User u where u.id=:tid")
    User findById(@Param("tid") int id);

    //查询最少监考的用户群
    @Query("select u from User u where u.invigilateNumber = (select min(invigilateNumber) from User)")
    List<User> findMin();
}
