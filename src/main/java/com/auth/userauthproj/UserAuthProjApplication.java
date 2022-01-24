package com.auth.userauthproj;

import com.auth.userauthproj.entity.User;
import com.auth.userauthproj.entity.UserRole;
import com.auth.userauthproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@SpringBootApplication
public class UserAuthProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthProjApplication.class, args);
	}
//172.16.19.201:3000
//	@Bean
//	CommandLineRunner run(UserService userService)
//	{
//		return args -> {
//			userService.addUserRole(1L, 1L);
//			userService.addUserRole(1L, 2L);
//		};
//	}
//
//	@Bean
//	CommandLineRunner run(UserService userService)
//	{
//		return args -> {
//			userService.saveUser(new User(null, "Zahid Saad", "j@hid", "1234", new ArrayList<>()));
//		};
//	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider(){
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(encoder());
//
//		return authenticationProvider;
//	}
}
