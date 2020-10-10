import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Response } from '../../../models/response.model';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
})
export class EditEmployeeComponent implements OnInit {

  API_URL         = `${environment.apiUrl}`;
  form            : FormGroup;

  constructor(private route: ActivatedRoute,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router) { }

  ngOnInit() {

    let employee_id = Number((this.route.snapshot.params.id))

    this.form = this.formBuilder.group({
      id: [],
      full_name: [],
      email: [],
      mobile: [],
      role: [],
    });

    this.http.get<User>(`${this.API_URL}/users/${employee_id}`).subscribe(
      res => {
        this.form.setValue(res);
      }
    );
  }

  public onSubmit() {
    let employee = this.form.value;
    this.http.put<User>(`${this.API_URL}/users/${employee.id}`, employee).subscribe(
      res => {
        console.log(res);
        this.toastr.success('the account has been correctly modified ');;
        this.router.navigate(["admin/employees"]);
      }
    );
  }
}
