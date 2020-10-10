import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/models/user.model';
import { Response } from '../../../models/response.model';



@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
})
export class AddCustomerComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private http: HttpClient) { }

  BOOKING_API = `${environment.apiUrl}/users`;

  form = new FormGroup({
    email: new FormControl('', [Validators.required]),
    full_name: new FormControl('', [Validators.required]),
    mobile: new FormControl('', [Validators.required]),
    password: new FormControl('',[Validators.required]),
  })

  ngOnInit() {
  }

  public onSubmit() {
    let user = this.form.value;

    this.http.post<User>(`${this.BOOKING_API}`, user).subscribe(
      res => {
        let user: User = res;
        this.toastr.success('Your account has been registered correctly', 'Congratulations');;
        this.router.navigate([`/login`]);
      },
      (err: HttpErrorResponse) => {
        if (err.status === 400) {
          console.log(err)
          this.toastr.error(err.error.message,'', { disableTimeOut: true });
        }
      }
    );
  }

  public newCustomer(){
    this.form.get("email").setValue("customer-email@garage.com");
    this.form.get("full_name").setValue("Customer's Name");
    this.form.get("mobile").setValue("8991111111");
    this.form.get("password").setValue("123456");
  }
}