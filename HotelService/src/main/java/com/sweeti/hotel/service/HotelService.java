package com.sweeti.hotel.service;

import java.util.List;

import com.sweeti.hotel.entities.Hotel;

public interface HotelService {
	// create 
	Hotel saveHotel(Hotel hotel);
	
	// Get Hotels
   List<Hotel> getAllHotels();
   
   //Get Hotel by hotel id
   Hotel getHotelById(String hotelId);
   

}
