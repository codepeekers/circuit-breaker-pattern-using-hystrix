package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TemperatureService {

	@HystrixCommand(fallbackMethod = "getTemperaturefallBack")
	public String getTemperature(String city) {
		return new RestTemplate().getForObject("http://localhost:9091/temperature/" + city, String.class);
	}

	private String getTemperaturefallBack(String city) {
		return "Service Down. Value Not Available";
	}
}
