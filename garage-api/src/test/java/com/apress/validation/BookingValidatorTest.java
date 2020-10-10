package com.apress.validation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apress.client.VatServiceClient;
import com.apress.dto.BookingDTO;
import com.apress.dto.ProductDTO;

import eu.europa.ec.taxud.vies.services.checkvat.types.CheckVatResponse;

@ExtendWith(MockitoExtension.class)
public class BookingValidatorTest {

	@InjectMocks
	private BookingValidator validator;

	@Mock
	private VatServiceClient client;

	@Test
	void shouldHasErrorWhenVehiculeNumberPlateIsNotPresent() {
		BookingDTO bookingDTO = Mockito.spy(BookingDTO.builder().comments("comment").vehicleNumberPlate(" ")
				.status("booked").date(LocalDate.parse("2020-01-01")).build());

		validator.validate(bookingDTO);

		verify(bookingDTO).addError("Vehicule number plate can't be empty");
	}

	@Test
	void shouldHasErrorWhenVatNumberIsNotValid() {
		BookingDTO bookingDTO = Mockito.spy(BookingDTO.builder().comments("comment").vehicleNumberPlate("AAA-111")
				.status("booked").date(LocalDate.parse("2020-01-01")).countryCode("ES").vatNumber("1234567").build());
		CheckVatResponse checkVatResponse = new CheckVatResponse();
		checkVatResponse.setValid(false);
		when(client.checkVat(any())).thenReturn(checkVatResponse);

		validator.validate(bookingDTO);

		verify(bookingDTO).addError("Invalid vatNumber");
	}

	@Test
	void shouldHasErrorWhenVatNumberIsValid() {
		BookingDTO bookingDTO = Mockito.spy(BookingDTO.builder().comments("comment").vehicleNumberPlate("AAA-111")
				.status("booked").date(LocalDate.parse("2020-01-01")).baseProduct(new ProductDTO()).countryCode("ES")
				.vatNumber("1234567").vehicleType("car").build());
		CheckVatResponse checkVatResponse = new CheckVatResponse();
		checkVatResponse.setValid(true);
		when(client.checkVat(any())).thenReturn(checkVatResponse);

		validator.validate(bookingDTO);

		verify(bookingDTO, never()).addError(any());
	}

}
