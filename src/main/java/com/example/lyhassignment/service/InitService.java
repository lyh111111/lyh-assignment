package com.example.lyhassignment.service;

import com.example.lyhassignment.entity.User;
import com.example.lyhassignment.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
public class InitService implements InitializingBean {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setName("LYH");
            user.setAuthority(User.ADMIN_AUTHORITY);
            user.setBriefIntroduction("我是管理员爸爸！！！！我是管理员爸爸！！！！我是管理员爸爸！！！！我是管理员爸爸！！！！我是管理员爸爸！！！！我是管理员爸爸！！！！");
            user.setPhoneNumber("123456");
            user.setPassword("lyh199809");
            userRepository.save(user);
        }
    }
}
