import { Injectable } from "@angular/core";
import { UserRoles } from "../enum/user-roles";
import { RouteConst } from "../constants/route-const";
import { AccessControlService } from "./access-control.service";

@Injectable({
  providedIn: "root",
})
export class MenuService {
  constructor(private accessControlService: AccessControlService) {}

  public getMenuForUserRole(): MenuItem[] {
    const USER_ROLE = this.accessControlService.getUserRole();
    return [
      {
        label: "Dashboard",
        link: RouteConst.DASHBOARD_LINK,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_EMPLOYER ||
          USER_ROLE == UserRoles.ROLE_STUDENT ||
          USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
          USER_ROLE == UserRoles.ROLE_ALUMNI ||
          USER_ROLE == UserRoles.ROLE_PARTNER),
        subMenu: [],
        icon: "fa fa-house-chimney"
      },
      {
        label: "Users",
        link: RouteConst.USERS,
        visibilityCondition: (USER_ROLE == UserRoles.ROLE_ADMIN || USER_ROLE == UserRoles.ROLE_STUDENT),
        icon: "fa-solid fa-user",
        subMenu:[]
      },
      {
        label: "Job Listings",
        link: RouteConst.JOB_LIST_LINK,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_EMPLOYER ||
          USER_ROLE == UserRoles.ROLE_STUDENT ||
          USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
          USER_ROLE == UserRoles.ROLE_ALUMNI ||
          USER_ROLE == UserRoles.ROLE_PARTNER),
        subMenu: [],
        icon: "fa-solid fa-network-wired"
      },
      {
        label: "Reports",
        link: null,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN ||
          USER_ROLE == UserRoles.ROLE_ALUMNI),
        subMenu: [
          {
            label: "Student Report",
            link: "student-report",
            visibilityCondition: 
            (USER_ROLE == UserRoles.ROLE_ADMIN ||
            USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
            USER_ROLE == UserRoles.ROLE_ALUMNI),
            icon: "fas fa-chart-pie"
          },
          {
            label: "Employer Report",
            link: "employer-report",
            visibilityCondition: 
              (USER_ROLE == UserRoles.ROLE_ADMIN ||
              USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
              USER_ROLE == UserRoles.ROLE_ALUMNI),
            icon: "fas fa-list"
          },
        ],
        icon : "fa-solid fa-chart-pie"
      },
      {
        label: "Matching",
        link: RouteConst.MATCHING_LINK,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN ||
          USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
          USER_ROLE == UserRoles.ROLE_ALUMNI),
        subMenu: [],
        icon: "fas fa-puzzle-piece"
      },
      {
        label: "Resumes",
        link: RouteConst.RESUMES_LINK,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN ||
          USER_ROLE == UserRoles.ROLE_STUDENT ||
          USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
          USER_ROLE == UserRoles.ROLE_ALUMNI),
        subMenu: [],
        icon : "fa-solid fa-file"
      },
      {
        label: "Companies",
        link: RouteConst.COMPANIES_LINK,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN ||
          USER_ROLE == UserRoles.ROLE_STUDENT ||
          USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
          USER_ROLE == UserRoles.ROLE_ALUMNI),
        subMenu: [],
        icon: "fa-solid fa-house-laptop"
      },
      {
        label: "Notifications",
        link: RouteConst.NOTIFICATIONS_LINK,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN ||
          USER_ROLE == UserRoles.ROLE_STUDENT ||
          USER_ROLE == UserRoles.ROLE_EMPLOYER ||
          USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
          USER_ROLE == UserRoles.ROLE_ALUMNI),
        subMenu: [],
        icon: "fa-solid fa-bell"
      },
      {
        label: "Settings",
        link: RouteConst.SETTINGS_LINK,
        visibilityCondition: (USER_ROLE == UserRoles.ROLE_ADMIN),
        subMenu: [],
        icon: "fa-solid fa-gear ",
      },
      {
        label: "Alumni",
        link: RouteConst.ALUMNI_LINK,
        visibilityCondition:(USER_ROLE == UserRoles.ROLE_ADMIN || USER_ROLE == UserRoles.ROLE_STUDENT),
        subMenu: [],
        icon: "fa-solid fa-user-group"
      },
      {
        label: "Events",
        link: RouteConst.EVENTS_LINK,
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN ||
          USER_ROLE == UserRoles.ROLE_STUDENT ||
          USER_ROLE == UserRoles.ROLE_TNP_OFFICER ||
          USER_ROLE == UserRoles.ROLE_ALUMNI),
        subMenu: [],
        icon: "fa-solid fa-star"
      },
      {
        label: "Internships",
        link: "internships",
        visibilityCondition: (USER_ROLE == UserRoles.ROLE_ADMIN || USER_ROLE == UserRoles.ROLE_STUDENT),
        subMenu: [],
        icon: "fa-solid fa-code"
      },
      {
        label: "Applications",
        link: "applications",
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN || USER_ROLE == UserRoles.ROLE_STUDENT
            || USER_ROLE == UserRoles.ROLE_EMPLOYER),
        subMenu: [],
        icon: "fas fa-briefcase"
      },
      {
        label: "Career Resources",
        link: "career-resources",
        visibilityCondition:
          (USER_ROLE == UserRoles.ROLE_ADMIN || USER_ROLE == UserRoles.ROLE_STUDENT
          || USER_ROLE == UserRoles.ROLE_ALUMNI),
        subMenu: [],
        icon: "fas fa-book"
      },

      {
        label: "My Company Profile",
        link: RouteConst.COMPANY_PROFILE,
        visibilityCondition: (USER_ROLE == UserRoles.ROLE_EMPLOYER),
        subMenu: [],
        icon: "fa fa-users"
      },
      {
        label: "Post Job",
        link: "post-job",
        visibilityCondition: (USER_ROLE == UserRoles.ROLE_EMPLOYER),
        subMenu: [],
        icon: "fas fa-clipboard-list"
      },
      {
        label: "Manage Listings",
        link: "manage-listings",
        visibilityCondition: (USER_ROLE == UserRoles.ROLE_EMPLOYER),
        subMenu: [],
        icon: "fas fa-clipboard-list"
      },
      {
        label: "Student Profiles",
        link: "/student-profiles",
        visibilityCondition: USER_ROLE == UserRoles.ROLE_EMPLOYER,
        subMenu: [],
        icon: "fas fa-user-graduate"
      },
      {
        label: "Mentorship",
        link: "/mentorship",
        visibilityCondition: USER_ROLE == UserRoles.ROLE_ALUMNI,
        subMenu: [],
        icon: "fas fa-chalkboard-teacher"
      },
      {
        label: "My Partner Profile",
        link: "/partner-profile",
        visibilityCondition: USER_ROLE == UserRoles.ROLE_PARTNER,
        subMenu: [],
        icon: "fas fa-users"
      },
      {
        label: "Post Opportunities",
        link: "/post-opportunities",
        visibilityCondition: (USER_ROLE == UserRoles.ROLE_EMPLOYER || USER_ROLE == UserRoles.ROLE_PARTNER),
        subMenu: [],
        icon: "fas fa-bullhorn"
      },
      {
        label: "Manage Opportunities",
        link: "/manage-opportunities",
        visibilityCondition: USER_ROLE == UserRoles.ROLE_PARTNER,
        subMenu: [],
        icon: "fa fa-tasks"
      }
    ];


  }
}

interface MenuItem {
  label: string;
  link: string | null;
  visibilityCondition: boolean;
  subMenu: SubMenuItem[];
  icon?: string | null;
}

interface SubMenuItem {
  label: string;
  link: string;
  visibilityCondition: boolean;
  icon?: string | null;
}
