import { Injectable } from '@angular/core';
import { RoleInfo, UserRoles } from '../enum/user-roles';
import { User } from '../model/user-model';

@Injectable({
  providedIn: 'root',
})
export class AccessControlService {

  private readonly STORAGE_KEY = 'auth_details';

  public hasRoleAdmin!: boolean;
  public hasRoleStudent!:boolean;
  public hasRoleEmployer!:boolean;
  public hasRoleTNP!:boolean;
  public hasRoleAlumni!:boolean;
  public hasRolePartner!:boolean;

  constructor() {}

  // Set user details in local storage
  setAuthDetails(username: string, role: string, jwtToken: string, user:User): void {
    const authDetails = { username, role, jwtToken , user};
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(authDetails));
    this.setRoleFlag();
  }

  private setRoleFlag() {
    // this.hasRoleAdmin = this.getUserRole() === UserRoles.ROLE_ADMIN.toString();
    this.hasRoleEmployer = this.getUserRole() === UserRoles.ROLE_EMPLOYER.toString();
    this.hasRoleStudent = this.getUserRole() === UserRoles.ROLE_STUDENT.toString();
    this.hasRoleTNP = this.getUserRole() === UserRoles.ROLE_TNP_OFFICER.toString();
    this.hasRoleAlumni = this.getUserRole() === UserRoles.ROLE_ALUMNI.toString();
    this.hasRolePartner = this.getUserRole() === UserRoles.ROLE_PARTNER.toString();
  }

  // Get user details from local storage
  getAuthDetails(): {
    user: User | null; username: string, role: string, jwtToken: string 
} | null {
    const storedDetails = localStorage.getItem(this.STORAGE_KEY);
    return storedDetails ? JSON.parse(storedDetails) : null;
  }

  // Clear user details from local storage
  clearAuthDetails(): void {
    localStorage.removeItem(this.STORAGE_KEY);
  }

  // Check if the user is logged in
  isLoggedIn(): boolean {
    return !!this.getAuthDetails();
  }

  // Get the username
  getUsername(): string | null {
    const authDetails = this.getAuthDetails();
    return authDetails ? authDetails.username : null;
  }

  // Get the username
  getUserId(): number | null {
    const authUser = this.getUser();
    return authUser ? authUser.userId : null;
  }

  // Get the username
  getUser(): User | null {
    const authDetails = this.getAuthDetails();
    return authDetails ? authDetails.user : null;
  }

  // Get the user role
  getUserRole(): string | null {
    const authUser = this.getUser();
    return authUser ? authUser.role : null;
  }

  // Get the JWT token
  getJwtToken(): string | null {
    const authDetails = this.getAuthDetails();
    return authDetails ? authDetails.jwtToken : null;
  }

  // Get All Static roles
  getAllRoles() : any {
    return RoleInfo;
  }

}
