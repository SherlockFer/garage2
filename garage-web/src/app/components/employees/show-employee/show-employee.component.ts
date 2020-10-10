import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { Router } from "@angular/router";
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Response } from '../../../models/response.model';


@Component({
  selector: 'app-show-employee',
  templateUrl: './show-employee.component.html',
})
export class ShowEmployeeComponent implements OnInit {

  mechanic: User;
  isLoading: boolean = true;

  constructor(private router: Router,
              private toastr: ToastrService,
              private http: HttpClient,
              private route: ActivatedRoute) { }

    API_URL = `${environment.apiUrl}/users`;

  ngOnInit() {
    let id=Number((this.route.snapshot.params.id))
    this.showMechanic(id);
  }

  public showMechanic(id: Number){
    this.http.get<User>(`${this.API_URL}/${id}`).subscribe(
      res => { this.mechanic= res;this.isLoading = false; },
    ); 
  }


}
