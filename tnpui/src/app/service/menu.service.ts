import { Injectable } from "@angular/core";
import { UserRoles } from "../enum/user-roles";
import { RouteConst } from "../constants/route-const";
import { AccessControlService } from "./access-control.service";

@Injectable({
  providedIn: "root",
})
export class MenuService {
  constructor(private accessControlService: AccessControlService) {}

  public getMenuForUserRole(userRole: string): MenuItem[] {
    return [
      {
        label: "Dashboard",
        link: RouteConst.DASHBOARD_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [],
        icon: "fa fa-house-chimney"
      },
      {
        label: "Users",
        link: null,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleStudent,
        icon: "fa-solid fa-user",
        subMenu: [
          {
            label: "Student List",
            link: RouteConst.STUDENT_LIST_LINK,
            visibilityCondition: this.accessControlService.hasRoleAdmin,
            icon: "fa fa-user-friends"
          },
          {
            label: "Employer List",
            link: RouteConst.EMPLOYER_LIST_LINK,
            visibilityCondition: this.accessControlService.hasRoleAdmin,
            icon: "fas fa-users"
          },
        ],
      },
      {
        label: "Job Listings",
        link: RouteConst.JOB_LIST_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [],
        icon: "fa-solid fa-network-wired"
      },
      {
        label: "Reports",
        link: null,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [
          {
            label: "Student Report",
            link: "student-report",
            visibilityCondition: 
            this.accessControlService.hasRoleAdmin ||
            this.accessControlService.hasRoleAlumni ||
            this.accessControlService.hasRoleEmployer ||
            this.accessControlService.hasRoleHod ||
            this.accessControlService.hasRoleStudent ||
            this.accessControlService.hasRoleTNP,
            icon: "fas fa-chart-pie"
          },
          {
            label: "Employer Report",
            link: "employer-report",
            visibilityCondition: true,
            icon: "fas fa-list"
          },
        ],
        icon : "fa-solid fa-chart-pie"
      },
      {
        label: "Matching",
        link: RouteConst.MATCHING_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [],
        icon: "fas fa-puzzle-piece"
      },
      {
        label: "Resumes",
        link: RouteConst.RESUMES_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [],
        icon : "fa-solid fa-file"
      },
      {
        label: "Companies",
        link: RouteConst.COMPANIES_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [],
        icon: "fa-solid fa-house-laptop"
      },
      {
        label: "Notifications",
        link: RouteConst.NOTIFICATIONS_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [],
        icon: "fa-solid fa-bell"
      },
      {
        label: "Settings",
        link: RouteConst.SETTINGS_LINK,
        visibilityCondition: this.accessControlService.hasRoleAdmin,
        subMenu: [],
        icon: "fa-solid fa-gear ",
      },
      {
        label: "Alumni",
        link: RouteConst.ALUMNI_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleStudent,
        subMenu: [],
        icon: "fa-solid fa-user-group"
      },
      {
        label: "Events",
        link: RouteConst.EVENTS_LINK,
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleHod ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleTNP,
        subMenu: [],
        icon: "fa-solid fa-star"
      },
      {
        label: "Internships",
        link: "internships",
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleStudent,
        subMenu: [],
        icon: "fa-solid fa-code"
      },
      {
        label: "Applications",
        link: "applications",
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleStudent,
        subMenu: [],
        icon: "fas fa-briefcase"
      },
      {
        label: "Career Resources",
        link: "career-resources",
        visibilityCondition:
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleAlumni ||
          this.accessControlService.hasRoleStudent ||
          this.accessControlService.hasRoleStudent,
        subMenu: [],
        icon: "fas fa-book"
      },

      {
        label: "My Company Profile",
        link: "company-profile",
        visibilityCondition: this.accessControlService.hasRoleEmployer,
        subMenu: [],
        icon: ""
      },
      {
        label: "Post Job",
        link: "post-job",
        visibilityCondition: this.accessControlService.hasRoleEmployer,
        subMenu: [],
        icon: "fas fa-clipboard-list"
      },
      {
        label: "Manage Listings",
        link: "manage-listings",
        visibilityCondition: this.accessControlService.hasRoleEmployer,
        subMenu: [],
        icon: "fas fa-clipboard-list"
      },
      {
        label: "Applications",
        link: "/applications",
        visibilityCondition:
          this.accessControlService.hasRoleEmployer ||
          this.accessControlService.hasRoleAdmin,
        subMenu: [],
        icon: "fas fa-briefcase"
      },
      {
        label: "Student Profiles",
        link: "/student-profiles",
        visibilityCondition: this.accessControlService.hasRoleEmployer,
        subMenu: [],
        icon: "fas fa-user-graduate"
      },
      {
        label: "Mentorship",
        link: "/mentorship",
        visibilityCondition: this.accessControlService.hasRoleAlumni,
        subMenu: [],
        icon: "fas fa-chalkboard-teacher"
      },
      {
        label: "My Partner Profile",
        link: "/partner-profile",
        visibilityCondition: this.accessControlService.hasRolePartner,
        subMenu: [],
        icon: "fas fa-users"
      },
      {
        label: "Post Opportunities",
        link: "/post-opportunities",
        visibilityCondition:
          this.accessControlService.hasRolePartner ||
          this.accessControlService.hasRoleAdmin ||
          this.accessControlService.hasRoleStudent,
        subMenu: [],
        icon: "fas fa-bullhorn"
      },
      {
        label: "Manage Opportunities",
        link: "/manage-opportunities",
        visibilityCondition: this.accessControlService.hasRolePartner,
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
