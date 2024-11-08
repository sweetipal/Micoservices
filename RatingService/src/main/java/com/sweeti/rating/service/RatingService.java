package com.sweeti.rating.service;

import java.util.List;

import com.sweeti.rating.entities.Rating;

public interface RatingService {
	// add Rating
	Rating create(Rating rating);
	
	//Get all ratings 
	List<Rating> getAllRating();
	
	//get all by user Id
	List<Rating> getAllRatingByUserId(String userId);
	
	//get all by hotel id
	List<Rating> getAllRatingByHotelId(String hotelId);
}
