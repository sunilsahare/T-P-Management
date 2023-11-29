import { Injectable } from '@angular/core';
import { APIConst } from '../constants/api-const';
import { RestService } from './rest.service';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private readonly STORAGE_KEY = 'auth_details';
  public hasAdmin:boolean = false;

  constructor(private restService:RestService) {}

  authenticate(username: string, password: string):any {
    const requestBody = {
      username: username,
      password: password,
    };
    return this.restService.login(APIConst.AUTHENTICATE, requestBody);
  }

}
