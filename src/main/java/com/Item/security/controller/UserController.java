package com.Item.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Item.model.ApiResponse;
import com.Item.security.models.User;
import com.Item.security.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/users")

public class UserController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/all")
	public ApiResponse<List<User>> listUser() {
		return new ApiResponse<>(HttpStatus.OK.value(), "User  list fetched successfully.",
				userRepository.findAll());
	}

}
