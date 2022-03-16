package com.example.beacon.persistence.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.beacon.persistence.Timezone;

@FeignClient(value="getTimezone", url="http://api.timezonedb.com/v2.1/get-time-zone?key=VAQC6KNHS0BR&format=json&by=position")
public interface TimezoneRepository {

	@GetMapping()
	Timezone getTimezone(String lat, String lng);
}