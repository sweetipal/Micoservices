package com.sweeti.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sweeti.user.service.entities.Rating;
import com.sweeti.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

//	@Test
//	void createRating() {
//		Rating rating = new Rating();
//		rating.setRating(10);
//		rating.setUserId("");
//		rating.setHotelId("");
//		rating.setFeedback("This is creating using feign client");
//		Rating savedRating = ratingService.createRating(rating);
//		System.out.println("new Rating is created.");
//	}
}
