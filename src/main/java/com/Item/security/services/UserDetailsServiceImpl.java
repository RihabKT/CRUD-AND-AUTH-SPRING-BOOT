package com.Item.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Item.security.models.User;
import com.Item.security.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	
	public UserDetails loadUserByUserName(String username)throws UsernameNotFoundException{
		User user=userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("USER NOT FOUND WITH USERNAME:" + username));
		return UserDetailsImpl.build(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
