import { User } from "./user-model";

export class Partner {
  userId!: number;
  user!: User;
  organizationName!: string;
  industry!: string;
  organizationDescription!: string;
  organizationLogoUrl!: string;
  reviewsAndRatings!: string;
}
