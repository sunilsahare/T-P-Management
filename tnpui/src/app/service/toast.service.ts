// toast.service.ts

import { Injectable } from "@angular/core";
import { ToastrService } from "ngx-toastr";

@Injectable({
    providedIn: "root",
})
export class ToastService {

  messageProperties = { closeButton: true, newestOnTop: true, progressBar: true };
  
  public SUCCESS: string = "SUCCESS";
  public FAILURE: string = "FAILURE";
  public INFO: string = "INFO";
  public WARNING: string = "WARNING";

  constructor(private toastr: ToastrService) {}

  info(message: string): void {
    this.showMessage(message, this.INFO);
  }

  success(message: string): void {
    this.showMessage(message, this.SUCCESS);
  }

  error(message: string): void {
    this.showMessage(message, this.FAILURE);
  }

  warning(status: string, message: string): void {
    this.showMessage(message, this.WARNING);
  }
  
  
  showMessage(message:string, status:string) {
    switch (status) {
        case this.SUCCESS:
             this.toastr.success(message, this.SUCCESS, this.messageProperties);
            break;
        case this.INFO:
             this.toastr.info(message, this.INFO, this.messageProperties);
            break;
        case this.WARNING:
             this.toastr.warning(message, this.WARNING, this.messageProperties);
            break;
        case this.FAILURE:
             this.toastr.error(message, this.FAILURE, this.messageProperties);
            break;
    
        default:
            this.toastr.info(message, this.INFO, this.messageProperties);
            break;
    }
  }

  public handleResponse(response: any) {
    if (response.status == 200) {
      this.success(response.message);
    } else {
      this.error(response.message);
    }
    this.handleErrorList(response);
    this.handleFieldErrors(response);
  }

  private handleErrorList(response: any) {
    response.errorList?.forEach((error: { message: any }) => {
      if (response.status == 200) {
        this.success(response.message);
      } else {
        this.error(response.message);
      }
    });
  }

  private handleFieldErrors(response: any) {
    response.fieldErrors?.forEach((error: { message: any }) => {
      if (response.status == 200) {
        this.success(response.message);
      } else {
        this.error(response.message);
      }
    });
  }

}
