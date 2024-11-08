package com.sweeti.rating.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sweeti.rating.entities.Rating;
@Repository
public interface RatingRepository extends MongoRepository<Rating, String>{
	
	//custom finder method
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);

}
