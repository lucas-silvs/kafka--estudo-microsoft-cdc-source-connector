import { UserData } from "../entities/UserData";

export interface NotificationRepository {
  sendNotification(
    userContactTarget: string,
    message: string,
    subjectMessage: string
  ): void;
}
