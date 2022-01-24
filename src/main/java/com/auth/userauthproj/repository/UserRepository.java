package com.auth.userauthproj.repository;

import com.auth.userauthproj.entity.Login;
import com.auth.userauthproj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    List<User> findByName (String name);
}
