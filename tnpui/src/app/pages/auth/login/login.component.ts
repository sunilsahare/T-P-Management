import { ChangeDetectorRef, Component } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { APIConst } from "src/app/constants/api-const";
import { AccessControlService } from "src/app/service/access-control.service";
import { AuthenticationService } from "src/app/service/authentication.service";
import { CryptoService } from "src/app/service/crypto.service";
import { RestService } from "src/app/service/rest.service";
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
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.buildForm();
    this.login();
  }

  private buildForm() {
    this.loginForm = this.formBuilder.group({
      username: [
        "sunny",
        [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(25),
        ],
      ],
      password: ["Sunny@123", [Validators.minLength(6), Validators.maxLength(25)]],
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
          // Write a service to save data (if needed)
          this.accessControlService.setAuthDetails(
            loginResponse.mapList.responseData.username, 
            loginResponse.mapList.responseData.role,
            loginResponse.mapList.responseData.jwtToken,        
            loginResponse.mapList.responseData.user);       
          // Display success message
          this.snackBar.open("Login Successful", "Success");
          console.log('JWT - ', this.accessControlService.getJwtToken());
          console.log('role - ', this.accessControlService.getUserRole());
          console.log('id - ', this.accessControlService.getUserId());
          // Redirect to user page
          this.routerService.navigateToUrl("tnp", "user/student-list");
        },
        (error: any) => {
          // Handle authentication error
          console.error("Authentication Error: ", error);
  
          // Display error message
          this.snackBar.open("Login Failed."+error.message, "Close");
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
