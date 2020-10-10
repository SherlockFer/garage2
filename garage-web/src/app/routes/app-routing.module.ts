

//Components
import { ShowEmployeeComponent } from './../components/employees/show-employee/show-employee.component';
import { ListBookingsComponent } from './../components/bookings/list-bookings/list-bookings.component';
import { ShowCustomerComponent } from './../components/customers/show-customer/show-customer.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from '../app.component';
import { ListCustomersComponent } from '../components/customers/list-customers/list-customers.component';
import { AddBookingComponent } from '../components/bookings/add-booking/add-booking.component';
import { ShowBookingComponent } from '../components/bookings/show-booking/show-booking.component';
import { ListEmployeesComponent } from '../components/employees/list-employees/list-employees.component';
import { HomeComponent } from '../home/home.component';
import { LoginComponent } from '../login/login.component';
import { AddCustomerComponent } from '../components/customers/add-customer/add-customer.component';
import { EditBookingComponent } from '../components/bookings/edit-booking/edit-booking.component';
import { AuthGuard } from '../helpers/auth.guard';
import { RoleGuard } from '../helpers/role.guard';
import { EditCustomerComponent } from '../components/customers/edit-customer/edit-customer.component';
import { EditEmployeeComponent } from '../components/employees/edit-employee/edit-employee.component';
import { EditMyBookingComponent } from '../components/bookings/edit-my-booking/edit-my-booking.component';

//Routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent},
  { path: 'sign-up', component: AddCustomerComponent},

  { path: 'my-bookings/new', component: AddBookingComponent, canActivate: [AuthGuard] }, 
  { path: 'my-bookings/edit/:id', component: EditMyBookingComponent, canActivate: [AuthGuard] }, 
  { path: 'my-bookings', component: ListBookingsComponent, canActivate: [AuthGuard] },  
  { path: 'my-account/edit/:id', component: EditCustomerComponent, canActivate: [AuthGuard] },
  { path: 'my-bookings/invoice/:id', component: ShowBookingComponent, canActivate: [AuthGuard] },


  { path: 'admin/bookings', component: ListBookingsComponent, canActivate: [RoleGuard] }, 
  { path: 'admin/booking/edit/:id', component: EditBookingComponent, canActivate: [RoleGuard] },  
  { path: 'admin/bookings/:id', component: ShowBookingComponent, canActivate: [RoleGuard] },
  { path: 'admin/customers', component: ListCustomersComponent, canActivate: [RoleGuard] }, 
  { path: 'admin/customers/edit/:id', component: EditCustomerComponent, canActivate: [RoleGuard] }, 
  { path: 'admin/customers/:id', component: ShowCustomerComponent, canActivate: [RoleGuard] }, 
  { path: 'admin/employees', component: ListEmployeesComponent, canActivate: [RoleGuard] }, 
  { path: 'admin/employees/edit/:id', component:EditEmployeeComponent, canActivate: [RoleGuard] }, 
  { path: 'admin/employees/:id', component: ShowEmployeeComponent, canActivate: [RoleGuard] },
  
  { path: '**', redirectTo: '/', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
