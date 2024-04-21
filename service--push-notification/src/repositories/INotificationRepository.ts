import { UserData } from "../entities/UserData";

interface INotificationRepository {
  sendNotification(userData: UserData): void;
}
