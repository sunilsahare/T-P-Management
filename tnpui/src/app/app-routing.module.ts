import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './pages/feature/user/user-list/user-list.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { AlumniComponent } from './pages/feature/alumni/alumni.component';
import { CompaniesComponent } from './pages/feature/companies/companies.component';
import { DashboardComponent } from './pages/feature/dashboard/dashboard.component';
import { EventsComponent } from './pages/feature/events/events.component';
import { JobsComponent } from './pages/feature/jobs/jobs.component';
import { MatchingComponent } from './pages/feature/matching/matching.component';
import { NotificationComponent } from './pages/feature/notification/notification.component';
import { ReportsComponent } from './pages/feature/reports/reports.component';
import { ResumeComponent } from './pages/feature/resume/resume.component';
import { EmployerListComponent } from './pages/feature/user/employer-list/employer-list.component';
import { EmployerComponent } from './pages/feature/user/operations/employer/employer.component';
import { StudentComponent } from './pages/feature/user/operations/student/student.component';
import { MainLayoutComponent } from './pages/main-layout/main-layout.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: StudentComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Default route

  // ------- USER TAB -----
  {
    path: 'tnp',
    component: MainLayoutComponent,
    children: [
      //---  DASHBOARD TAB ----
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }, // Default child route
      { path: 'dashboard', component: DashboardComponent },
      
      //---  USER TAB ----
      { path: 'user/employer-list', component: EmployerListComponent },
      { path: 'user/student-list', component: UserListComponent },
      { path: 'user/student', component: StudentComponent },
      { path: 'user/employer', component: EmployerComponent },
      
      { path: 'students', component: StudentComponent },
      { path: 'employee', component: JobsComponent },

      //---  ALUMNI TAB ----
      { path: 'alumni', component: AlumniComponent },

      //---  NOTIFICATIONS TAB ----
      { path: 'notifications', component: NotificationComponent },
      
      //---  COMPANIES TAB ----
      { path: 'companies', component: CompaniesComponent },
      
      //---  RESUMES TAB ----
      { path: 'resumes', component: ResumeComponent },
      
      //---  JOB-LIST TAB ----
      { path: 'job-list', component: JobsComponent },
      
      //---  REPORTS TAB ----
      { path: 'reports', component: ReportsComponent },
      
      //---  MATCHING TAB ----
      { path: 'matching', component: MatchingComponent },
      
      //---  EVENTS TAB ----
      { path: 'events', component: EventsComponent },
      

    ],
  },

  // { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
