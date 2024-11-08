package com.sweeti.user.service.UserService;

import java.util.List;

import com.sweeti.user.service.entities.User;

public interface UserService {

	// user operations

	// create
	User saveUser(User user);

	//Get All users
	List<User> getAllUser();
	
	//get Single user of user id
	User getUser(String userId);
}
