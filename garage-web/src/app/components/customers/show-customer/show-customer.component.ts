import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../../../models/user.model';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Response } from '../../../models/response.model';


@Component({
  selector: 'app-show-customer',
  templateUrl: './show-customer.component.html',
})
export class ShowCustomerComponent implements OnInit {

  customer: User;
  isLoading: boolean = true;

  constructor(private router: Router,
              private toastr: ToastrService,
              private http: HttpClient,
              private route: ActivatedRoute) { }

  API_URL = `${environment.apiUrl}/users`;

  ngOnInit() {
    let id = Number(this.route.snapshot.params.id)
    this.showCustomer(id);
  }

  public showCustomer(id: Number){
    this.http.get<User>(`${this.API_URL}/${id}`).subscribe(
      res => { this.customer = res; this.isLoading = false;},
    );
  }
  
}
