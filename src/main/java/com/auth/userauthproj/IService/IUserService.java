package com.auth.userauthproj.IService;

import com.auth.userauthproj.entity.Login;
import com.auth.userauthproj.entity.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getByUserId(Long id);

    void addUserRole(Long userId, Long roleId);

    User login(String username, String password);

}