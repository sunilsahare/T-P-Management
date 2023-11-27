import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private loginApiUrl = 'http://localhost:8080/login'; // Replace with your login API URL

  constructor(private http: HttpClient) {}

  authenticate(username: string, password: string): Observable<any> {
    // Create a request body with username and password
    const requestBody = {
      username: username,
      password: password,
    };

    // Send a POST request to the login API
    return this.http.post(this.loginApiUrl, requestBody);
  }
}
