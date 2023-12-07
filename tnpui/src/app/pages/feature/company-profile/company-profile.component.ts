import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserRoles } from 'src/app/enum/user-roles';
import { User } from 'src/app/model/user-model';
import { AccessControlService } from 'src/app/service/access-control.service';
import { AlertService } from 'src/app/service/alert.service';
import { CryptoService } from 'src/app/service/crypto.service';
import { RouterService } from 'src/app/service/router.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {
  public messageData: any = null;
  // alertComponent!: AlertMessageComponent;
  public currentRouteUrl;
  public companyProfileForm: any;

  constructor(
    private formBuilder: FormBuilder,
    private cdref: ChangeDetectorRef,
    private routerService: RouterService,
    private router: Router,
    private userService: UserService,
    private alertService:AlertService,
    public accessControlService:AccessControlService
  ) {
    this.currentRouteUrl = this.router.url;
    console.log('this.currentRouteUrl', this.currentRouteUrl);
  }

  ngOnInit(): void {
    this.initForm();
  }

  private initForm() {
    this.companyProfileForm = this.formBuilder.group({
      userId: [''],
      companyProfileId: [''],
      companyName: ['', [Validators.required, Validators.maxLength(64)]],
      logoUrl: ['',[]],
      industry: ['', [Validators.maxLength(64)]],
      description: ['', [Validators.required, Validators.maxLength(1024)]],
      location: ['', [Validators.required, Validators.maxLength(1024)]],
      website: ['', [Validators.maxLength(64)]],
      foundedYear: [null, Validators.pattern('^[0-9]*$')],
      companySize: [null, Validators.pattern('^[0-9]*$')],
      typeOfCompany: ['',[Validators.maxLength(1024)]],
      contactInformation: ['', [Validators.maxLength(1024)]],
      benefits: ['', [Validators.maxLength(1024)]],
      companyCulture: ['', [Validators.maxLength(1024)]],
      mission: ['', [Validators.maxLength(1024)]],
      vision: ['', [Validators.maxLength(1024)]],
      specializations: ['', [Validators.maxLength(1024)]],
      awardsOrRecognitions: ['', [Validators.maxLength(1024)]],
      newsOrUpdates: ['', [Validators.maxLength(1024)]]
    });
  }


  /**
   * Remove Control from Student Form
   * 
   */
  private removeControlFromStudentForm(controlName:string) {
      this.companyProfileForm.removeControl(controlName);
  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
  }

  // save Student
  public saveStudent() {
    console.log('Student - ', this.companyProfileForm);
    console.log('Student Form Values - ', this.companyProfileForm.value);
    // if (this.companyProfileForm.valid) {
    //   const user: User = this.mapFormGroupToUser();
    //   this.userService.saveUser(user).subscribe(
    //     (response:any) => {
    //       this.alertService.success("User Successfully Registered");
    //       if(this.currentRouteUrl === '/register'){
    //         this.routerService.navigateToUrl('', '/login');
    //       } else {
    //         this.routerService.navigateToUrl('tnp', 'user');
    //       }
    //     },
    //     (responseError:any) => {
    //       this.alertService.failed("User Registration Failed.");
    //       const fieldErrors:FieldError[] = responseError.error.fieldErrors;

    //        // Set form control errors based on backend validation errors
    //        fieldErrors.forEach((fieldError) => {
    //         this.companyProfileForm.get(fieldError.field).setErrors({ 'backendValidation': true , message:fieldError.message});
    //       });

    //     }
    //   );
    // }
  }

  mapFormGroupToUser(): User {
    let user: User = {
      userId: 0,
      username: CryptoService.encrypt(this.companyProfileForm.value.username),
      password: CryptoService.encrypt(this.companyProfileForm.value.password),
      fullName: this.companyProfileForm.value.fullName,
      gender: this.companyProfileForm.value.gender,
      email: this.companyProfileForm.value.email,
      mobile: this.companyProfileForm.value.mobile,
      role : '',
      profilePictureUrl: '',
      address: this.companyProfileForm.value.address,
      active:false
    };
    user.role = this.currentRouteUrl === '/register' ? CryptoService.encrypt(UserRoles.ROLE_STUDENT)
                 : CryptoService.encrypt(this.companyProfileForm.value.role);
    return user;
  }

  hasError(fieldName: string): boolean {
    return (
      this.companyProfileForm.get(fieldName).invalid &&
      (this.companyProfileForm.get(fieldName).dirty ||
        this.companyProfileForm.get(fieldName).touched)
    );
  }

  getFormControl(controlName: string) {
    return this.companyProfileForm.get(controlName);
  }

}
