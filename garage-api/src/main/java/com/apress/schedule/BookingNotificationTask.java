package com.apress.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.apress.repository.BookingRepository;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Slf4j

@Service
public class BookingNotificationTask {

	@Autowired
	private BookingRepository bookingRepository;

	@Scheduled(cron = "0 */1 * * * *", zone = "Europe/Dublin")
	@SchedulerLock(name = "BookingNotifierTask", lockAtMostFor = "1m", lockAtLeastFor = "30s")
	public void sendPendingBookings() {
		log.info("Pending Bookings : {} " + bookingRepository.countByStatus("booked"));
	}

}
