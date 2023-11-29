// rest.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AccessControlService } from './access-control.service';

@Injectable({
  providedIn: 'root',
})
export class RestService {
  constructor(private http: HttpClient, private accessControlService:AccessControlService) {}

  // private apiUrl = 'http://localhost:8080/tnpserver'; // Replace with your API endpoint
  private apiUrl = '/tnpserver';

  private openAPI: string[] = ['auth/register', 'api/ve/user'];

  // Generic method for making HTTP requests
  request(
    method: string,
    endpoint: string,
    params?: HttpParams,
    body?: any,
    headers?: HttpHeaders
  ): Observable<any> {

    headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.accessControlService.getJwtToken()
    });
    
    const requestOptions: any = {
      params,
      body,
      headers
    };

    return this.http.request(method, `${this.apiUrl}/${endpoint}`, requestOptions);
  }

  // Method to make GET request
  get(endpoint: string, params?: HttpParams, headers?: HttpHeaders): Observable<any> {
    return this.request('GET', endpoint, params, undefined, headers);
  }

  // Method to make POST request
  post(endpoint: string, body: any, params?: HttpParams, headers?: HttpHeaders): Observable<any> {
    return this.request('POST', endpoint, params, body, headers);
  }

  // Method to make PUT request
  put(endpoint: string, body: any, params?: HttpParams, headers?: HttpHeaders): Observable<any> {
    return this.request('PUT', endpoint, params, body, headers);
  }

  // Method to make DELETE request
  delete(endpoint: string, body?: any, params?: HttpParams, headers?: HttpHeaders): Observable<any> {
    return this.request('DELETE', endpoint, params, body, headers);
  }

  // Method to handle file upload
  uploadFile(endpoint: string, file: File, params?: HttpParams, headers?: HttpHeaders): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.post(endpoint, formData, params, headers);
  }

  login(endpoint: string, body: any): Observable<any> {
    const requestOptions: any = { body };
    return this.http.request('POST', `${this.apiUrl}/${endpoint}`, requestOptions);
  }
  
  register(endpoint: string, body: any): Observable<any> {
    const requestOptions: any = { body };
    return this.http.request('POST', `${this.apiUrl}/${endpoint}`, requestOptions);
  }

  private isNotOpenAPI(apiName: string): boolean {
    return this.openAPI.includes(apiName);
  }


}
