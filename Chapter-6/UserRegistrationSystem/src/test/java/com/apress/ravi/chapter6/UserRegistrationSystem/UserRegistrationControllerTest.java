package com.apress.ravi.chapter6.UserRegistrationSystem;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.apress.ravi.chapter2.UserRegistrationSystemApplication;
import com.apress.ravi.chapter2.Rest.UserRegistrationRestController;
import com.apress.ravi.chapter2.dto.UsersDTO;
import com.apress.ravi.chapter2.repository.UserJpaRepository;;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRegistrationRestController.class)
@ContextConfiguration(classes = UserRegistrationSystemApplication.class)
public class UserRegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserJpaRepository userJpaRepositoryMock;

	private MediaType contentType;
	private UsersDTO user;

	@Before
	public void setup() {
		contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
				MediaType.APPLICATION_JSON.getSubtype(),
				Charset.forName("utf8"));

		user = new UsersDTO();
		user.setName("Ravi Kant Soni");
		user.setAddress("JP Nagar; Bangalore; India");
		user.setEmail("ravikantsoni.author@gmail.com");
	}

	@Test
	public void shouldReturnSuccessMessage() throws Exception {
		when(this.userJpaRepositoryMock.findById(1L)).thenReturn(user);

		this.mockMvc.perform(get("/api/user/1")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.name", is("Ravi Kant Soni")))
				.andExpect(
						jsonPath("$.address", is("JP Nagar; Bangalore; India")))
				.andExpect(jsonPath("$.email",
						is("ravikantsoni.author@gmail.com")))
				.andDo(print());
	}
}
