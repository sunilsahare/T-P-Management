import { User } from "./user-model";

export class Employer {
  userId!: number;
  user!: User;
  companyName!: string;
  industry!: string;
  companyDescription!: string;
  companyLogoUrl!: string;
  reviewsAndRatings!: string;
}
