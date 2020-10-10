package com.apress.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apress.dto.LocationDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class LocationService {

	public LocationDTO getLocationByIp(String ip) {
		if (isPrivateIp(ip)) {
			ip = getPublicIp();
		}
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<LocationDTO> responseLocationDTO = null;
		try {
			responseLocationDTO = restTemplate.getForEntity("http://ip-api.com/json/" + ip, LocationDTO.class);
		} catch (RuntimeException e) {
			log.error("ip-api.com Service Error" + responseLocationDTO, e);
		}

		return responseLocationDTO.getBody();
	}

	private String getPublicIp() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity("https://api.ipify.org/", String.class);
		} catch (RuntimeException e) {
			log.error("ip-api.com Service Error" + response, e);
		}
		return response.getBody();

	}

	private boolean isPrivateIp(String ip) {
		InetAddress address;
		try {
			address = InetAddress.getByName(ip);
		} catch (UnknownHostException exception) {
			return false;
		}
		return (address.isSiteLocalAddress() || address.isLoopbackAddress());
	}

}
