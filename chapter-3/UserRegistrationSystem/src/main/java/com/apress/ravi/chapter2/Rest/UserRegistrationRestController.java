package com.apress.ravi.chapter2.Rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.ravi.chapter2.Exception.CustomErrorType;
import com.apress.ravi.chapter2.dto.UsersDTO;
import com.apress.ravi.chapter2.repository.UserJpaRepository;

/**
 * @author Ravi Kant Soni
 */
@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {

	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

	private UserJpaRepository userJpaRepository;

	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@GetMapping("/")
	public ResponseEntity<List<UsersDTO>> listAllUsers() {
		logger.info("Fetching all users");
		List<UsersDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsersDTO>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsersDTO> getUserById(@PathVariable("id") final Long id) {
		logger.info("Fetching User with id {}", id);
		UsersDTO user = userJpaRepository.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity<UsersDTO>(new CustomErrorType("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsersDTO>(user, HttpStatus.OK);
	}

	/**
	 * @exception MethodArgumentNotValidException
	 *                (validation fails)
	 */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody final UsersDTO user) {
		logger.info("Creating User : {}", user);
		if (userJpaRepository.findByName(user.getName()) != null) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType(
							"Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userJpaRepository.save(user);
		return new ResponseEntity<UsersDTO>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UsersDTO user) {
		logger.info("Updating User with id {}", id);
		UsersDTO currentUser = userJpaRepository.findById(id);
		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Unable to upate. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		currentUser.setName(user.getName());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());
		userJpaRepository.saveAndFlush(currentUser);
		return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") final Long id) {
		logger.info("Deleting User with id {}", id);
		UsersDTO user = userJpaRepository.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<UsersDTO>(
					new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		userJpaRepository.delete(id);
		return new ResponseEntity<UsersDTO>(new CustomErrorType("Deleted User with id " + id + "."),
				HttpStatus.NO_CONTENT);
	}

}
