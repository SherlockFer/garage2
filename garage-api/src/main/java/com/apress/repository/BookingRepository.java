package com.apress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apress.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	public Optional<Booking> countByStatus(String status);

}