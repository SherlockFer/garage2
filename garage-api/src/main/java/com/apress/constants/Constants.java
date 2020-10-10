package com.apress.constants;

public interface Constants {
	
	public enum BookingStatus{
		booked,
		in_service,
		fixed,
		collected,
		unrepairable;
		
	}
	
	public enum UserRole{
		admin,
		customer,
		mechanic;
	}
	
	public enum VehicleType{
		car,
		small_van,
		small_bus,
		motorbike;
	}
	
	public enum VehicleEngine{
		diesel,
		petrol,
		hybrid,
		electric;
	}
	
	public enum ServiceCategory{
		base,
		extra;
	}
		
}
