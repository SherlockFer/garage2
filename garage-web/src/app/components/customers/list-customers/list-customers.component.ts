import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user.model';
import { Observable } from 'rxjs';
import { Response } from '../../../models/response.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-customers',
  templateUrl: './list-customers.component.html'
})
export class ListCustomersComponent implements OnInit {

  customers: User[];

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private http: HttpClient) { }

  API_URL = `${environment.apiUrl}/users?filter[role]=customer`;

  ngOnInit() {
    this.http.get<User[]>(`${this.API_URL}`).subscribe(
      res => { this.customers = res; },
    );
  }

  public editCustomer(customer: User) {
    let CustomerId:number;
    CustomerId=customer.id;
    this.router.navigate([`/admin/customers/edit/${CustomerId}`]);
  }

}
