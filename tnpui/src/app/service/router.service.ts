import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router:Router) { }



  public navigate(url:string) {
    console.log('Navigating to -',url);
    this.router.navigate([url]);
  }

  navigateToUrl(basePath: string, route: string) {
    console.log('Navigating to -',basePath, route);
    const fullUrl = `${basePath}/${route}`;
    this.router.navigateByUrl(fullUrl);
  }


}
