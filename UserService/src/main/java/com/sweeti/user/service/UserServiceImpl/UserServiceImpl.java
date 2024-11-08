package com.sweeti.user.service.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sweeti.user.service.Repository.UserRepository;
import com.sweeti.user.service.UserService.UserService;
import com.sweeti.user.service.entities.Hotel;
import com.sweeti.user.service.entities.Rating;
import com.sweeti.user.service.entities.User;
import com.sweeti.user.service.exception.ResourceNotFoundException;
import com.sweeti.user.service.external.services.HotelService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// Implement rating service call using restTemplate
		return userRepository.findAll();
	}

//	@Override
//	public User getUser(String userId) {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User With Given id not found on server !!"));
//
//		// Fetch rating of above user from Rating Service
//		// http://localhost:8084/ratings/users/1480b71c-f110-4852-a389-e171e6fc4bb6
//
//		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(),
//				Rating[].class);
//		logger.info("{}", ratingsOfUser);
//		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//
//		List<Rating> ratingList = ratings.stream().map(rating -> {
//			// Api call to hotel service to get the hotel
//		//	ResponseEntity<Hotel> forEntity = restTemplate
//		//			.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//		//	Hotel hotel = forEntity.getBody();
//			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//		//	logger.info("Response Status Code: {}", forEntity.getStatusCode());
//			// set the hotel to rating
//			rating.setHotel(hotel);
//			// return rating
//			return rating;
//		}).collect(Collectors.toList());
//		user.setRatings(ratings);
//		return user;
//
//	}
	@Override
	public User getUser(String userId) {
	    // Fetch user by ID from repository
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User with given id not found on server!!"));

	    // Fetch rating of the user from Rating Service
	    String ratingServiceUrl = "http://RATINGSERVICE/ratings/users/" + user.getUserId();
	    Rating[] ratingsOfUser = restTemplate.getForObject(ratingServiceUrl, Rating[].class);
	    
	    if (ratingsOfUser == null) {
	        logger.warn("No ratings found for user with ID: {}", user.getUserId());
	        return user; // Return user with empty ratings if no ratings found
	    }

	    logger.info("Ratings for user: {}", ratingsOfUser);
	    List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

	    // Map ratings to hotels
	    List<Rating> ratingList = ratings.stream().map(rating -> {
	        try {
	            // Call to Hotel Service to fetch hotel details by hotel ID
	            Hotel hotel = hotelService.getHotel(rating.getHotelId());
	            logger.info("Hotel details for rating: {}", hotel);

	            // Set the fetched hotel object to the rating
	            rating.setHotel(hotel);
	        } catch (Exception e) {
	            logger.error("Error fetching hotel details for hotelId: {}", rating.getHotelId(), e);
	            rating.setHotel(null); // In case of error, set hotel as null to prevent crashing
	        }
	        return rating;
	    }).collect(Collectors.toList());

	    // Set the ratings to the user and return the user
	    user.setRatings(ratingList);
	    return user;
	}

}
