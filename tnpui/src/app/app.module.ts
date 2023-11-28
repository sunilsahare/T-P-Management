import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatCheckboxModule } from '@angular/material/checkbox';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { UserListComponent } from './pages/feature/user/user-list/user-list.component';
import { MainLayoutComponent } from './pages/main-layout/main-layout.component';
import { SideNavComponent } from './pages/main-layout/side-nav/side-nav.component';
import { TopNavComponent } from './pages/main-layout/header/top-nav.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { CdkTableModule } from '@angular/cdk/table';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatSortModule } from '@angular/material/sort';
import { EmployerListComponent } from './pages/feature/user/employer-list/employer-list.component';
import { EmployerComponent } from './pages/feature/user/operations/employer/employer.component';
import { StudentComponent } from './pages/feature/user/operations/student/student.component';
import { AlumniComponent } from './pages/feature/alumni/alumni.component';
import { CompaniesComponent } from './pages/feature/companies/companies.component';
import { DashboardComponent } from './pages/feature/dashboard/dashboard.component';
import { EventsComponent } from './pages/feature/events/events.component';
import { JobsComponent } from './pages/feature/jobs/jobs.component';
import { MatchingComponent } from './pages/feature/matching/matching.component';
import { NotificationComponent } from './pages/feature/notification/notification.component';
import { ReportsComponent } from './pages/feature/reports/reports.component';
import { ResumeComponent } from './pages/feature/resume/resume.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    MainLayoutComponent,
    SideNavComponent,
    TopNavComponent,
    LoginComponent,
    EmployerListComponent,
    EmployerComponent,
    StudentComponent,
    AlumniComponent,
    CompaniesComponent,
    DashboardComponent,
    EventsComponent,
    JobsComponent,
    MatchingComponent,
    NotificationComponent,
    ReportsComponent,
    ResumeComponent,
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatCheckboxModule,
    AppRoutingModule,
    HttpClientModule,
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
    MatSnackBarModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }