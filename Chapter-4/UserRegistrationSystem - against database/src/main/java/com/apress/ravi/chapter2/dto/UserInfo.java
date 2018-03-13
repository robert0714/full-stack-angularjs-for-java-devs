package com.apress.ravi.chapter2.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users")
public class UserInfo {

	@Id
	@GeneratedValue
	@Column(name = "userid")
	private Long id;

	@Column(name = "username")
	@NotEmpty
	private String username;

	@Column(name = "password")
	@NotEmpty
	private String password;

	@Column(name = "enabled")
	private boolean isEnabled;

	@Column(name = "role")
	private String role;

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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
