package com.staynest.service;

import com.staynest.entities.User;
import com.staynest.enums.UserStatus;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Integer userId);

    List<User> getAllUsers();

    User updateUserStatus(Integer userId, UserStatus status);

    void deleteUser(Integer userId);
}
