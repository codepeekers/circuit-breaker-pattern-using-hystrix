package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HumidityService {

	@HystrixCommand(fallbackMethod = "getHumidityFallback")
	public String getHumidity(String city) {
		return new RestTemplate().getForObject("http://localhost:9092/humidity/" + city, String.class);
	}

	public String getHumidityFallback(String city) {
		return "Service Down. Value Not Available";
	}
}
