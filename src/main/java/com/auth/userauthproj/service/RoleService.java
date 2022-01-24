package com.auth.userauthproj.service;

import com.auth.userauthproj.IService.IRoleService;
import com.auth.userauthproj.entity.Role;
import com.auth.userauthproj.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class RoleService implements IRoleService {
    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
