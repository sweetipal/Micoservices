package com.sweeti.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sweeti.user.service.entities.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
	// Get

	// post
	@PostMapping("ratings")
	public ResponseEntity<Rating> createRating(Rating values);

	// put
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);
	
	//delete 
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);

}
