import { User } from "./user-model";

export class Admin {
  userId!: number;
  user!: User;
  accessLevel!: string;
  feedbackAndSupportManagement!: string;
  systemConfiguration!: string;
}
