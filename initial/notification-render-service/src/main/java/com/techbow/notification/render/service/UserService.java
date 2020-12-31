package com.techbow.notification.render.service;

import com.techbow.notification.data.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUserById(String id) {
        User user = new User();
        user.setId(id);
        user.setName("Test name");
        user.setEmail("test@email.com");
        return user;
    }
}
