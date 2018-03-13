package com.apress.ravi.chapter2.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UsersDTO {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;

	@NotEmpty(message = "error.name.empty")
	@Length(max = 50, message = "error.name.length")
	@Column(name = "NAME")
	private String name;

	@NotEmpty(message = "error.address.empty")
	@Length(max = 150, message = "error.address.length")
	@Column(name = "ADDRESS")
	private String address;

	@Email(message = "error.email.email")
	@NotEmpty(message = "error.email.empty")
	@Length(max = 80, message = "error.email.length")
	@Column(name = "EMAIL")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
