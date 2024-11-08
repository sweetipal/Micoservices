package com.sweeti.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweeti.rating.entities.Rating;
import com.sweeti.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private RatingService ratingService;

	// dd Ratings
	@PostMapping
	public ResponseEntity<Rating> createRatings(@RequestBody Rating ratings) {
		Rating newRating = ratingService.create(ratings);
		return new ResponseEntity<>(newRating, HttpStatus.CREATED);
	}

	// Get all Rating
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> ratings = ratingService.getAllRating();
		return new ResponseEntity<List<Rating>>(ratings, HttpStatus.OK);
	}

	// Get all Rating by userId
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getAllRatingsByUser(@PathVariable String userId) {
		List<Rating> ratings = ratingService.getAllRatingByUserId(userId);
		return new ResponseEntity<List<Rating>>(ratings, HttpStatus.OK);
	}

	// Get all Rating by userId
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getAllRatingsByHotel(@PathVariable String hotelId) {
		List<Rating> ratings = ratingService.getAllRatingByHotelId(hotelId);
		return new ResponseEntity<List<Rating>>(ratings, HttpStatus.OK);
	}

}
