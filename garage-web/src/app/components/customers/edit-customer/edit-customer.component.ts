import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Response } from '../../../models/response.model';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
})
export class EditCustomerComponent implements OnInit {

  API_URL         = `${environment.apiUrl}`;
  form            : FormGroup;

  constructor(private route: ActivatedRoute,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router) { }

  ngOnInit() {

    let customer = Number((this.route.snapshot.params.id))

    this.form = this.formBuilder.group({
      id: [],
      full_name: [],
      email: [],
      mobile: [],
      role: [],
    });

    this.http.get<User>(`${this.API_URL}/users/${customer}`).subscribe(
      res => {
        this.form.setValue(res);
      },
    );
  }

  public onSubmit() {
    let customer = this.form.value;
    this.http.put<User>(`${this.API_URL}/users/${customer.id}`, customer).subscribe(
      res => {
        this.toastr.success('The account has been correctly modified');;
        this.router.navigate(["admin/customers"]);
      }
    );
  }
}
