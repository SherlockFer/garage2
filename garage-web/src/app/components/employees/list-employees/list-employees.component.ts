import { User } from './../../../models/user.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from "@angular/router";
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Response } from '../../../models/response.model';

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
})
export class ListEmployeesComponent implements OnInit {

  mechanics: User[];

  constructor(private router: Router,
              private toastr: ToastrService,
              private http: HttpClient,
              private route: ActivatedRoute) { }

  API_URL = `${environment.apiUrl}/users`;

  ngOnInit() {
    this.http.get<User[]>(`${this.API_URL}` + "?filter[role]=mechanic").subscribe(
      res => { this.mechanics = res; },
    );
  }

  public editEmployee(employee: User) {
    let employeeId:number;
    employeeId=employee.id;
    this.router.navigate([`/admin/employees/edit/${employeeId}`]);
  }

}
