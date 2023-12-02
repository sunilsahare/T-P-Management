import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user-model';
import { RestService } from './rest.service';
import { APIConst } from '../constants/api-const';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // private userApiUrl = 'user/'; // Replace with your login API URL
  private userApiUrl = 'api/v1/user/'; // Replace with your login API URL

  constructor(private restService:RestService) {}

  
  public saveUser(user:User):any {
    return this.restService.register(APIConst.REGISTER, user);
  }

  public updateUser(user:User) {
    return this.restService.post(this.userApiUrl, user);
  }

  public getAllUser(pageObj:any):any {
    const params = new HttpParams()
      .set('page', pageObj.page.toString())
      .set('size', pageObj.size.toString());
    return this.restService.get(this.userApiUrl, params);
  }
  
  public updateUserStatus(userId:number, currentStatus:boolean, statusToBeUpdated:boolean):any {
    const params = new HttpParams()
      .set('currentStatus',currentStatus)
      .set('statusToBeUpdated', statusToBeUpdated);
    return this.restService.put(this.userApiUrl+userId, null,params);
  }

  public removeUser(userId:number[]) {
    return this.restService.delete(this.userApiUrl, userId);
  }


}
