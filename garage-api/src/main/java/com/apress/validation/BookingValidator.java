package com.apress.validation;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.client.VatServiceClient;
import com.apress.constants.Constants;
import com.apress.dto.BookingDTO;

import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVat;
import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component
public class BookingValidator {

	@Autowired
	VatServiceClient client;

	public void validate(BookingDTO bookingDTO) {
		validateVehiculeNumberPlate(bookingDTO);
		validateStatus(bookingDTO);
		validateDate(bookingDTO);
		validateBaseService(bookingDTO);
		validateVehicleType(bookingDTO);
		validateVehicleEngine(bookingDTO);
		validateVatNumberAndCountryCode(bookingDTO);
	}

	private void validateVehiculeNumberPlate(BookingDTO bookingDTO) {
		if (StringUtils.isBlank(bookingDTO.getVehicleNumberPlate())) {
			bookingDTO.addError("Vehicule number plate can't be empty");
		}
	}

	private void validateStatus(BookingDTO bookingDTO) {
		if (!EnumUtils.isValidEnum(Constants.BookingStatus.class, bookingDTO.getStatus())) {
			bookingDTO.addError("Status value is not valid");
		}
	}

	private void validateDate(BookingDTO bookingDTO) {
		if (bookingDTO.getDate() == null) {
			bookingDTO.addError("Date can't be empty");
		}
	}

	private void validateBaseService(BookingDTO bookingDTO) {
		if (bookingDTO.getBaseProduct() == null) {
			bookingDTO.addError("BaseService can't be empty");
		}
	}

	private void validateVehicleType(BookingDTO bookingDTO) {
		if (!EnumUtils.isValidEnum(Constants.VehicleType.class, bookingDTO.getVehicleType())) {
			bookingDTO.addError("Vehicle type value is not valid");
		}
	}

	private void validateVehicleEngine(BookingDTO bookingDTO) {
		if (bookingDTO.getVehicleEngine() != null && !EnumUtils.isValidEnum(Constants.VehicleEngine.class, bookingDTO.getVehicleEngine())) {
				bookingDTO.addError("Vehicle engine value is not valid");
		}
	}

	private void validateVatNumberAndCountryCode(BookingDTO bookingDTO) {
		if (!StringUtils.isBlank(bookingDTO.getVatNumber()) && !StringUtils.isBlank(bookingDTO.getCountryCode())) {
			CheckVat checkVat = new CheckVat();
			checkVat.setCountryCode(bookingDTO.getCountryCode());
			checkVat.setVatNumber(bookingDTO.getVatNumber());
			CheckVatResponse checkVatResponse = client.checkVat(checkVat);
			if (checkVatResponse == null) {
				log.warn("Vies Service unavailable, vat validation skiped");
			} else if (!checkVatResponse.isValid()) {
				bookingDTO.addError("Invalid vatNumber");
			}
		}
	}

}
