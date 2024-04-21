import {
  DataNotificationModelUseCase,
  UserNotificationUserCase,
} from "./UserNotificationUseCase";

export class UserNotificationUserCaseImpl implements UserNotificationUserCase {
  public constructor() {}

  pushNotification(data: DataNotificationModelUseCase): void {
    console.log("Notification pushed: ", data);
  }
}
