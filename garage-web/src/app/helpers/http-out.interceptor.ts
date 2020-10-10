import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable()
export class HttpOutInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('[LOG_OUT_INTERCEPTOR] Request:', request['method'], request['urlWithParams']);   
    if (this.authService.isLoggedIn) {
      console.log('[LOG_OUT_INTERCEPTOR] Adding token header to HTTP request');
      request = request.clone({
        setHeaders: {
          "Authorization": `token ${localStorage.getItem('token')}`
        }
      });
    }
    return next.handle(request);
  }
}