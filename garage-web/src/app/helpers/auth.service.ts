import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable()
export class AuthService {

  BOOKING_API = `${environment.apiUrl}/token`;

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    return this.http.post<any>(this.BOOKING_API, {email, password })
      .pipe(
        map(res => {
         localStorage.setItem('token', res['token']);
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
  }

  isLoggedIn() {
    let token = localStorage.getItem('token')
    return token != null
  }

  isAdmin(): boolean{
    let jwtHelper = new JwtHelperService();
    let decodeToken = jwtHelper.decodeToken(localStorage.getItem('token')); 
    return decodeToken!=null && decodeToken['role']==="admin";
  }

  getUserName():string{
    let jwtHelper = new JwtHelperService();
    let decodeToken = jwtHelper.decodeToken(localStorage.getItem('token')); 
    return decodeToken!=null && decodeToken['full_name'];
  }

}