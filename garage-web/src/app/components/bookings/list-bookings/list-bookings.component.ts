import { User } from './../../../models/user.model';
import { Booking } from './../../../models/booking.model';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Response } from '../../../models/response.model';
import { Injectable } from '@angular/core';
import { forkJoin } from 'rxjs';  // RxJS 6 syntax
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { VEHICLE_ENGINES } from 'src/app/models/constants.model';
import { VEHICLE_TYPES } from 'src/app/models/constants.model';
import { BOOKING_STATUS } from 'src/app/models/constants.model';
import { AuthService } from 'src/app/helpers/auth.service';
import { Service } from '../../../models/service.model';



@Component({
  selector: 'app-list-bookings',
  templateUrl: './list-bookings.component.html'
})
export class ListBookingsComponent implements OnInit {

  API_URL = `${environment.apiUrl}`;

  curr_bookings: Booking[];
  on_site_booking: Booking[];
  past_bookings: Booking[];
  base_service: Service[];
  customers: User[];
  vehicle_engines = VEHICLE_ENGINES;
  vehicle_types = VEHICLE_TYPES;
  booking_status = BOOKING_STATUS;
  checkPolicy: boolean = true;

  constructor(private router: Router,
    private http: HttpClient,
    private toastr: ToastrService,
    private authService: AuthService) { }

  ngOnInit() {
    if (this.authService.isAdmin()) {
      this.checkPolicy = false;
    }

    //customers
    this.http.get<User[]>(`${this.API_URL}/users`).subscribe(
      res => { this.customers = res;},
    );

    // Base services
    this.http.get<Service[]>(`${this.API_URL}/services/?filter[category]=base`).subscribe(
      res => {
        this.base_service = res
      }
    );

    // Bookings
    this.setBooking();

  }

  public getServiceById(id: number) {
    return this.base_service.find(service => service.id === id);
  }

  public getNameById(id: number) {
    return this.customers.find(customer => customer.id === id);
  }

  public editBooking(booking: Booking) {
    let Bookingid: number;
    Bookingid = booking.id;
    if (this.authService.isAdmin()) {
      this.router.navigate([`admin/booking/edit/${Bookingid}`]);
    } else {
      this.router.navigate([`my-bookings/edit/${Bookingid}`]);
    }
  }

  // Bookings
  public setBooking() {
    this.http.get<Booking[]>(`${this.API_URL}/bookings`).subscribe(
      res => {
        let bookings = res;
        this.curr_bookings = bookings.filter(
          booking => ["booked"].includes(booking.status)
        );
        this.on_site_booking = bookings.filter(
          booking => ["in_service", "fixed"].includes(booking.status)
        );
        this.past_bookings = bookings.filter(
          booking => ["collected", "unrepairable"].includes(booking.status)
        );
      }
    );
  }

  public delete(booking: Booking) {
    if(confirm("Are you sure to delete")){
      this.http.delete<Booking[]>(`${this.API_URL}/bookings/${booking.id}`).subscribe(
        res => {
          this.toastr.success('The booking has been deleted correctly');
          this.setBooking();
        }
      ); 
    }
  }

  public invoice(booking: Booking) {
    this.router.navigate([`my-bookings/invoice/${booking.id}`]);
  }

}
