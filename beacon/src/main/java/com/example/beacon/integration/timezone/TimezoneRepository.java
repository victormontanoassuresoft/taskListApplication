package com.example.beacon.integration.timezone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.beacon.persistence.Timezone;


@FeignClient(name="timezone", url="http://api.timezonedb.com/v2.1")
public interface TimezoneRepository {
	
	@Value("${timezone.api.token}")
	public String token;
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-time-zone?key=" + token + "&format=json&by=position&")
	Timezone getTimezone(string lat, string lng);

}