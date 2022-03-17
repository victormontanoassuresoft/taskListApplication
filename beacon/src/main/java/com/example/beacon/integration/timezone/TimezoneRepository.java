package com.example.beacon.integration.timezone;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;

import com.example.beacon.persistence.Timezone;

@Value("${timezone.api.url}")
public String apiUrl;

@Value("${timezone.api.token}")
public String token;

@FeignClient(value="getTimezone", url=apiUrl + "get-time-zone?key=" + token + "&format=json&by=position&")
public interface TimezoneRepository {
	
	@GetMapping()
	Timezone getTimezone();
}