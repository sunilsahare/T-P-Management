import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { User } from 'src/app/model/user-model';
import { RouterService } from 'src/app/service/router.service';
import { UserService } from 'src/app/service/user.service';
import { FieldError, ValidationError } from 'src/app/model/error-model';
import { UserRoles } from 'src/app/enum/user-roles';
import { CryptoService } from 'src/app/service/crypto.service';
import { AccessControlService } from 'src/app/service/access-control.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css'],
})
export class StudentComponent implements OnInit {
  public messageData: any = null;
  // alertComponent!: AlertMessageComponent;
  public currentRouteUrl;
  public studentForm: any;

  constructor(
    private formBuilder: FormBuilder,
    private cdref: ChangeDetectorRef,
    private routerService: RouterService,
    private router: Router,
    private userService: UserService,
    private alertService:ToastrService,
    public accessControlService:AccessControlService
  ) {
    this.currentRouteUrl = this.router.url;
    console.log('this.currentRouteUrl', this.currentRouteUrl);
  }

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm() {
    this.studentForm = this.formBuilder.group({
      fullName: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(60),
        ],
      ],
      email: [''],
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(25),
        ],
      ],
      password: ['', [Validators.minLength(6), Validators.maxLength(25)]],
      address: [''],
      mobile: ['', [Validators.minLength(10), Validators.maxLength(10)]],
      gender: ['', [Validators.required]],
      role: ['', [Validators.required]],
    });

    if (this.currentRouteUrl === '/register') {
      this.removeControlFromStudentForm("role");
    }
  }

  /**
   * Remove Control from Student Form
   * 
   */
  private removeControlFromStudentForm(controlName:string) {
      this.studentForm.removeControl(controlName);
  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
  }

  // save Student
  public saveStudent() {
    console.log('Student - ', this.studentForm);
    console.log('Student Form Values - ', this.studentForm.value);
    if (this.studentForm.valid) {
      const user: User = this.mapFormGroupToUser();
      this.userService.saveUser(user).subscribe(
        (response:any) => {
          this.alertService.success("User Successfully Registered");
          if(this.currentRouteUrl === '/register'){
            this.routerService.navigateToUrl('', '/login');
          } else {
            this.routerService.navigateToUrl('tnp', 'user');
          }
        },
        (responseError:any) => {
          this.alertService.error("User Registration Failed.");
          const fieldErrors:FieldError[] = responseError.error.fieldErrors;

           // Set form control errors based on backend validation errors
           fieldErrors.forEach((fieldError) => {
            this.studentForm.get(fieldError.field).setErrors({ 'backendValidation': true , message:fieldError.message});
          });

        }
      );
    }
  }

  mapFormGroupToUser(): User {
    let user: User = {
      userId: 0,
      username: CryptoService.encrypt(this.studentForm.value.username),
      password: CryptoService.encrypt(this.studentForm.value.password),
      fullName: this.studentForm.value.fullName,
      gender: this.studentForm.value.gender,
      email: this.studentForm.value.email,
      mobile: this.studentForm.value.mobile,
      role : '',
      profilePictureUrl: '',
      address: this.studentForm.value.address,
      active:false
    };
    user.role = this.currentRouteUrl === '/register' ? CryptoService.encrypt(UserRoles.ROLE_STUDENT)
                 : CryptoService.encrypt(this.studentForm.value.role);
    return user;
  }

  hasError(fieldName: string): boolean {
    return (
      this.studentForm.get(fieldName).invalid &&
      (this.studentForm.get(fieldName).dirty ||
        this.studentForm.get(fieldName).touched)
    );
  }

  getFormControl(controlName: string) {
    return this.studentForm.get(controlName);
  }

  

}
