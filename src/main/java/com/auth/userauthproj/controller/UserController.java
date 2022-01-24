package com.auth.userauthproj.controller;

import com.auth.userauthproj.entity.Login;
import com.auth.userauthproj.entity.Role;
import com.auth.userauthproj.entity.User;
import com.auth.userauthproj.entity.UserRole;
import com.auth.userauthproj.service.RoleService;
import com.auth.userauthproj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAlluser()
    {
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getByUserId(@PathVariable Long id, HttpServletResponse response)
            throws UsernameNotFoundException
    {
        User user = userService.getByUserId(id);

        if (user == null)
        {
            throw new UsernameNotFoundException(" : username NOT FOUND");
        }
        return ResponseEntity.ok().body(user);
//        return  (userService.getByUserId(id) == null) ?
//                ResponseEntity.ok().body(HttpStatus.NOT_FOUND) : ResponseEntity.ok().body(userService.getByUserId(id));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles()
    {
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PostMapping("/user/add")
    public ResponseEntity<Long> saveUser(@RequestBody User user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/user/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user).getId());
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> saveRole(@RequestBody Role role)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/role/add").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }

    @PostMapping("/add/user-role")
    public ResponseEntity<?> addUserRole(@RequestBody UserRole userRole)
    {
        userService.addUserRole(userRole.getUser_id(), userRole.getRole_id());
        return ResponseEntity.ok().build();
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Login login, HttpServletResponse response)
//    {
//        User body = userService.login(login.getUsername(), login.getPassword());
////        return  (body == null) ? ResponseEntity.ok().body(HttpStatus.UNAUTHORIZED) :
//        return  (body == null) ? ResponseEntity.notFound().build() :
//                ResponseEntity.ok().body(body);
//
//    }
}
