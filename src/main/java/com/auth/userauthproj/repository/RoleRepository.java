package com.auth.userauthproj.repository;

import com.auth.userauthproj.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName (String roleName);
}
