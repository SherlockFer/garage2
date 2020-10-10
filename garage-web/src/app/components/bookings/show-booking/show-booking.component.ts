import { Booking } from 'src/app/models/booking.model';
import { Service } from './../../../models/service.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Response } from '../../../models/response.model';
import { User } from 'src/app/models/user.model';
import { Part } from 'src/app/models/part.model';
import { VEHICLE_ENGINES } from 'src/app/models/constants.model';
import { VEHICLE_TYPES } from 'src/app/models/constants.model';
import { BOOKING_STATUS } from 'src/app/models/constants.model';

@Component({
  selector: 'app-show-booking',
  templateUrl: './show-booking.component.html',
})
export class ShowBookingComponent implements OnInit {

  API_URL         = `${environment.apiUrl}`;

  extra_services  : Service[];
  base_service    : Service[];
  service         : Service;
  parts           : Part[];
  customer        : User;
  booking         : Booking;
  mechanics       : User[];  
  vehicle_engines = VEHICLE_ENGINES;
  vehicle_types   = VEHICLE_TYPES;
  booking_status  = BOOKING_STATUS;
  datetoday       = new Date();
  isLoading: boolean = true;
  

  constructor(private route: ActivatedRoute,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router) {}

  ngOnInit() {
    let booking_id = Number((this.route.snapshot.params.id))
    
    // Base services
    this.http.get<Service[]>(`${this.API_URL}/services/?filter[category]=base`).subscribe(
      res => {
        this.base_service = res
      }
    );

    // Extra services
    this.http.get<Service[]>(`${this.API_URL}/services/?filter[category]=extra`).subscribe(
      res => {
        this.extra_services = res
      }
    );

    // Autoparts
    this.http.get<Part[]>(`${this.API_URL}/vehicles/parts`).subscribe(
      res => {
        this.parts = res;        
      }
    );

    this.http.get<Booking>(`${this.API_URL}/bookings/${booking_id}`).subscribe(
      res => {
        this.booking=res;
        //start customer
        this.http.get<User>(`${this.API_URL}/users/${this.booking.customer.id}`).subscribe(
          res => {
            this.customer=res;
          }
        );
        //finish customer

        // start service
        this.http.get<Service>(`${this.API_URL}/services/${this.booking.base_service.id}`).subscribe(
          res => {
            this.service = res
            this.isLoading = false;
          }
        );
      }
    );
  }  

  public getExtraServiceById(id: number){
    return this.extra_services.find(service => service.id === id);
  }

  public getBaseServiceById(id: number){
    return this.base_service.find(service => service.id === id);
  }

  public getPartById(id: number){
    return this.parts.find(part => part.id === id);
  }
}
