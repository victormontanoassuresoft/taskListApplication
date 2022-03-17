package com.example.beacon.integration.timezone;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.beacon.persistence.Timezone;

@FeignClient(value="getTimezone", url="http://api.timezonedb.com/v2.1/get-time-zone?key=VAQC6KNHS0BR&format=json&by=position&lat=-17.414&lng=-66.1653")
public interface TimezoneRepository {

	@GetMapping()
	Timezone getTimezone(String lat, String lng);
}