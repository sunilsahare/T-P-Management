import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from 'src/app/service/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  public loginForm:any; 

  constructor(private formBuilder:FormBuilder, private cdref: ChangeDetectorRef
    ,private routerService:RouterService, private snackBar:MatSnackBar) {

  }

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm() {
    this.loginForm = this.formBuilder.group({
      userId: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(25)]],
      password: ['', [Validators.minLength(6), Validators.maxLength(25)]],
    });
  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
 }

  // save Student
  public login() {
    console.log('User - ',this.loginForm);
    console.log('User Form Values - ',this.loginForm.value);
    // write a service to save data
    // dsplay error message
    this.snackBar.open('Login Successful', 'Success');
    // redirect to user page
    this.routerService.navigateToUrl('tnp','user/student-list');
    

  }

  hasError(fieldName:string):boolean {
    return this.loginForm.get(fieldName).invalid && (this.loginForm.get(fieldName).dirty || this.loginForm.get(fieldName).touched);
  }


}
