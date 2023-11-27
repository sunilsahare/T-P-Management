import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { User } from 'src/app/model/user-model';
import { RouterService } from 'src/app/service/router.service';
import { UserService } from 'src/app/service/user.service';

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
    private _snackBar: MatSnackBar
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
          Validators.minLength(3),
          Validators.maxLength(60),
        ],
      ],
      email: [''],
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(25),
        ],
      ],
      password: ['', [Validators.minLength(6), Validators.maxLength(25)]],
      address: [''],
      mobile: ['', [Validators.minLength(10), Validators.maxLength(10)]],
      gender: ['', [Validators.required]],
    });
  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
  }

  // save Student
  public saveStudent() {
    console.log('Student - ', this.studentForm);
    console.log('Student Form Values - ', this.studentForm.value);
    // write a service to save data
    // dsplay error message
    alert('Student Successfully Registered');
    // redirect to user page
    this.routerService.navigateToUrl('tnp', 'user/student-list');

    if (this.studentForm.valid) {
      const user: User = this.mapFormGroupToUser();
      this.userService.saveUser(user).subscribe(
        (response:any) => {
          console.log('user Save respose: ', response);
        },
        (error:any) => {
          // Handle error
          console.log('user Save respose: ', error);
        }
      );
    }
  }

  mapFormGroupToUser(): User {
    const user: User = {
      userId: 0,
      username: this.studentForm.value.username,
      password: this.studentForm.value.password,
      fullName: this.studentForm.value.fullName,
      gender: this.studentForm.value.gender,
      email: this.studentForm.value.email,
      mobile: this.studentForm.value.mobile,
      role: this.studentForm.value.role,
      profilePictureUrl: '',
      address: this.studentForm.value.address,
      active:null
    };

    return user;
  }

  hasError(fieldName: string): boolean {
    return (
      this.studentForm.get(fieldName).invalid &&
      (this.studentForm.get(fieldName).dirty ||
        this.studentForm.get(fieldName).touched)
    );
  }
}
