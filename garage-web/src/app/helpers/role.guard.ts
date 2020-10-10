import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(private router: Router,
              private toastr: ToastrService,
              private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isAdmin()) {
        return true;
    }
    this.toastr.error('You are not authorize to this page'); 
    this.router.navigate(['/']);
    return false;
  }
}