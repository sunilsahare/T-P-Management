export class CompanyProfile {
  userId!: number;
  companyProfileId?: number;
  user: any; // You may need to replace 'any' with the actual User model
  companyName!: string;
  logoUrl!: string;
  industry!: string;
  description!: string;
  location!: string;
  website!: string;
  foundedYear!: number;
  companySize!: number;
  typeOfCompany!: string;
  contactInformation!: string;
  benefits!: string;
  companyCulture!: string;
  mission!: string;
  vision!: string;
  specializations!: string;
  awardsOrRecognitions!: string;
  newsOrUpdates!: string;
}
