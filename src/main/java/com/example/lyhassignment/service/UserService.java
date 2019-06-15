package com.example.lyhassignment.service;

import com.example.lyhassignment.entity.User;
import com.example.lyhassignment.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        userRepository.save(user);
        return userRepository.refresh(user);
    }

    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    public User getUserById(int tid){
        return userRepository.findById(tid);
    }

    public User updateUser(User user){
        userRepository.save(user);
        return user;
    }

    public List<User> findMinInvigilate(){
        return userRepository.findMin();
    }
}
