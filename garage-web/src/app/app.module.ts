import { AppErrorHandler } from './helpers/app.error-handler';
import { EditMyBookingComponent } from './components/bookings/edit-my-booking/edit-my-booking.component';
import { EditCustomerComponent } from './components/customers/edit-customer/edit-customer.component';
import { AddCustomerComponent } from './components/customers/add-customer/add-customer.component';
import { LoginComponent } from './login/login.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './routes/app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { AddBookingComponent } from './components/bookings/add-booking/add-booking.component';
import { ListCustomersComponent } from './components/customers/list-customers/list-customers.component';
import { ShowCustomerComponent } from './components/customers/show-customer/show-customer.component';
import { ListBookingsComponent } from './components/bookings/list-bookings/list-bookings.component';
import { ShowBookingComponent } from './components/bookings/show-booking/show-booking.component';
import { ListEmployeesComponent } from './components/employees/list-employees/list-employees.component';
import { ShowEmployeeComponent } from './components/employees/show-employee/show-employee.component';
import { AddEmployeeComponent } from './components/employees/add-employee/add-employee.component';
import { HttpInInterceptor } from './helpers/http-in.interceptor';
import { HttpOutInterceptor } from './helpers/http-out.interceptor';
import { EditBookingComponent } from './components/bookings/edit-booking/edit-booking.component';
import { AuthGuard } from './helpers/auth.guard';
import { AuthService } from './helpers/auth.service';
import { RoleGuard } from './helpers/role.guard';
import { EditEmployeeComponent } from './components/employees/edit-employee/edit-employee.component';

@NgModule({
  // Components
  declarations: [ 
    AppComponent,
    AddBookingComponent,
    ListCustomersComponent,
    ShowCustomerComponent,
    ListBookingsComponent,
    ShowBookingComponent,
    ListEmployeesComponent,
    ShowEmployeeComponent,
    AddEmployeeComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    AddCustomerComponent,
    EditBookingComponent,
    EditCustomerComponent,
    EditEmployeeComponent,
    EditMyBookingComponent,

  ],
  // Libraries
  imports: [  
    BrowserModule,
    BrowserAnimationsModule, 
    FormsModule,
    ToastrModule.forRoot({preventDuplicates: true}),
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    LoadingBarHttpClientModule,
  ],
  
  //Interceptors
  providers: [
    AuthGuard,
    RoleGuard,
    AuthService,
    { provide: ErrorHandler, useClass: AppErrorHandler},
    { provide: HTTP_INTERCEPTORS, useClass: HttpOutInterceptor, multi: true },
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
