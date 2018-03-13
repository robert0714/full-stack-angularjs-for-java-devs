package com.apress.ravi.chapter6;

import java.util.Random;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import com.apress.ravi.chapter2.dto.UsersDTO;
import com.apress.ravi.chapter6.client.UserRegistrationClient;

@SpringBootApplication
public class UserRegistrationSystemClientApplication {

	private static long id;

	public static void main(String[] args) {
		UserRegistrationClient userRegistrationClient = new UserRegistrationClient();

		UserRegistrationSystemClientApplication uRegistrationClient = new UserRegistrationSystemClientApplication();

		id = uRegistrationClient.createUser(userRegistrationClient);
		uRegistrationClient.listAllUsers(userRegistrationClient);
		uRegistrationClient.getUserById(userRegistrationClient);
		uRegistrationClient.getUserByIdUsingExchange(userRegistrationClient);
		uRegistrationClient.updateUserById(userRegistrationClient);
		// call delete at the last
		uRegistrationClient.deleteUser(userRegistrationClient);
	}

	private Long createUser(UserRegistrationClient userRegistrationClient) {
		UsersDTO user = new UsersDTO();
		user.setName("Shree Kant" + new Random().nextInt());
		user.setAddress("Sasaram; Bihar; India");
		user.setEmail("mytest@test.com");
		userRegistrationClient.createUser(user);

		user.setName("Shashi Kant" + new Random().nextInt());
		user.setAddress("Sasaram; Bihar; India");
		user.setEmail("mytest@test.com");
		UsersDTO newUser = userRegistrationClient.createUser(user);
		System.out.println(newUser.getId());
		return newUser.getId();
	}

	private void listAllUsers(UserRegistrationClient userRegistrationClient) {
		System.out.println("listAllUsers");
		UsersDTO[] usersList = userRegistrationClient.getAllUsers();
		System.out.println(usersList.length);
	}

	private void getUserById(UserRegistrationClient userRegistrationClient) {
		System.out.println("getUserById");
		UsersDTO user = userRegistrationClient.getUserById(id);
		System.out.println(
				"User-ID" + user.getId() + " User-Name" + user.getName());
	}

	private void updateUserById(UserRegistrationClient userRegistrationClient) {
		System.out.println("updateUserById");
		UsersDTO user = userRegistrationClient.getUserById(id);
		System.out.println("old user name: " + user.getName());
		user.setName("Shree Kant " + id);
		userRegistrationClient.updateUser(id, user);
		System.out.println("updated user name: " + user.getName());
	}

	private void deleteUser(UserRegistrationClient userRegistrationClient) {
		System.out.println("deleteUser");
		System.out.println("Old Users List: "
				+ userRegistrationClient.getAllUsers().length);
		userRegistrationClient.deleteUser(id);
		System.out.println("New Users List: "
				+ userRegistrationClient.getAllUsers().length);

	}

	private void getUserByIdUsingExchange(
			UserRegistrationClient userRegistrationClient) {
		System.out.println("getUserByIdUsingExchange");
		ResponseEntity<UsersDTO> responseEntity = userRegistrationClient
				.getUserByIdUsingExchangeAPI(id);
		UsersDTO user = responseEntity.getBody();
		System.out.println(
				"User-ID" + user.getId() + " User-Name" + user.getName());
	}
}
