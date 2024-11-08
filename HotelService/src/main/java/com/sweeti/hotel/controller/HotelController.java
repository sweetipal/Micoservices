package com.sweeti.hotel.controller;

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

import com.sweeti.hotel.entities.Hotel;
import com.sweeti.hotel.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotels(@RequestBody Hotel hotel){
		Hotel savedhotel = hotelService.saveHotel(hotel);
		return new ResponseEntity<Hotel>(savedhotel,HttpStatus.OK);
	}
    
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
		Hotel hotel = hotelService.getHotelById(hotelId);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels(){
		List<Hotel> hotels = hotelService.getAllHotels();
		return new ResponseEntity<>(hotels,HttpStatus.OK);
	}
}
