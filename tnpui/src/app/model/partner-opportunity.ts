import { Partner } from "./partner";

export class PartnerOpportunity {
  opportunityId!: number;
  partner!: Partner;
  title!: string;
  description!: string;
  applicationDeadline!: Date;
  status!: string;
}
