package com.Item.security.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints= {
		@UniqueConstraint(columnNames = "username")
})
public class User {
public User() {
		super();
		// TODO Auto-generated constructor stub
	}

public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

@Id
@GeneratedValue

private long id;
private String username;
private String password;
	
@ManyToMany(fetch = FetchType.LAZY)

@JoinTable(name= "user_roles",
joinColumns = @JoinColumn(name="user_id"),
inverseJoinColumns = @JoinColumn(name="role_id"))


private Set<Role> roles = new HashSet<>();

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

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

public Set<Role> getRoles() {
	return roles;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}



}