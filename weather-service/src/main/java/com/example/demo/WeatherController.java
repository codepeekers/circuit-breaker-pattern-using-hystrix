package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

	private Map<Integer, String> map = new HashMap<Integer, String>();

	@PostConstruct
	public void init() {
		log.info("init started");
		map.put(100, "delhi");
		map.put(200, "bangalore");
	}

	@Autowired
	private TemperatureService temperatureService;

	@Autowired
	private HumidityService humidityService;

	@GetMapping("/{pincode}")
	public Weather getWeather(@PathVariable("pincode") int pincode) {

		String city = map.get(pincode);
		log.info("Request to get weather info for city {}", city);
		Weather weather = new Weather();
		weather.setCity(city);
		String temp = temperatureService.getTemperature(city);
		log.info("Temperature for the city {} is {}", city, temp);

		String humidity = humidityService.getHumidity(city);
		log.info("Humidity for the city {} is {}", city, humidity);

		weather.setHumidity(humidity);
		weather.setTemperature(temp);

		return weather;

	}
}
