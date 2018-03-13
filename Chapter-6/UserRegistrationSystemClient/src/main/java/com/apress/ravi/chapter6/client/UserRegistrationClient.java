package com.apress.ravi.chapter6.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.apress.ravi.chapter2.dto.UsersDTO;

public class UserRegistrationClient {

	private static RestTemplate restTemplate = new RestTemplate();
	private static final String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user/";

	public UsersDTO getUserById(final Long userId) {
		return restTemplate.getForObject(USER_REGISTRATION_BASE_URL + "/{id}", UsersDTO.class, userId);
	}

	public UsersDTO[] getAllUsers() {
		return restTemplate.getForObject(USER_REGISTRATION_BASE_URL, UsersDTO[].class);
	}

	public UsersDTO createUser(final UsersDTO user) {
		return restTemplate.postForObject(USER_REGISTRATION_BASE_URL, user, UsersDTO.class);
	}

	public void updateUser(final Long userId, final UsersDTO user) {
		restTemplate.put(USER_REGISTRATION_BASE_URL + "/{id}", user, userId);
	}

	public void deleteUser(final Long userId) {
		restTemplate.delete(USER_REGISTRATION_BASE_URL + "/{id}", userId);
	}

	public ResponseEntity<UsersDTO> getUserByIdUsingExchangeAPI(final Long userId) {
		HttpEntity<UsersDTO> httpEntity = new HttpEntity<UsersDTO>(new UsersDTO());
		return restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}", HttpMethod.GET, httpEntity, UsersDTO.class,
				userId);
	}
}
