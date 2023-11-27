import { JobListing } from "./job-listing";
import { Student } from "./student-model";

export class Application {
  applicationId!: number;
  jobListing!: JobListing;
  student!: Student;
  applicationDate!: Date;
  status!: string;
}
