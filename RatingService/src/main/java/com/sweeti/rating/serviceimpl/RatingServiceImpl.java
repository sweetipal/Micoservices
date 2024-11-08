package com.sweeti.rating.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweeti.rating.entities.Rating;
import com.sweeti.rating.repositories.RatingRepository;
import com.sweeti.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating create(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		List<Rating> ratings = ratingRepository.findAll();
		return ratings;
	}

	@Override
	public List<Rating> getAllRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
