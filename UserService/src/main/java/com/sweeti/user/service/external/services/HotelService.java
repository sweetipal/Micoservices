package com.sweeti.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sweeti.user.service.entities.Hotel;
@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
	@GetMapping("/hotels/{hotelId")
	public Hotel getHotel(@PathVariable String hotelId);
}
