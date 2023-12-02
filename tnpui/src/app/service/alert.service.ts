import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class AlertService {
  constructor(private snackbar: MatSnackBar) {}

  public success(message: string, duration?: number) {
    this.snackbar.open(message, 'Success', {
      duration: duration ? duration : 3000,
    });
  }

  public failed(message: string, duration?: number) {
    this.snackbar.open(message, 'Failed', {
      duration: duration ? duration : 3000,
    });
  }

  public handleResponse(response: any) {
    if (response.status == 200) {
      this.success(response.message);
    } else {
      this.failed(response.message);
    }
    this.handleErrorList(response);
    this.handleFieldErrors(response);
  }

  private handleErrorList(response: any) {
    response.errorList?.forEach((error: { message: any; }) => {
      if (response.status == 200) {
        this.success(error.message);
      } else {
        this.failed(error.message);
      }
    });
  }

  private handleFieldErrors(response: any) {
    response.fieldErrors?.forEach((error: { message: any; }) => {
      if (response.status == 200) {
        this.success(error.message);
      } else {
        this.failed(error.message);
      }
    });
  }

}
