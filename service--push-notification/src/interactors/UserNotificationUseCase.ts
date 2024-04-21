import { UserData } from "../entities/UserData";

export interface DataNotificationModelUseCase {
  userDataBefore?: UserData;
  userDataAfter?: UserData;
  operation: string;
}
export interface UserNotificationUserCase {
  pushNotification(data: DataNotificationModelUseCase): void;
}
