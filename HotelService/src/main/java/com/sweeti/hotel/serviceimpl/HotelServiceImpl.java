package com.sweeti.hotel.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sweeti.hotel.entities.Hotel;
import com.sweeti.hotel.exception.ResourceNotFoundException;
import com.sweeti.hotel.repository.HotelRepository;
import com.sweeti.hotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{
	@Autowired
    private HotelRepository hotelRepository;
	
	@Override
	public Hotel saveHotel(@RequestBody Hotel hotel) {
		String randomHotelId = UUID.randomUUID().toString();
		hotel.setId(randomHotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with the given id is not found on the server !!"));
	}

}
