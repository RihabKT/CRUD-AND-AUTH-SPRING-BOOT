package com.Item.security.payload.request;

import java.util.Set;

public class SignupRequest {
public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
public SignupRequest(String username, String password, Set<String> role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
private String username;
private String password;
private Set<String>role;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Set<String> getRole() {
	return role;
}
public void setRole(Set<String> role) {
	this.role = role;
}
@Override
public String toString() {
	return "SignupRequest [username=" + username + ", password=" + password + ", role=" + role + "]";
}
}
