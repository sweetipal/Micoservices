package com.sweeti.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweeti.user.service.UserService.UserService;
import com.sweeti.user.service.entities.User;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	// create Api To Create a New User
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	int retryCount=1;
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getAllUsers(@PathVariable String userId) {
	    logger.info("Get Single User Handler: UserController");
	    logger.info("Retry Count: {}", retryCount);
	    retryCount++;
	    User user = userService.getUser(userId);
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// Creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
	     logger.info("Fallback is executed because service is down: " + ex.getMessage());
	    // Creating a dummy user to return in case of failure
	    User user = new User();
	    user.setEmail("dummy@gmail.com");
	    user.setName("Dummy User");
	    user.setAbout("This user is created as a dummy because the service is currently unavailable.");
	    user.setUserId("341343");
	    
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// get all user api 
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
