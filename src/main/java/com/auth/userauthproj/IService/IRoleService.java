package com.auth.userauthproj.IService;

import com.auth.userauthproj.entity.Role;

import java.util.List;

public interface IRoleService {
    Role saveRole(Role role);
    List<Role> getRoles();

}
