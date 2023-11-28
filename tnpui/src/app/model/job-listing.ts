import { Employer } from "./employer-model";

export class JobListing {
  jobId!: number;
  employer!: Employer;
  title!: string;
  description!: string;
  requirements!: string;
  applicationDeadline!: Date;
  status!: string;
}
