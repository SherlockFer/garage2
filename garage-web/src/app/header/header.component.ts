import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../helpers/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, public authService: AuthService) { }

  ngOnInit() {
    
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
  

}
