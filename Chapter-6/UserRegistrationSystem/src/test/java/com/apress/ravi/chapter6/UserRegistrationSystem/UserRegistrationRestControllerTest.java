package com.apress.ravi.chapter6.UserRegistrationSystem;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.apress.ravi.chapter2.UserRegistrationSystemApplication;
import com.apress.ravi.chapter2.Rest.UserRegistrationRestController;
import com.apress.ravi.chapter2.dto.UsersDTO;
import com.apress.ravi.chapter2.repository.UserJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRegistrationSystemApplication.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRegistrationRestControllerTest {

	@Spy
	private UserRegistrationRestController userRegistrationRestControllerSpy;

	@Mock
	private UserJpaRepository userJpaRepositoryMock;

	@Before
	public void setup() {
		userRegistrationRestControllerSpy = new UserRegistrationRestController();
		ReflectionTestUtils.setField(userRegistrationRestControllerSpy,
				"userJpaRepository", userJpaRepositoryMock);
	}

	@Test
	public void testListAllUsers() {
		List<UsersDTO> userList = new ArrayList<UsersDTO>();
		userList.add(new UsersDTO());
		when(this.userJpaRepositoryMock.findAll()).thenReturn(userList);

		ResponseEntity<List<UsersDTO>> responseEntity = 
				this.userRegistrationRestControllerSpy.listAllUsers();

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertEquals(1, responseEntity.getBody().size());
	}

	@After
	public void teardown() {
		userRegistrationRestControllerSpy = null;
	}
}
