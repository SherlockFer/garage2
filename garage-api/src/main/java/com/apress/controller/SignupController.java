package com.apress.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apress.dto.UserDTO;
import com.apress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
public class SignupController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody UserDTO userDTO) {
		log.info("Signing up new user: {}", userDTO);
		userDTO = userService.save(userDTO.toBuilder().id(null).build());
		if (userDTO.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, userDTO.getErrors());
		} else {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(buildLocationUri(userDTO.getId()));
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		}
	}

	private URI buildLocationUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}