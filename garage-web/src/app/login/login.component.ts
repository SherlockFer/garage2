import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl, EmailValidator } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { first } from "rxjs/operators";
import { User } from './../models/user.model';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Response } from '../models/response.model';
import { AuthService } from '../helpers/auth.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { forkJoin } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  returnUrl: string;
  API_URL = `${environment.apiUrl}`;
  user_name: string;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private authService: AuthService,
    private http: HttpClient) { }

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    })

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams["returnUrl"] || "/";
  }

  public onSubmit() {
    this.authService.login(this.form.controls.email.value, this.form.controls.password.value).subscribe(
      res => {
        if (this.authService.isAdmin()) {
          this.router.navigate([`/my-bookings`]);
        }else{
          this.router.navigate([this.returnUrl]);
        }
        
      },
      (err: HttpErrorResponse) => {
        if (err.status === 401) {
          console.log(err)
          this.toastr.error(err.error.message, '', { disableTimeOut: true });
        }
      }
    );
  }

  public customerLogin() {
    this.form.get("email").setValue("customer-1@garage.com");
    this.form.get("password").setValue("123456");
  }

  public adminLogin() {
    this.form.get("email").setValue("admin@garage.com");
    this.form.get("password").setValue("123456");
  }

}