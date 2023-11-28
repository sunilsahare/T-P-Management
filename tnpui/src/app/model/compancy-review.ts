import { Employer } from "./employer-model";
import { Student } from "./student-model";

export class CompanyReview {
  reviewId!: number;
  employer!: Employer;
  student!: Student;
  rating!: number;
  comment!: string;
  reviewDate!: Date;
}
