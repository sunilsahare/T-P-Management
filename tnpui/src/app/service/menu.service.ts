import { Injectable } from '@angular/core';
import { UserRoles } from '../enum/user-roles';
import { RouteConst } from '../constants/route-const';

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  public static getMenuForUserRole(userRole: string): MenuItem[] {
    switch (userRole) {
      case UserRoles.ROLE_ADMIN:
        return adminMenu;
      case UserRoles.ROLE_STUDENT:
        return studentMenu;
      case UserRoles.ROLE_EMPLOYER:
        return employerMenu;
      case UserRoles.ROLE_ALUMNI:
        return alumniMenu;
      case UserRoles.ROLE_HOD:
        return partnerMenu;
      default:
        return [];
    }
  }
}

interface MenuItem {
  label: string;
  link: string | null;
  visibilityCondition: boolean
  subMenu:SubMenuItem []
}

interface SubMenuItem {
  label: string;
  link: string;
  visibilityCondition: boolean
}

const adminMenu: MenuItem[] = [
  { label: 'Dashboard', link: RouteConst.DASHBOARD_LINK, visibilityCondition: true, subMenu:[]},
  { label: 'Users', link: null , visibilityCondition: true, subMenu:[
    {label: 'Student List', link: RouteConst.STUDENT_LIST_LINK, visibilityCondition: true},
    {label: 'Employer List', link: RouteConst.EMPLOYER_LIST_LINK, visibilityCondition: true},
  ]},
  { label: 'Job Listings', link: RouteConst.JOB_LIST_LINK, visibilityCondition: true, subMenu:[]},
  { label: 'Reports', link: null , visibilityCondition: true, subMenu:[
    {label: 'Student Report', link: 'tnp/student-report', visibilityCondition: true},
    {label: 'Employer Report', link: 'tnp/employer-report', visibilityCondition: true},
  ]},
  { label: 'Matching', link: RouteConst.MATCHING_LINK , visibilityCondition: true, subMenu:[]},
  { label: 'Resumes', link: RouteConst.RESUMES_LINK , visibilityCondition: true, subMenu:[]},
  { label: 'Companies', link: RouteConst.COMPANIES_LINK , visibilityCondition: true, subMenu:[]},
  { label: 'Notifications', link: RouteConst.NOTIFICATIONS_LINK , visibilityCondition: true, subMenu:[]},
  { label: 'Settings', link: RouteConst.SETTINGS_LINK , visibilityCondition: true, subMenu:[]},
  { label: 'Alumni', link: RouteConst.ALUMNI_LINK , visibilityCondition: true, subMenu:[]},
  { label: 'Events', link: RouteConst.EVENTS_LINK , visibilityCondition: true, subMenu:[]},
];

const studentMenu: MenuItem[] = [
  { label: 'Dashboard', link: '/dashboard' , visibilityCondition: true, subMenu:[]},
  { label: 'Job Listings', link: '/job-listings' , visibilityCondition: true, subMenu:[]},
  { label: 'Internships', link: '/internships' , visibilityCondition: true, subMenu:[]},
  { label: 'Applications', link: '/applications' , visibilityCondition: true, subMenu:[]},
  { label: 'Resumes', link: '/resumes' , visibilityCondition: true, subMenu:[]},
  { label: 'Companies', link: '/companies' , visibilityCondition: true, subMenu:[]},
  { label: 'Notifications', link: '/notifications' , visibilityCondition: true, subMenu:[]},
  { label: 'Career Resources', link: '/career-resources' , visibilityCondition: true, subMenu:[]},
];

const employerMenu: MenuItem[] = [
  { label: 'Dashboard', link: '/dashboard' , visibilityCondition: true, subMenu:[]},
  { label: 'My Company Profile', link: '/company-profile' , visibilityCondition: true, subMenu:[]},
  { label: 'Post Job', link: '/post-job' , visibilityCondition: true, subMenu:[]},
  { label: 'Manage Listings', link: '/manage-listings' , visibilityCondition: true, subMenu:[]},
  { label: 'Applications', link: '/applications' , visibilityCondition: true, subMenu:[]},
  { label: 'Student Profiles', link: '/student-profiles' , visibilityCondition: true, subMenu:[]},
  { label: 'Notifications', link: '/notifications' , visibilityCondition: true, subMenu:[]},
];

const alumniMenu: MenuItem[] = [
  { label: 'Dashboard', link: '/dashboard' , visibilityCondition: true, subMenu:[]},
  { label: 'My Profile', link: '/profile' , visibilityCondition: true, subMenu:[]},
  { label: 'Mentorship', link: '/mentorship' , visibilityCondition: true, subMenu:[]},
  { label: 'Notifications', link: '/notifications' , visibilityCondition: true, subMenu:[]},
  { label: 'Career Resources', link: '/career-resources' , visibilityCondition: true, subMenu:[]},
];

const partnerMenu: MenuItem[] = [
  { label: 'Dashboard', link: '/dashboard' , visibilityCondition: true, subMenu:[]},
  { label: 'My Partner Profile', link: '/partner-profile' , visibilityCondition: true, subMenu:[]},
  { label: 'Post Opportunities', link: '/post-opportunities' , visibilityCondition: true, subMenu:[]},
  { label: 'Manage Opportunities', link: '/manage-opportunities' , visibilityCondition: true, subMenu:[]},
  { label: 'Notifications', link: '/notifications' , visibilityCondition: true, subMenu:[]},
];

const hodMenu: MenuItem[] = [
  { label: 'Dashboard', link: '/dashboard' , visibilityCondition: true, subMenu:[]},
  { label: 'Students', link: '/students' , visibilityCondition: true, subMenu:[]},
  { label: 'My Profile', link: '/profile' , visibilityCondition: true, subMenu:[]},
  { label: 'Job Listings', link: '/job-listings' , visibilityCondition: true, subMenu:[]},
  { label: 'Internships', link: '/internships' , visibilityCondition: true, subMenu:[]},
  { label: 'Applications', link: '/applications' , visibilityCondition: true, subMenu:[]},
  { label: 'Resumes', link: '/resumes' , visibilityCondition: true, subMenu:[]},
  { label: 'Companies', link: '/companies' , visibilityCondition: true, subMenu:[]},
  { label: 'Notifications', link: '/notifications' , visibilityCondition: true, subMenu:[]},
  { label: 'Career Resources', link: '/career-resources' , visibilityCondition: true, subMenu:[]},
  
];
