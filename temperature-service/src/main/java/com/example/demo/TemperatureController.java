package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/temperature")
@Slf4j
public class TemperatureController {

	private Map<String, String> map = new HashMap<String, String>();

	@PostConstruct
	public void init() {
		log.info("Init starting");
		map.put("delhi", "40 c");
		map.put("bangalore", "20 c");
	}

	@GetMapping("/{city}")
	public String getTemp(@PathVariable("city") String city) {
		return map.get(city);
	}
}
