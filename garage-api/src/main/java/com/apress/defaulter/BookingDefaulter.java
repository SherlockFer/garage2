package com.apress.defaulter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.apress.client.GeoLocationClient;
import com.apress.constants.Constants.BookingStatus;
import com.apress.domain.Booking;
import com.apress.domain.User;
import com.apress.dto.BookingDTO;
import com.apress.dto.UserDTO;
import com.apress.mappers.UserMapper;
import com.apress.repository.BookingRepository;
import com.apress.repository.UserRepository;

import garage.services.geolocation.types.GetLocationRequest;
import garage.services.geolocation.types.GetLocationResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component
public class BookingDefaulter {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GeoLocationClient geoLocationClient;

	public void populateDefaults(BookingDTO bookingDTO) {
		populateReference(bookingDTO);
		populateStatus(bookingDTO);
		populateCountryAndCity(bookingDTO);
		populateCustomer(bookingDTO);
	}

	private void populateCustomer(BookingDTO bookingDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> user = userRepository.findById((long) authentication.getPrincipal());
		UserDTO userDTO = userMapper.toUserDTO(user.get());
		bookingDTO.setCustomer(userDTO);
	}

	public void populateReference(BookingDTO bookingDTO) {
		if (bookingDTO.getId() != null) {
			Optional<Booking> booking = bookingRepository.findById(bookingDTO.getId());
			bookingDTO.setReference(booking.get().getReference());
		} else if (bookingDTO.getReference() == null) {
			bookingDTO.setReference(UUID.randomUUID().toString());
		}
	}

	public void populateStatus(BookingDTO bookingDTO) {
		if (bookingDTO.getStatus() == null) {
			bookingDTO.setStatus(BookingStatus.booked.name());
		}
	}

	public void populateCountryAndCity(BookingDTO bookingDTO) {
		GetLocationRequest getLocationRequest = new GetLocationRequest();
		getLocationRequest.setIp(bookingDTO.getIpSource());
		GetLocationResponse getLocationResponse = geoLocationClient.getLocation(getLocationRequest);
		if (getLocationResponse != null && getLocationResponse.getStatus().equals("success")) {
			bookingDTO.setIpCountry(getLocationResponse.getCountry());
			bookingDTO.setIpCity(getLocationResponse.getCity());
		} else {
			bookingDTO.setIpCountry("Unknown");
			bookingDTO.setIpCity("Unknown");
		}

	}
}
