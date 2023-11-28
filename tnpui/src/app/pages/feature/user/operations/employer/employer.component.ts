import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RouterService } from 'src/app/service/router.service';

@Component({
  selector: 'app-employer',
  templateUrl: './employer.component.html',
  styleUrls: ['./employer.component.css']
})
export class EmployerComponent {

  public alertMessage:any;

  public studentForm:any; 

  constructor(private formBuilder:FormBuilder, private cdref: ChangeDetectorRef
    ,private routerService:RouterService,) {

  }

  ngOnInit(): void {
    
    this.studentForm =  this.formBuilder.group({
      fullName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      email: ['', ],
      userId: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(25)]],
      password: ['', [Validators.minLength(6), Validators.maxLength(25)]],
      address: ['', ],
      about: ['', ],
      mobile: ['', [Validators.minLength(10), Validators.maxLength(10)]],
      officialLink: ['', [Validators.required]]
    });

  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
 }

  // save Student
  public saveStudent() {
    console.log('Student - ',this.studentForm);
    console.log('Student Form Values - ',this.studentForm.value);
    
    // write a service to save data

    // dsplay error message
    this.alertMessage = {
      title : "SUCCESS",
      message : "Student Successfully Added.",
      status : 'SUCCESS'
    }

    // redirect to user page
    this.routerService.navigate('student-list');
    

  }

  hasError(fieldName:string):boolean {
    return this.studentForm.get(fieldName).invalid && (this.studentForm.get(fieldName).dirty || this.studentForm.get(fieldName).touched);
  }
  
}