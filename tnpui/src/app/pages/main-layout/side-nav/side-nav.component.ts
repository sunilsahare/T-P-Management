import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserRoles } from 'src/app/enum/user-roles';
import { MenuService } from 'src/app/service/menu.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit {

  constructor(private router:Router) {

  }

  public menu = MenuService.getMenuForUserRole(UserRoles.ROLE_ADMIN);

  ngOnInit(): void {
  }


  public navigateToLink(url:string | null) {
    if(url == null || url == undefined) {
      return;
    }
    this.router.navigate([url]);
  }

}
