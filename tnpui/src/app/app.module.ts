import { NgModule } from '@angular/core';
import {MatDialogModule} from "@angular/material/dialog";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SideNavComponent } from './pages/main-layout/side-nav/side-nav.component';
import { UserComponent } from './pages/feature/user/user.component';
import { JobsComponent } from './pages/feature/jobs/jobs.component';
import { MainLayoutComponent } from './pages/main-layout/main-layout.component';
import { TopNavComponent } from './pages/main-layout/header/top-nav.component';
import { DashboardComponent } from './pages/feature/dashboard/dashboard.component';
import { ResumeComponent } from './pages/feature/resume/resume.component';
import { CompaniesComponent } from './pages/feature/companies/companies.component';
import { NotificationComponent } from './pages/feature/notification/notification.component';
import { AlumniComponent } from './pages/feature/alumni/alumni.component';
import { MatchingComponent } from './pages/feature/matching/matching.component';
import { ReportsComponent } from './pages/feature/reports/reports.component';
import { UserListComponent } from './pages/feature/user/user-list/user-list.component';
import { EmployerListComponent } from './pages/feature/user/employer-list/employer-list.component';
import { EventsComponent } from './pages/feature/events/events.component';
import { AgGridModule } from '@ag-grid-community/angular';
import { HttpClientModule } from '@angular/common/http';
import { EmployerComponent } from './pages/feature/user/operations/employer/employer.component';
import { StudentComponent } from './pages/feature/user/operations/student/student.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { BrowserModule } from '@angular/platform-browser';
import { ToastrModule } from 'ngx-toastr/toastr/toastr.module';
import { ToastrService } from 'ngx-toastr/toastr/toastr.service';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';

import { CdkTableModule } from '@angular/cdk/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    MainLayoutComponent,
    SideNavComponent,
    UserComponent,
    JobsComponent,
    TopNavComponent,
    DashboardComponent,
    ResumeComponent,
    CompaniesComponent,
    NotificationComponent,
    AlumniComponent,
    MatchingComponent,
    ReportsComponent,
    UserListComponent,
    EmployerListComponent,
    EventsComponent,
    StudentComponent,
    EmployerComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AgGridModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    // AlertModule.forRoot(), // ngx-bootstrap alert module
    // ToastrModule.forRoot(), // ngx-toastr module
    MatPaginatorModule,
    MatTableModule,
    FormsModule,
    MatSortModule,
    CdkTableModule,
    BrowserAnimationsModule,
    MatTableDataSource
  ],
  providers: [ToastrService],
  bootstrap: [AppComponent]
})
export class AppModule { }
