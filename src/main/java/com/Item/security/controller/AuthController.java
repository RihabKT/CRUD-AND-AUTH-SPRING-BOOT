package com.Item.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Item.security.jwt.JwtUtils;
import com.Item.security.models.ERole;
import com.Item.security.models.Role;
import com.Item.security.models.User;
import com.Item.security.payload.request.LoginRequest;
import com.Item.security.payload.request.SignupRequest;
import com.Item.security.payload.response.JwtResponse;
import com.Item.security.payload.response.MessageResponse;
import com.Item.security.repository.RoleRepository;
import com.Item.security.repository.UserRepository;
import com.Item.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
@Autowired

AuthenticationManager authenticationManager;
@Autowired
UserRepository userRepository;
@Autowired
RoleRepository roleRepository;
@Autowired
PasswordEncoder encoder;
@Autowired
JwtUtils jwtUtils;

@PostMapping("/signin")
public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
	
	System.out.println("**********"+loginRequest.toString()+"***********");
	Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = jwtUtils.generateJwtToken(authentication);
	
	UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
	List<String> roles = userDetails.getAuthorities().stream()
			.map(item -> item.getAuthority())
			.collect(Collectors.toList());
	return ResponseEntity.ok(new JwtResponse(jwt, 
											 userDetails.getId(), 
											 userDetails.getUsername(), 
											  
											 roles 
											
											));
	
	
	
}

@PostMapping("/signup")
public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
	if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Username is already taken!"));
	}

	 

	// Create new user's account
	User user = new User(signUpRequest.getUsername(), 
					  
						 encoder.encode(signUpRequest.getPassword()));
	
	user.setUsername(signUpRequest.getUsername());
		Set<String> strRoles = signUpRequest.getRole();
		 
		
	Set<Role> roles = new HashSet<>();


		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.Role_Admin)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);

				break;
			case "user":
				Role userRole = roleRepository.findByName(ERole.Role_User)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);

				break;
		
			
			default:
				Role userRole1 = roleRepository.findByName(ERole.Role_User)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole1);
			}
		});
	

	user.setRoles(roles);
	userRepository.save(user);

	return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
}



}
