import { ChangeDetectorRef, Component } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { ToastrService } from "ngx-toastr";
import { AccessControlService } from "src/app/service/access-control.service";
import { AuthenticationService } from "src/app/service/authentication.service";
import { CryptoService } from "src/app/service/crypto.service";
import { RouterService } from "src/app/service/router.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent {
  public loginForm: any;

  constructor(
    private formBuilder: FormBuilder,
    private cdref: ChangeDetectorRef,
    private authService: AuthenticationService,
    private accessControlService: AccessControlService,
    private routerService: RouterService,
    private alertService:ToastrService,
  ) {}

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm() {
    this.loginForm = this.formBuilder.group({
      username: [
        "",
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(25),
        ],
      ],
      password: ["", [Validators.minLength(6), Validators.maxLength(25)]],
    });
  }

  ngAfterContentChecked() {
    // this.cdref.detectChanges();
  }

  public login() {
    console.log("User Form Values - ", this.loginForm.value);
    const encryptedUsername = CryptoService.encrypt(this.loginForm.value.username);
    const encryptedPassword = CryptoService.encrypt(this.loginForm.value.password);
  
    this.authService.authenticate(encryptedUsername, encryptedPassword)
      .subscribe(
        (loginResponse: any) => {
          console.log("Login Response: ", loginResponse);
          this.accessControlService.setAuthDetails(
            loginResponse.mapList.responseData.username, 
            loginResponse.mapList.responseData.role,
            loginResponse.mapList.responseData.jwtToken,        
            loginResponse.mapList.responseData.user);       
          this.alertService.success("Login Successful");
          console.log('JWT - ', this.accessControlService.getJwtToken());
          console.log('role - ', this.accessControlService.getUserRole());
          console.log('id - ', this.accessControlService.getUserId());
          // Redirect to user page
          this.routerService.navigateToUrl("tnp", "user");
        },
        (error: any) => {
          console.error("Authentication Error: ", error);
          const errorResponse = error.error;
          this.alertService.error(errorResponse.message);
        }
      );
  }
  

  hasError(fieldName: string): boolean {
    return (
      this.loginForm.get(fieldName).invalid &&
      (this.loginForm.get(fieldName).dirty ||
        this.loginForm.get(fieldName).touched)
    );
  }
}
