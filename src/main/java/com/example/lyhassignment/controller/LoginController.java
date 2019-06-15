package com.example.lyhassignment.controller;

import com.example.lyhassignment.component.EncryptorComponent;
import com.example.lyhassignment.entity.User;
import com.example.lyhassignment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    private static final String STUDENT_ROLE = "Hello,normal teacher!";
    private static final String ADMIN_ROLE = "Hello,admin!";
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptorComponent encryptorComponent;

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(userService.getUserByName(user.getName()))
                .ifPresentOrElse(u -> {
                    logger.debug("11111");
                    logger.debug(user.getPassword());
                    logger.debug(u.getPassword());
                    if (!user.getPassword().equals(u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                    }
                    logger.debug("密码正确！！！");
                    Map map = Map.of("uid", u.getId(), "aid", u.getAuthority());
                    // 生成加密token
                    String token = encryptorComponent.encrypt(map);
                    // 在header创建自定义的权限
                    response.setHeader("token", token);
                    String role = null;
                    if (u.getAuthority() == User.USER_AUTHORITY) {
                        role = STUDENT_ROLE;
                    } if (u.getAuthority() == User.ADMIN_AUTHORITY) {
                        role = ADMIN_ROLE;
                    }
                    response.setHeader("role", role);
                }, () -> {
                    logger.debug("22222");
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                });
    }
}
