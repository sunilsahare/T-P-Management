import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user-model';
import { RestService } from './rest.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // private userApiUrl = 'user/'; // Replace with your login API URL
  private userApiUrl = 'api/v1/user/'; // Replace with your login API URL

  constructor(private http: HttpClient, private restService:RestService) {}

  
  public saveUser(user:User):any {
    return this.restService.post(this.userApiUrl, user);
  }

  public updateUser(user:User) {
    return this.restService.post(this.userApiUrl, user);
  }

  public getAllUser(pageNumber: number, pageSize: number):any {
    const params = new HttpParams()
      .set('page', pageNumber.toString())
      .set('size', pageSize.toString());
    return this.restService.get(this.userApiUrl, params);
  }

  public removeUser(userId:number[]) {
    return this.restService.delete(this.userApiUrl, userId);
  }


}
