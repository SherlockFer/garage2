package com.apress.utils;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.apress.constants.Constants.BookingStatus;
import com.apress.constants.Constants.UserRole;
import com.apress.constants.Constants.VehicleEngine;
import com.apress.constants.Constants.VehicleType;
import com.apress.domain.Booking;
import com.apress.domain.Part;
import com.apress.domain.Product;
import com.apress.domain.User;
import com.apress.repository.BookingRepository;
import com.apress.repository.PartRepository;
import com.apress.repository.ProductRepository;
import com.apress.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoadDemoData implements ApplicationRunner {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PartRepository partRepository;

	@SuppressWarnings("unused")
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Loading database");

		User user1 = userRepository
				.findById(1L).get();
		
		User user2 = userRepository
				.findById(2L).get();
		
		User user3 = userRepository
				.findById(3L).get();
		
		User user4 = userRepository
				.findById(4L).get();
		
		User user5 = userRepository
				.save(User.builder()
				.role(UserRole.customer.name())
				.password("123456")
				.fullName("Customer-1")
				.mobile("123456789")
				.email("customer-1@garage.com")
				.build());
		
		User user6 = userRepository
				.save(User.builder()
				.role(UserRole.customer.name())
				.password("123456")
				.fullName("Customer-2")
				.mobile("123456789")
				.email("customer-2@garage.com")
				.build());
		
		User user7 = userRepository
				.save(User.builder()
				.role(UserRole.customer.name())
				.password("123456")
				.fullName("Customer-3")
				.mobile("123456789")
				.email("customer-3@garage.com")
				.build());

		Product product1 = productRepository
				.findById(1L).get();
			
		Product product2 = productRepository
				.findById(2L).get();
		
		Product product3 = productRepository
				.findById(3L).get();
		
		Product product4 = productRepository
				.findById(4L).get();
		
		Product product5 = productRepository
				.findById(5L).get();
		
		Product product6 = productRepository
				.findById(6L).get();
		
		Product product7 = productRepository
				.findById(7L).get();

		Part part1 = partRepository
				.findById(1L).get();
		
		Part part2 = partRepository
				.findById(2L).get();
		
		Part part3 = partRepository
				.findById(3L).get();

		Booking booking1 = bookingRepository
				.save(Booking.builder()
				.date(LocalDate.parse("2020-10-01"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 1")
				.status(BookingStatus.booked.name())
				.vehicleBrand("Ford")
				.vehicleEngine(VehicleEngine.diesel.name())
				.vehicleModel("M5")
				.vehicleNumberPlate("AAA-111")
				.vehicleType(VehicleType.car.name())
				.part(part1)
				.part(part2)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3)
				.total(290.0)
				.build());
		
		Booking booking2 = bookingRepository
				.save(Booking.builder()
				.date(LocalDate.parse("2020-10-02"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 2")
				.status(BookingStatus.booked.name())
				.vehicleBrand("Ford")
				.vehicleEngine(VehicleEngine.diesel.name())
				.vehicleModel("M5")
				.vehicleNumberPlate("BBB-222")
				.vehicleType(VehicleType.car.name())
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3)
				.total(230.0)
				.build());
		
		Booking booking3 = bookingRepository
				.save(Booking.builder()
				.date(LocalDate.parse("2020-10-03"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 3")
				.status(BookingStatus.in_service.name())
				.vehicleBrand("Ford")
				.vehicleEngine(VehicleEngine.petrol.name())
				.vehicleModel("M5")
				.vehicleNumberPlate("CCC-333")
				.vehicleType(VehicleType.small_van.name())
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3)
				.total(230.0)
				.build());
		
		Booking booking4 = bookingRepository
				.save(Booking.builder()
				.date(LocalDate.parse("2020-10-04"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 4")
				.status(BookingStatus.fixed.name())
				.vehicleBrand("Ford")
				.vehicleEngine(VehicleEngine.hybrid.name())
				.vehicleModel("M5")
				.vehicleNumberPlate("DDD-444")
				.vehicleType(VehicleType.small_bus.name())
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3)
				.total(230.0)
				.build());
		
		Booking booking5 = bookingRepository
				.save(Booking.builder()
				.date(LocalDate.parse("2020-10-05"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 5")
				.status(BookingStatus.collected.name())
				.vehicleBrand("Ford")
				.vehicleEngine(VehicleEngine.electric.name())
				.vehicleModel("M5")
				.vehicleNumberPlate("EEE-666")
				.vehicleType(VehicleType.small_bus.name())
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3)
				.total(230.0)
				.build());
		
		Booking booking6 = bookingRepository
				.save(Booking.builder()
				.date(LocalDate.parse("2020-10-06"))
				.reference(UUID.randomUUID().toString())
				.comments("comentario 6")
				.status(BookingStatus.unrepairable.name())
				.vehicleBrand("Ford")
				.vehicleEngine(VehicleEngine.electric.name())
				.vehicleModel("M5")
				.vehicleNumberPlate("FFF-777")
				.vehicleType(VehicleType.car.name())
				.part(part1)
				.mechanic(user2)
				.customer(user1)
				.baseProduct(product1)
				.extraProduct(product2)
				.extraProduct(product3)
				.total(230.0)
				.build());
		
	}
}