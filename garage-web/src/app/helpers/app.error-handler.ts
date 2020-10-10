import {Injectable, ErrorHandler, Inject, Injector} from "@angular/core";
import {Router} from "@angular/router";
import { HttpErrorResponse } from '@angular/common/http';
import {ToastrService} from "ngx-toastr";

@Injectable()
export class AppErrorHandler implements ErrorHandler {
      
  constructor(@Inject(Injector) private injector: Injector) {}

  public handleError(error: any) {
    console.error("Handling error", error);
    
    if (error instanceof HttpErrorResponse) {
      switch (error.status) {
        case 401: //Unauthorize
          let router = this.injector.get(Router);
          router.navigateByUrl("/login");
          break;
        default:
          this.showError("Error calling the Server, please try again");
      }
    }else{
      this.showError("An error occurred, please try again");
    }  
  }
  
  private showError(message:string){
    let toastrService = this.injector.get(ToastrService);
    toastrService.error(message, "Something went wrong", { disableTimeOut: true});
  }

}  