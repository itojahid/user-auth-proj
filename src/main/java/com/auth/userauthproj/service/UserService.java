package com.auth.userauthproj.service;

import com.auth.userauthproj.IService.IUserService;
import com.auth.userauthproj.entity.Login;
import com.auth.userauthproj.entity.Role;
import com.auth.userauthproj.entity.User;
import com.auth.userauthproj.repository.RoleRepository;
import com.auth.userauthproj.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service @RequiredArgsConstructor @Transactional
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public User getByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public void addUserRole(Long userId, Long roleId) {
        User user = userRepository.getById(userId);
        Role role = roleRepository.getById(roleId);
        user.getRoles().add(role);
    }


    @Override
    public User login(String username, String password) {
        password = passwordEncoder.encode(password);
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null)
        {
            return new User(null, null, null, password, new ArrayList<>());
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException(username +" : username NOT FOUND");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
