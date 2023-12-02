export enum UserRoles {
  ROLE_ADMIN = 'ROLE_ADMIN',
  ROLE_HOD = 'ROLE_HOD',
  ROLE_STUDENT = 'ROLE_STUDENT',
  STUDENT = 'student',
  ROLE_TNP_OFFICER = 'ROLE_TNP_OFFICER',
  ROLE_EMPLOYER = "ROLE_EMPLOYER",
  ROLE_ALUMNI = "ROLE_ALUMNI",
  ROLE_PARTNER = "ROLE_PARTNER"
}

export const RoleInfo = [
  { role: UserRoles.ROLE_ADMIN, displayName: 'Admin' },
  { role: UserRoles.ROLE_EMPLOYER, displayName: 'Employer' },
  { role: UserRoles.ROLE_HOD, displayName: 'Head of Department' },
  { role: UserRoles.ROLE_ALUMNI, displayName: 'Alumni' },
  { role: UserRoles.ROLE_STUDENT, displayName: 'Student' },
  { role: UserRoles.ROLE_TNP_OFFICER, displayName: 'TNP Officer' },
  { role: UserRoles.ROLE_PARTNER, displayName: 'Partner' },
];

export function getRoleByDisplayName(displayName: string): string | null {
  const roleInfo = RoleInfo.find((role) => role.displayName === displayName);
  return roleInfo ? roleInfo.role : null;
}

export function getDisplayNameByRole(role: string): string | null {
  const roleInfo = RoleInfo.find((displayName) => displayName.role === role);
  return roleInfo ? roleInfo.displayName : null;
}

export function getAllRoles(): any[] | null {
  return RoleInfo;
}
