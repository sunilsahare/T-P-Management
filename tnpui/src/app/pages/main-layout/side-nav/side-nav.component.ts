import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserRoles } from "src/app/enum/user-roles";
import { AccessControlService } from "src/app/service/access-control.service";
import { MenuService } from "src/app/service/menu.service";
import { RouterService } from "src/app/service/router.service";
import { ToastService } from "src/app/service/toast.service";

@Component({
  selector: "app-side-nav",
  templateUrl: "./side-nav.component.html",
  styleUrls: ["./side-nav.component.css"],
})
export class SideNavComponent implements OnInit {
  constructor(
    private router: Router,
    private menuService: MenuService,
    private accessControlService: AccessControlService,
    private alertService: ToastService,
    private routerService:RouterService
  ) {}

  public menu = this.menuService.getMenuForUserRole();

  ngOnInit(): void {}

  public navigateToLink(url: string | null) {
    if (url == null || url == undefined) {
      return;
    }
    this.router.navigate([url]);
  }

  logOut() {
    if(this.accessControlService.isLoggedIn()) {
      this.accessControlService.clearAuthDetails();
      this.routerService.navigate("/login");
      this.alertService.success("User Successfully Logged out.")
    } else {
      this.alertService.error("Logged In User Not Found.")
    }
  }

  getFullName(): string | undefined {
    return this.accessControlService.getUser()?.fullName;
  }

}
